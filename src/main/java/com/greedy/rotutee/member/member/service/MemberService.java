package com.greedy.rotutee.member.member.service;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.member.member.dto.LectureCategoryDTO;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.dto.ReasonsDTO;
import com.greedy.rotutee.member.member.entity.*;
import com.greedy.rotutee.member.member.entity.AttachedFile;
import com.greedy.rotutee.member.member.entity.MemberRole;
import com.greedy.rotutee.member.member.repository.*;
import com.greedy.rotutee.member.profile.dto.AttachedFileDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final LectureCategoryRepository lectureCategoryRepository;
    private final MemberInterestPartRepository memberInterestPartRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final MemberAchievementHistoryRepository memberAchievementHistoryRepository;
    private final AchievementRepository achievementRepository;
    private final MemberAchievementRepository memberAchievementRepository;
    private final MemberStatusHistoryRepository memberStatusHistoryRepository;
    private final MemberStatusHistoryRepositoryQuery memberStatusHistoryRepositoryQuery;
    private final ReasonsRepository reasonsRepository;
    private final AttachedFileRepository attachedFileRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public MemberService(LectureCategoryRepository lectureCategoryRepository, MemberInterestPartRepository memberInterestPartRepository, PasswordEncoder passwordEncoder, MemberRepository memberRepository, MemberRoleRepository memberRoleRepository, RoleRepository roleRepository, ModelMapper modelMapper, AchievementRepository achievementRepository, MemberAchievementRepository memberAchievementRepository, MemberAchievementHistoryRepository memberAchievementHistoryRepository, MemberStatusHistoryRepository memberStatusHistoryRepository, MemberStatusHistoryRepositoryQuery memberStatusHistoryRepositoryQuery, ReasonsRepository reasonsRepository, AttachedFileRepository attachedFileRepository) {

        this.lectureCategoryRepository = lectureCategoryRepository;
        this.memberInterestPartRepository = memberInterestPartRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        this.memberRoleRepository = memberRoleRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.achievementRepository = achievementRepository;
        this.memberAchievementRepository = memberAchievementRepository;
        this.memberAchievementHistoryRepository = memberAchievementHistoryRepository;
        this.memberStatusHistoryRepository = memberStatusHistoryRepository;
        this.memberStatusHistoryRepositoryQuery = memberStatusHistoryRepositoryQuery;
        this.reasonsRepository = reasonsRepository;
        this.attachedFileRepository = attachedFileRepository;
    }

    /* 사용자번호로 사용자 정보 조회용 메서드 */
    public MemberDTO findMember(int memberNo) {

        return modelMapper.map(memberRepository.findById(memberNo).get(), MemberDTO.class);
    }

    /* 모든 강의 카테고리 정보 조회용 메서드 */
    public List<LectureCategoryDTO> findLectureCategoryList() {

        List<LectureCategory> lectureCategoryList =  lectureCategoryRepository.findAll();

        return lectureCategoryList.stream().map(lectureCategory ->
                modelMapper.map(lectureCategory, LectureCategoryDTO.class)).collect(Collectors.toList());
    }

    /* 사용자 등록용 메서드 */
    @Transactional
    public void registMember(MemberDTO member, int[] categoryNo) {

        String encodePwd = passwordEncoder.encode(member.getPwd());
        member.setPwd(encodePwd);
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        member.setRegistrationDate(date);
        member.setLeaveStatusYn("N");

        memberRepository.save(modelMapper.map(member, Member.class));

        setMemberInterest(member, categoryNo);
        setMemberStatus(member);
//        setMemberAchievement(member);
        setMemberRole(member);
    }

    /* 사용자 등록시 관심분야 등록용 메서드 */
    private void setMemberInterest(MemberDTO member, int[] categoryNo) {

        Member foundMember = memberRepository.findMemberByEmail(member.getEmail());
        List<LectureCategory> lectureCategoryList = lectureCategoryRepository.findAll();

        for(LectureCategory lectureCategory : lectureCategoryList) {
            MemberInterestPart memberInterestPart = new MemberInterestPart();
            memberInterestPart.setMember(foundMember);
            memberInterestPart.setLectureCategory(lectureCategory);
            memberInterestPart.setInterestDegree(0);
            for(int i = 0; i < categoryNo.length; i++) {
                if(categoryNo[i] == memberInterestPart.getLectureCategory().getNo()) {
                    memberInterestPart.setInterestDegree(10);
                }
            }
            memberInterestPartRepository.save(memberInterestPart);
        }
    }

    /* 사용자 등록시 상태등록용 메서드 */
    private void setMemberStatus(MemberDTO member) {

        Member foundMember = memberRepository.findMemberByEmail(member.getEmail());
        MemberStatusHistory memberStatusHistory = new MemberStatusHistory();
        memberStatusHistory.setMember(foundMember);
        memberStatusHistory.setStatus("활동");
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        memberStatusHistory.setHistoryDate(date);

        //when
        memberStatusHistoryRepository.save(memberStatusHistory);
    }

    /* 사용자 등록시 업적등록용 메서드 */
    private void setMemberAchievement(MemberDTO member) {

        Member foundMember = memberRepository.findMemberByEmail(member.getEmail());
        MemberAchievement memberAchievement = new MemberAchievement();
        memberAchievement.setAchievement(achievementRepository.findById(1).get());
        memberAchievement.setMember(foundMember);
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        memberAchievement.setGetDat(date);
        memberAchievementRepository.save(memberAchievement);

        MemberAchievementHistory memberAchievementHistory = new MemberAchievementHistory();
        memberAchievementHistory.setMemberAchievement(memberAchievementRepository.findByMemberAndAchievement(foundMember, achievementRepository.findById(1).get()));
        memberAchievementHistory.setMember(foundMember);
        memberAchievementHistory.setChangeDate(date);

        memberAchievementHistoryRepository.save(memberAchievementHistory);
    }

    /* 사용자 등록시 권한등록용 메서드 */
    private void setMemberRole(MemberDTO member) {

        MemberRole memberRole = new MemberRole();
        memberRole.setMember(memberRepository.findMemberByEmail(member.getEmail()));
        memberRole.setRole(roleRepository.findRoleByNo(3));
        memberRoleRepository.save(memberRole);
    }

    /* 이메일 중복 확인용 메서드 */
    public boolean duplicateEmail(String checkEmail) {

        return memberRepository.findMemberByEmail(checkEmail) == null ? true : false;
    }

    /* 비밀번호 재등록용 메서드 */
    @Transactional
    public void findMemberPwd(MemberDTO member) {

        String encodePwd = passwordEncoder.encode(member.getPwd());

        Member findMember = memberRepository.findMemberByEmail(member.getEmail());
        findMember.setPwd(encodePwd);
    }

    /* 비밀번호 수정용 메서드 */
    @Transactional
    public void modifyPassword(CustomUser loginMember, String modifyPassword) {

        String encodePwd = passwordEncoder.encode(modifyPassword);

        Member findMember = memberRepository.findById(loginMember.getNo()).get();

        findMember.setPwd(encodePwd);
        System.out.println("encodePwd = " + encodePwd);
        System.out.println("findMember = " + findMember);
    }


    public Page<MemberDTO> findAllMember(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("registrationDate").descending());

        return memberRepository.findAll(pageable).map(member -> modelMapper.map(member, MemberDTO.class));
    }

    public Page<MemberDTO> findAllTutee(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("no").descending());

        return memberRepository.findByMemberRoleListRoleNo(3, pageable).map(member -> modelMapper.map(member, MemberDTO.class));
    }

    public Page<MemberDTO> findAllTutor(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("no").descending());

        return memberRepository.findByMemberRoleListRoleNo(4, pageable).map(member -> modelMapper.map(member, MemberDTO.class));
    }


    public MemberStatusHistory findMemberStatus(int memberNo) {

        return memberStatusHistoryRepositoryQuery.findMemberStatus(entityManager, memberNo);
    }

    @Transactional
    public void memberStatusStop(int memberNo, int stopReasons, int stopDate) {

        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        Date endDate = new Date(miliseconds + (long)( ( 1000 * 60 * 60 * 24 ) * stopDate ));

        SuspensionHitory suspensionHitory = new SuspensionHitory();
        suspensionHitory.setStartDate(date);
        suspensionHitory.setEndDate(endDate);
        suspensionHitory.setReasons(reasonsRepository.findById(stopReasons).get());

        MemberStatusHistory memberStatusHistory = new MemberStatusHistory();
        memberStatusHistory.setStatus("정지");
        memberStatusHistory.setMember(memberRepository.findById(memberNo).get());
        memberStatusHistory.setSuspensionHitory(suspensionHitory);
        memberStatusHistory.setHistoryDate(date);

        suspensionHitory.setMemberStatusHistory(memberStatusHistory);

        memberStatusHistoryRepository.save(memberStatusHistory);

    }

    public List<ReasonsDTO> findReasonsList() {

        return reasonsRepository.findAll().stream().map(reasons -> modelMapper.map(reasons, ReasonsDTO.class)).collect(Collectors.toList());
    }

    /* 회원 정지 철회 메서드 */
    @Transactional
    public void memberStatusPlay(int memberNo) {

        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        MemberStatusHistory memberStatusHistory = new MemberStatusHistory();
        memberStatusHistory.setStatus("활동");
        memberStatusHistory.setMember(memberRepository.findById(memberNo).get());
        memberStatusHistory.setHistoryDate(date);

        memberStatusHistoryRepository.save(memberStatusHistory);
    }

    public Page<AttachedFileDTO> findMemberAttachedFileList(int memberNo, Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("attachedFileNo").descending());

        String division = "서류";

        Page<AttachedFile> attachedFilesList = attachedFileRepository.findByMemberNoAndDivisionAndFileDeletionYn(memberNo, division, "N ", pageable);

        return attachedFilesList.map(attachedFile -> modelMapper.map(attachedFile, AttachedFileDTO.class));
    }

    @Transactional
    public void removeMemberFiles(int filesNo) {

        AttachedFile attachedFile = attachedFileRepository.findById(filesNo).get();

        attachedFile.setFileDeletionYn("Y");
    }

    public Page<MemberDTO> findSearchMemberList(String searchCondition, String searchValue, Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("no").descending());

        Page<Member> memberList = null;

        if("name".equals(searchCondition)) {

            memberList = memberRepository.findByNameContaining(searchValue, pageable);
        } else if("email".equals(searchCondition)) {

            memberList = memberRepository.findByEmailContaining(searchValue, pageable);
        } else if("category".equals(searchCondition) && "튜터".equals(searchValue)) {

            memberList = memberRepository.findByMemberRoleListRoleNo(4, pageable);
        } else if("category".equals(searchCondition) && "튜티".equals(searchValue)) {

            memberList = memberRepository.findByMemberRoleListRoleNo(3, pageable);
        }

        return memberList.map(member -> modelMapper.map(member, MemberDTO.class));
    }

}
