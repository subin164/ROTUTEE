package com.greedy.rotutee.lecture.request.model.repository;


import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.lecture.request.entity.Lecture;
import com.greedy.rotutee.lecture.request.entity.Member;
import com.greedy.rotutee.lecture.request.repository.RequestLectureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * packageName      : com.greedy.rotutee.lecture.request.model.repository
 * fileName         : RequestLectureRepositoryTest
 * author           : SEOK
 * date             : 2022-05-02
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-02      SEOK         최초 생성
 */
@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, BeanConfiguration.class, JPAConfiguration.class})
public class RequestLectureRepositoryTest {

    @Autowired
    private RequestLectureRepository requestLectureRepository;

    @Test
    public void 레포지토리_의존성_테스트() {

        assertNotNull(requestLectureRepository);
        System.out.println("requestLectureRepository = " + requestLectureRepository);
    }

    @Test
    public void 멤버_객체로_강의_조회_테스트() {
        //given
        Member tutor = new Member();
        tutor.setNo(13);
        //when
        List<Lecture> lectureList = requestLectureRepository.findByTutor(tutor);
        //then
        assertNotNull(lectureList);
    }

    @Test
    public void 대기_상태_강의_조회_테스트() {
        //given
        String status = "대기";
        //when
        List<Lecture> lectureList = requestLectureRepository.findByLectureApprovalStatus(status);
        //then
        assertNotNull(lectureList);
    }

    @Test
    public void 대기_상태가_아닌_강의_조회() {
        //given
        String status1 = "승인";
        String status2 = "거절";
        //when
        List<Lecture> lectureList = requestLectureRepository.findByLectureApprovalStatusOrLectureApprovalStatus(status1, status2);
        //then
        assertNotNull(lectureList);
    }

    @Test
    public void 강의_번호로_강의_조회_테스트() {
        //given
        int lectureNo = 7;
        //when
        Lecture lecture = requestLectureRepository.findByLectureNo(lectureNo);
        //then
        assertNotNull(lecture);
    }
}
