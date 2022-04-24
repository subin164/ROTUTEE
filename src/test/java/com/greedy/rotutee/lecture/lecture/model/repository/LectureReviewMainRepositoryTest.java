package com.greedy.rotutee.lecture.lecture.model.repository;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.lecture.lecture.repository.LectureReviewMainRepository;
import com.greedy.rotutee.lecture.lecture.entity.LectureReview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, BeanConfiguration.class, JPAConfiguration.class})
public class LectureReviewMainRepositoryTest {

    @Autowired
    private LectureReviewMainRepository lectureReviewMainRepository;

    @Test
    public void 레포지토리_의존성_테스트() {

        assertNotNull(lectureReviewMainRepository);
        System.out.println("lectureReviewMainRepository = " + lectureReviewMainRepository);
    }

    @Test
    @Transactional
    public void 강의상세_강의평_조회_테스트() {

        //given
        int lectureNo = 2;
        //when
        List<LectureReview> lectureReviewList = lectureReviewMainRepository.findLectureReviewByLectureNoAndLectureReviewRemoveYN(lectureNo);
        //then
        assertNotNull(lectureReviewList);
        lectureReviewList.forEach(System.out::println);
    }

    @Test
    public void 강의_평점_평균_조회_테스트() {
        //given
        int lectreNo = 2;
        //when
        int avg = lectureReviewMainRepository.findfindGradeAverageByLectureNo(lectreNo);
        //then
        assertEquals(3, avg);
    }
}
