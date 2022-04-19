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
    public void 현재_진행중인_모든_강의_조회() {

        //given
        String lectureApprovalStatus = "승인";
        //when
        List<Lecture> lectureList = lectureMainRepository.findBylectureApprovalStatus(lectureApprovalStatus);
        //then
        assertNotNull(lectureList);
        lectureList.forEach(System.out::println);
    }


}
