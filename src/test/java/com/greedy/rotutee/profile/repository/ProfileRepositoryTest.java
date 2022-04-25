package com.greedy.rotutee.profile.repository;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.member.member.repository.MemberRepository;
import com.greedy.rotutee.member.profile.entity.Achievement;
import com.greedy.rotutee.member.profile.entity.AttachedFile;
import com.greedy.rotutee.member.profile.entity.TutorInfo;
import com.greedy.rotutee.member.profile.repository.AchievementHistoryRepository;
import com.greedy.rotutee.member.profile.repository.AchievementRepository;
import com.greedy.rotutee.member.profile.repository.TutorInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * packageName : com.greedy.rotutee.profile.repository
 * fileName : ProfileRepositoryTest
 * author : 7sang
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-22 7sang 최초 생성
 */


@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class ProfileRepositoryTest {

    private final MemberRepository memberRepository;
    private final AchievementHistoryRepository achievementHistoryRepository;
    private final AchievementRepository achievementRepository;
    private final TutorInfoRepository tutorInfoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ProfileRepositoryTest(MemberRepository memberRepository, AchievementHistoryRepository achievementHistoryRepository, AchievementRepository achievementRepository, TutorInfoRepository tutorInfoRepository) {
        this.memberRepository = memberRepository;
        this.achievementHistoryRepository = achievementHistoryRepository;
        this.achievementRepository = achievementRepository;
        this.tutorInfoRepository = tutorInfoRepository;
    }

    @Test
    public void init() {
        assertNotNull(memberRepository);
        assertNotNull(achievementHistoryRepository);
        assertNotNull(achievementRepository);
        assertNotNull(tutorInfoRepository);
        assertNotNull(entityManager);
    }


    @Test
    public void 사용자_현재_칭호_조회_테스트_메서드() {

        //given
        int memberNo = 14;

        //when
        Achievement achievement = achievementRepository.findById(achievementHistoryRepository.findAchievement(entityManager,memberNo)).get();

        //then
        assertNotNull(achievement);
        System.out.println("achievement = " + achievement);
    }

    /* 프로필 사진 업로드용 메서드 */
    @Test
    public void 프로필_사진_업로드_테스트_메서드(List<AttachedFile> attachedFiles) throws Exception {

        if(attachedFiles.isEmpty()) {
            throw new Exception();
        }

        for(AttachedFile file : attachedFiles) {

//            profileRepository.save(file);
        }
    }

    @Test
    public void 튜터_개인정보_조회_테스트_메서드() {

        //given
        int memberNo = 13;
        //when
        TutorInfo tutorInfo = tutorInfoRepository.findById(memberNo).get();

        //then
        assertNotNull(tutorInfo);
        System.out.println("tutorInfo = " + tutorInfo);

    }

    @Test
    public void 전체_칭호_카테고리_정보_조회_테스트_메서드() {

        //given

        //when
        List<Achievement> achievementList = achievementRepository.findAll();

        //when
        assertNotNull(achievementList);
        achievementList.forEach(System.out::println);
    }
}
