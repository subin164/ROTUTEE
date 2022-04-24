package com.greedy.rotutee.lecture.lecture.model.repository;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.lecture.lecture.entity.Lecture;
import com.greedy.rotutee.lecture.lecture.repository.LectureMainRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, BeanConfiguration.class, JPAConfiguration.class})
public class LectureMainRepositoryTest {

    @Autowired
    private LectureMainRepository lectureMainRepository;

    @Test
    public void 레포지토리_의존성_테스트() {

        assertNotNull(lectureMainRepository);
        System.out.println("lectureMainRepository = " + lectureMainRepository);
    }

    @Test
    public void 현재_진행중인_모든_강의_조회_테스트() {

        //given
        String lectureApprovalStatus = "승인";
        //when
        List<Lecture> lectureList = lectureMainRepository.findBylectureApprovalStatus(lectureApprovalStatus);
        //then
        assertNotNull(lectureList);
        lectureList.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void 강의_이름_검색_테스트() {

        //given
        String lectureName = "완성";
        //when
        List<Lecture> lectureList = lectureMainRepository.findBylectureNameContaining(lectureName);
        //then
        assertNotNull(lectureList);
        lectureList.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void 튜터_이름_검색_테스트() {
        //given
        String name = "이석재";
        //when
        List<Lecture> lectureList = lectureMainRepository.findLecturesByTutorName(name);
        //then
        assertNotNull(lectureList);
        lectureList.forEach(System.out::println);
    }
}
