package com.greedy.rotutee.lecture.request.model.service;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.lecture.request.dto.LectureDTO;
import com.greedy.rotutee.lecture.request.service.LectureRequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName      : com.greedy.rotutee.lecture.request.model.service
 * fileName         : LectureRequestServiceTest
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
public class LectureRequestServiceTest {

    @Autowired
    private LectureRequestService lectureRequestService;

    @Test
    public void 레포지토리_의존성_테스트() {

        assertNotNull(lectureRequestService);
        System.out.println("lectureRequestService = " + lectureRequestService);
    }

    @Test
    public void 회원_번호로_튜터의_강의_목록_조회_테스트() {
        //given
        int memberNo = 13;
        //when
        List<LectureDTO> lectureList = lectureRequestService.findLectureListBytutorNo(memberNo);
        //then
        assertNotNull(lectureList);
    }

//    @Test
//    public void 승인_대기_상태의_강의_목록_조회_테스트() {
//        //given
//
//        //when
//        Page<LectureDTO> lectureList = lectureRequestService.findStatusOfLectureIsWaiting();
//        //then
//        assertNotNull(lectureList);
//    }
}
