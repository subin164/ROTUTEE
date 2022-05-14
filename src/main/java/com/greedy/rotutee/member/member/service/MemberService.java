package com.greedy.rotutee.member.member.service;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.member.member.dto.*;
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
    private final SecessionReasonRepository secessionReasonRepository;
    private final MemberSecessionHistoryRepository memberSecessionHistoryRepository;
    private final PointAcquisitionPlaceRepository pointAcquisitionPlaceRepository;
    private final PointHistoryRepositoryQuery pointHistoryRepositoryQuery;
    private final PointHistoryRepository pointHistoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public MemberService(LectureCategoryRepository lectureCategoryRepository, MemberInterestPartRepository memberInterestPartRepository, PasswordEncoder passwordEncoder, MemberRepository memberRepository, MemberRoleRepository memberRoleRepository, RoleRepository roleRepository, ModelMapper modelMapper, AchievementRepository achievementRepository, MemberAchievementRepository memberAchievementRepository, MemberAchievementHistoryRepository memberAchievementHistoryRepository, MemberStatusHistoryRepository memberStatusHistoryRepository, MemberStatusHistoryRepositoryQuery memberStatusHistoryRepositoryQuery, ReasonsRepository reasonsRepository, AttachedFileRepository attachedFileRepository, SecessionReasonRepository secessionReasonRepository, MemberSecessionHistoryRepository memberSecessionHistoryRepository, PointAcquisitionPlaceRepository pointAcquisitionPlaceRepository, PointHistoryRepositoryQuery pointHistoryRepositoryQuery, PointHistoryRepository pointHistoryRepository) {

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
        this.secessionReasonRepository = secessionReasonRepository;
        this.memberSecessionHistoryRepository = memberSecessionHistoryRepository;
        this.pointAcquisitionPlaceRepository = pointAcquisitionPlaceRepository;
        this.pointHistoryRepositoryQuery = pointHistoryRepositoryQuery;
        this.pointHistoryRepository = pointHistoryRepository;
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

        //비밀번호 암호화
        String encodePwd = passwordEncoder.encode(member.getPwd());
        member.setPwd(encodePwd);

        memberRepository.save(modelMapper.map(member, Member.class));

        //등록시 포인트 지급
        setMemberPointHistory(member);
        //카테고리별 관심도 등록
        setMemberInterest(member, categoryNo);
        //활동상태 등록
        setMemberStatus(member);
        //권한 등록
        setMemberRole(member);
    }

    /* 사용자 등록시 포인트지급 메서드 */
    private void setMemberPointHistory(MemberDTO member) {

        Member foundMember = memberRepository.findMemberByEmail(member.getEmail());
        PointAcquisitionPlace pointAcquisitionPlace = pointAcquisitionPlaceRepository.findById(99).get();
        PointHistory pointHistory = new PointHistory();
        pointHistory.setChangePoint(pointAcquisitionPlace.getPoint());
        pointHistory.setChangeDate(new Date(System.currentTimeMillis()));
        pointHistory.setDivision("획득");
        pointHistory.setMember(foundMember);
        pointHistory.setPointAcquisitionPlace(pointAcquisitionPlace);
        pointHistory.setFinalPoint(pointAcquisitionPlace.getPoint());

        pointHistoryRepository.save(pointHistory);
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
            //회원가입시 선택한 강의 카테고리를 만날 시 별도로 관심도 등록
            for (int i = 0; i < categoryNo.length; i++) {
                if (categoryNo[i] == memberInterestPart.getLectureCategory().getNo()) {
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
        memberStatusHistory.setHistoryDate(new Date(System.currentTimeMillis()));

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
        //기본으로 튜티권한으로 등록
        memberRole.setRole(roleRepository.findRoleByNo(3));
        memberRoleRepository.save(memberRole);
    }

    /* 이메일 중복 확인용 메서드 */
    public boolean duplicateEmail(String checkEmail) {

        //확인할 이메일이 DB에 없다면 ture 반환
        return memberRepository.findMemberByEmail(checkEmail) == null ? true : false;
    }

    /* 비밀번호 재등록용 메서드 */
    @Transactional
    public void findMemberPwd(MemberDTO member) {

        //변경할 비밀번호 암호화
        String encodePwd = passwordEncoder.encode(member.getPwd());
        //비밀번호 변경할 회원 조회
        Member findMember = memberRepository.findMemberByEmail(member.getEmail());
        findMember.setPwd(encodePwd);
    }

    /* 비밀번호 수정용 메서드 */
    @Transactional
    public void modifyPassword(CustomUser loginMember, String modifyPassword) {

        //변경할 비밀번호 암호화
        String encodePwd = passwordEncoder.encode(modifyPassword);
        //비밀번호 변경할 회원 조회
        Member findMember = memberRepository.findById(loginMember.getNo()).get();
        findMember.setPwd(encodePwd);
    }


    public Page<MemberDTO> findAllMember(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("registrationDate").descending());

        //모든 회원 목록 조회
        return memberRepository.findAll(pageable).map(member -> modelMapper.map(member, MemberDTO.class));
    }


    public MemberStatusHistoryDTO findMemberStatus(int memberNo) {

        //회원의 마지막 활동 내역 조회 (=회원의 현재 활동상태)
        return modelMapper.map(memberStatusHistoryRepositoryQuery.findMemberStatus(entityManager, memberNo), MemberStatusHistoryDTO.class);
    }

    @Transactional
    public void memberStatusStop(int memberNo, int stopReasons, int stopDate) {

        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        //정지일 계산 (오늘 + (하루 x 정지일 될 일수))
        Date endDate = new Date(miliseconds + (long)( ( 1000 * 60 * 60 * 24 ) * stopDate ));

        //정지 내역 등록
        SuspensionHitory suspensionHitory = new SuspensionHitory();
        suspensionHitory.setStartDate(date);
        suspensionHitory.setEndDate(endDate);
        suspensionHitory.setReasons(reasonsRepository.findById(stopReasons).get());
        //회원 활동상태 내역 등록
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

        //회원 활동상태 내역 등록
        Date date = new Date(System.currentTimeMillis());
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

        //첨부파일중 본인 회원번호의 서류만 조회
        String division = "서류";
        Page<AttachedFile> attachedFilesList = attachedFileRepository.findByMemberNoAndDivisionAndFileDeletionYn(memberNo, division, "N ", pageable);

        return attachedFilesList.map(attachedFile -> modelMapper.map(attachedFile, AttachedFileDTO.class));
    }

    @Transactional
    public void removeMemberFiles(int filesNo) {

        //삭제할 회원 파일 조회
        AttachedFile attachedFile = attachedFileRepository.findById(filesNo).get();
        attachedFile.setFileDeletionYn("Y");
    }

    public Page<MemberDTO> findSearchMemberList(String searchCondition, String searchValue, Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("no").descending());

        Page<Member> memberList = null;

        //검색 조건별 회원 목록 조회
        if("name".equals(searchCondition)) {
            //이름으로 검색할 경우
            memberList = memberRepository.findByNameContaining(searchValue, pageable);
        } else if("email".equals(searchCondition)) {
            //이메일로 검색할 경우
            memberList = memberRepository.findByEmailContaining(searchValue, pageable);
        } else if("category".equals(searchCondition) && "튜터".equals(searchValue)) {
            //카테고리(튜터)로 검색할 경우
            memberList = memberRepository.findByMemberRoleListRoleNo(4, pageable);
        } else if("category".equals(searchCondition) && "튜티".equals(searchValue)) {
            //카테고리(튜티)로 검색할 경우
            memberList = memberRepository.findByMemberRoleListRoleNo(3, pageable);
        }

        return memberList.map(member -> modelMapper.map(member, MemberDTO.class));
    }

    @Transactional
    public void secessionMember(int memberNo, int reasonNo, String content) {

        //회원 활동상태 내역 등록
        MemberStatusHistory memberStatusHistory = new MemberStatusHistory();
        memberStatusHistory.setMember(memberRepository.findById(memberNo).get());
        memberStatusHistory.setStatus("탈퇴");
        memberStatusHistory.setHistoryDate(new Date(System.currentTimeMillis()));
        //회원 탈퇴 내역 등록
        MemberSecessionHistory memberSecessionHistory = new MemberSecessionHistory();
        memberSecessionHistory.setSecessionDate(new Date(System.currentTimeMillis()));
        memberSecessionHistory.setSecessionReason(secessionReasonRepository.findById(reasonNo).get());
        memberSecessionHistory.setContent(content);
        memberSecessionHistory.setMemberStatusHistory(memberStatusHistory);

        memberSecessionHistoryRepository.save(memberSecessionHistory);
    }

    public List<SecessionReasonDTO> findAllSecessionCategory() {

        //모든 탈퇴사유 조회
        List<SecessionReason> secessionReasonList = secessionReasonRepository.findAll();

        return secessionReasonList.stream().map(secessionReason -> modelMapper.map(secessionReason, SecessionReasonDTO.class)).collect(Collectors.toList());
    }

}
