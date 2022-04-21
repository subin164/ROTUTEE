package com.greedy.rotutee.member.service;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.member.dto.*;
import com.greedy.rotutee.member.entity.Achievement;
import com.greedy.rotutee.member.entity.AttachedFile;
import com.greedy.rotutee.member.entity.Member;
import com.greedy.rotutee.member.entity.TutorInfo;
import com.greedy.rotutee.member.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    private final ProfileRepository profileRepository;
    private final AchievementHistoryRepository achievementHistoryRepository;
    private final AchievementRepository achievementRepository;
    private final TutorInfoRepository tutorInfoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ProfileService(MemberRepository memberRepository, ModelMapper modelMapper, ProfileRepository profileRepository, AchievementHistoryRepository achievementHistoryRepository, AchievementRepository achievementRepository, TutorInfoRepository tutorInfoRepository) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
        this.profileRepository = profileRepository;
        this.achievementHistoryRepository = achievementHistoryRepository;
        this.achievementRepository = achievementRepository;
        this.tutorInfoRepository = tutorInfoRepository;
    }

    public MemberDTO memberProfile(int memberNo) {

        return modelMapper.map(memberRepository.findById(memberNo), MemberDTO.class);
    }

    public AchievementDTO findMemberAchievement(int memberNo) {

        Achievement achievement = achievementRepository.findById(achievementHistoryRepository.findAchievement(entityManager,memberNo)).get();

        return modelMapper.map(achievement, AchievementDTO.class);
    }

    public void profileUpload(List<AttachedFile> attachedFiles) throws Exception {

        if(attachedFiles.isEmpty()) {
            throw new Exception();
        }

        for(AttachedFile file : attachedFiles) {

            profileRepository.save(file);
        }
    }

    public AttachedFileDTO imgTest(int no) {

        return modelMapper.map(profileRepository.findById(no), AttachedFileDTO.class);
    }

    public TutorInfoDTO findTutorInfo(int memberNo) {

        return modelMapper.map(tutorInfoRepository.findById(memberNo), TutorInfoDTO.class);
    }

    public List<AchievementDTO> findAllAchievementCategory() {

        List<Achievement> achievementList = achievementRepository.findAll();

        return achievementList.stream().map(achievement ->
                modelMapper.map(achievement, AchievementDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public void modifyProfile(CustomUser loginMember, MemberDTO member, TutorInfoDTO tutorInfo) {

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
    }
}
