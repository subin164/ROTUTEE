package com.greedy.rotutee.member.profile.service;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.profile.repository.TutorInfo;
import com.greedy.rotutee.member.member.repository.MemberRepository;
import com.greedy.rotutee.member.profile.entity.*;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.dto.AchievementDTO;
import com.greedy.rotutee.member.profile.dto.AttachedFileDTO;
import com.greedy.rotutee.member.profile.entity.TutorInfoDTO;
import com.greedy.rotutee.member.profile.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : com.greedy.rotutee.member.service
 * fileName : ProfileService
 * author : 7sang
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 7sang 최초 생성
 */

@Service
public class ProfileService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final AttachedFileRepository attachedFileRepository;
    private final AchievementHistoryRepository achievementHistoryRepository;
    private final AchievementRepository achievementRepository;
    private final TutorInfoRepository tutorInfoRepository;
    private final MemberAchievementRepository memberAchievementRepository;
    private final MemberAchievementHistoryRepository memberAchievementHistoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ProfileService(MemberRepository memberRepository, ModelMapper modelMapper, AttachedFileRepository attachedFileRepository, AchievementHistoryRepository achievementHistoryRepository, AchievementRepository achievementRepository, TutorInfoRepository tutorInfoRepository, MemberAchievementRepository memberAchievementRepository, MemberAchievementHistoryRepository memberAchievementHistoryRepository) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
        this.attachedFileRepository = attachedFileRepository;
        this.achievementHistoryRepository = achievementHistoryRepository;
        this.achievementRepository = achievementRepository;
        this.tutorInfoRepository = tutorInfoRepository;
        this.memberAchievementRepository = memberAchievementRepository;
        this.memberAchievementHistoryRepository = memberAchievementHistoryRepository;
    }

    /* 사용자 정보 조회용 메서드 */
    public MemberDTO memberProfile(int memberNo) {

        return modelMapper.map(memberRepository.findById(memberNo), MemberDTO.class);
    }

    /* 사용자 현재 칭호 조회용 메서드 */
    public AchievementDTO findMemberAchievement(int memberNo) {

        Achievement achievement = achievementRepository.findById(achievementHistoryRepository.findAchievement(entityManager,memberNo)).get();

        return modelMapper.map(achievement, AchievementDTO.class);
    }

    /* 프로필 사진 업로드용 메서드 */
    @Transactional
    public void profileUpload(AttachedFile attachedFiles) throws Exception {

        attachedFileRepository.save(attachedFiles);
    }

    /*  */
    public AttachedFileDTO imgTest(int no) {

        return modelMapper.map(attachedFileRepository.findById(no), AttachedFileDTO.class);
    }

    /* 튜터 개인정보 조회용 메서드 */
    public TutorInfoDTO findTutorInfo(int memberNo) {

        return modelMapper.map(tutorInfoRepository.findById(memberNo), TutorInfoDTO.class);
    }

    /* 전체 칭호 카테고리 정보 조회용 메서드 */
    public List<AchievementDTO> findAllAchievementCategory() {

        System.out.println("카테고리조회");
        List<Achievement> achievementList = achievementRepository.findAll();
        System.out.println("카테고리조회");

        return achievementList.stream().map(achievement ->
                modelMapper.map(achievement, AchievementDTO.class)).collect(Collectors.toList());
    }

    /* 사용자 프로필 수정용 메서드 */
    @Transactional
    public void modifyProfile(CustomUser loginMember, MemberDTO member, TutorInfoDTO tutorInfo, int achievementNo) {

        Member foundMember = memberRepository.findById(loginMember.getNo()).get();
        foundMember.setIntroduction(member.getIntroduction());
        foundMember.setNickname(member.getNickname());
        foundMember.setName(member.getName());

        if(loginMember.getMemberRoleList().get(0).getRole().getName().equals("ROLE_TUTOR")) {
            TutorInfo foundTutorInfo = tutorInfoRepository.findById(loginMember.getNo()).get();
            foundTutorInfo.setAddress(tutorInfo.getAddress());
            foundTutorInfo.setAccountNumber(tutorInfo.getAccountNumber());
            foundTutorInfo.setBankName(tutorInfo.getBankName());
        }

        setMemberAhievment(loginMember.getNo(), achievementNo);

    }

    /* 사용자 칭호 수정용 메서드 */
    private void setMemberAhievment(int memberNo, int achievementNo) {

        Member foundMember = memberRepository.findById(memberNo).get();
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);

        MemberAchievementHistory memberAchievementHistory = new MemberAchievementHistory();
        memberAchievementHistory.setMemberAchievement(memberAchievementRepository.findByMemberAndAchievement(foundMember, achievementRepository.findById(achievementNo).get()));
        memberAchievementHistory.setMember(foundMember);
        memberAchievementHistory.setChangeDate(date);

        memberAchievementHistoryRepository.save(memberAchievementHistory);
    }

    public AttachedFileDTO findMemberProfile(int memeberNo) {

        String division = "프로필";

        AttachedFile attachedFile = attachedFileRepository.findByMemberNoAndDivision(memeberNo, division);

        if(attachedFile != null) {
            return modelMapper.map(attachedFile, AttachedFileDTO.class);
        }

        return new AttachedFileDTO();
    }
}
