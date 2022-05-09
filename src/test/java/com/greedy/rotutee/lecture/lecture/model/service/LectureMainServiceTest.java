package com.greedy.rotutee.lecture.lecture.model.service;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.lecture.lecture.dto.*;
import com.greedy.rotutee.lecture.lecture.entity.Lecture;
import com.greedy.rotutee.lecture.lecture.repository.LectureMainRepository;
import com.greedy.rotutee.lecture.lecture.service.LectureMainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, BeanConfiguration.class, JPAConfiguration.class})
public class LectureMainServiceTest {

    @Autowired
    private LectureMainService lectureMainService;

    @Test
    public void 레포지토리_의존성_테스트() {

        assertNotNull(lectureMainService);
        System.out.println("lectureMainService = " + lectureMainService);
    }

    @Test
    @Transactional
    public void 수강_승인받은_강의_조회_테스트() {
        //given

        //when
        List<LectureDTO> lectureList = lectureMainService.findAllLecture();
        //then
        assertNotNull(lectureList);
    }

    @Test
    @Transactional
    public void 강의_검색_테스트() {
        //given
        int searchCondition1 = 1;
        int searchCondition2 = 2;
        String searchValue1 = "완성";
        String searchValue2 = "이석재";
        //when
        List<LectureDTO> lectureList1 = new ArrayList<>();
        List<LectureDTO> lectureList2 = new ArrayList<>();

        lectureList1 = lectureMainService.findApproveLectureBysearchObject(searchCondition1, searchValue1);
        lectureList2 = lectureMainService.findApproveLectureBysearchObject(searchCondition2, searchValue2);
        //then
        assertNotNull(lectureList1);
        assertNotNull(lectureList2);
    }

    @Test
    @Transactional
    public void 강의번호로_강의_조회_테스트() {
        //given
        int lectureNo = 2;
        //when
        LectureDTO lecture = lectureMainService.findLectureByLectureNo(lectureNo);
        //then
        assertNotNull(lecture);

    }

    @Test
    @Transactional
    public void 강의의_챕터_목록_조회_테스트() {
        //given
        int lectureNo = 2;
        //when
        List<ChapterDTO> chapterList = lectureMainService.findChapterListByLectureNo(lectureNo);
        //then
        assertNotNull(chapterList);
        chapterList.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void 강의의_강의평_목록_조회_테스트() {
        //given
        int lectureNo = 2;
        //when
        List<LectureReviewDTO> reviewList = lectureMainService.findReviewListByLectureNo(lectureNo);
        //then
        assertNotNull(reviewList);
    }

    @Test
    public void 수강인원_수_조회_테스트() {
        //given
        int lectureNo = 2;
        //when
        int count = lectureMainService.findMemberCountByLectureNo(lectureNo);
        //then
        assertEquals(1, count);
    }

    @Test
    public void 강의평_평점_평균_조회_테스트() {
        //given
        int lectureNo = 2;
        //when
        int avg = lectureMainService.findGradeAverageByLectureNo(lectureNo);
        //then
        assertEquals(3, avg);
    }

    @Test
    public void 회원이_강의를_수강중인지_확인_테스트() {
        //given
        int memberNo = 14;
        int lectureNo = 2;
        //when
        MemberLectureDTO memberLecture = lectureMainService.findMemberInLecture(memberNo, lectureNo);
        //then
        assertNotNull(memberLecture);
    }

    @Test
    @Transactional
    public void 강의평_작성_테스트() {
        //given
        int memberNo = 14;
        int rating = 5;
        String content = "강의가 너무 체계적이네요.";
        int lectureNo = 2;
        //when
        lectureMainService.registLectureReviewAndPoint(rating, content, lectureNo, memberNo);
        //then

    }

    @Test
    @Transactional
    public void 강의평_수정_테스트() {
        //given
        int lectureReviewNo = 1;
        String lectureReviewContent = "강의평 테스트입니다.";
        //when
        lectureMainService.modifyReviewContent(lectureReviewNo, lectureReviewContent);
        //then
    }

    @Test
    @Transactional
    public void 강의평_삭제_테스트() {
        //given
        int lectureReviewNo = 1;
        //when
        lectureMainService.removeReview(lectureReviewNo);
        //then
    }
}
