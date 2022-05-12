package com.greedy.rotutee.lecture.lecture.model.repository;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.lecture.lecture.repository.MemberLectureMainRepository;
import com.greedy.rotutee.lecture.lecture.entity.MemberLecture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, BeanConfiguration.class, JPAConfiguration.class})
public class MemberLectureMainRepositoryTest {

    @Autowired
    private MemberLectureMainRepository memberLectureMainRepository;

    @Test
    public void 레포지토리_의존성_테스트() {

        assertNotNull(memberLectureMainRepository);
        System.out.println("memberLectureMainRepository = " + memberLectureMainRepository);
    }

    @Test
    public void 강의_수강생_인원_조회() {
        //given
        int lectureNo = 2;
        //when
        int memberCount = memberLectureMainRepository.countByLectureNo(lectureNo);
        //then
        assertEquals(1, memberCount);
    }

    @Test
    public void 수강_여부_확인_테스트() {
        //given
        int memberNo = 14;
        int lectureNO = 2;
        //when
        MemberLecture memberLecture = memberLectureMainRepository.findByMemberNoAndLectureNo(memberNo, lectureNO);
        //then
        assertNotNull(memberLecture);
    }
}
