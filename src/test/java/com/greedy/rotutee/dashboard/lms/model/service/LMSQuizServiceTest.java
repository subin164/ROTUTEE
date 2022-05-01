package com.greedy.rotutee.dashboard.lms.model.service;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.dashboard.lms.dto.LMSQuizDTO;
import com.greedy.rotutee.dashboard.lms.entity.LMSQuiz;
import com.greedy.rotutee.dashboard.lms.entity.LMSSubmissonQuiz;
import com.greedy.rotutee.dashboard.lms.repository.LMSQuizRepository;
import com.greedy.rotutee.dashboard.lms.repository.LMSSubmissionQuizRepository;
import com.greedy.rotutee.dashboard.mypage.entity.MyPageMemberLecture;
import com.greedy.rotutee.dashboard.mypage.repository.MypageMemberLectureRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.model.service
 * fileName : LMSQuizServiceTest
 * author : SeoYoung
 * date : 2022-04-29
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-29 SeoYoung 최초 생성
 */
@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class LMSQuizServiceTest {

    @Autowired
    private LMSQuizRepository lmsQuizRepository;
    @Autowired
    private MypageMemberLectureRepository memberLectureRepository;
    @Autowired
    private LMSSubmissionQuizRepository lmsSubmissionQuizRepository;
    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void init() {
        assertNotNull(lmsQuizRepository);
        assertNotNull(modelMapper);
    }

    @Test
    public void findByQUizNo퀴즈_조회() {

        //given
        int quizNo = 1;

        //when
        LMSQuiz quizDetailEntity = lmsQuizRepository.findById(quizNo).get();
        LMSQuizDTO quizDetail = modelMapper.map(quizDetailEntity, LMSQuizDTO.class);

        //then
        System.out.println("quizDetail = " + quizDetail);
    }

    @Test
    @Transactional
    public void 제출_정답_채점() {
        //given
        int memberNo = 27;
        int quizNo = 2;
        int answer = 2;
        int lectureNo = 6;
        int wrongAnswer = 3;

        //when
        LMSQuiz quizInfo = lmsQuizRepository.findByQuizNo(quizNo);
        int correctAnswer = quizInfo.getAnswer();
        String result = "";
        boolean gradingResult = false;
        LMSSubmissonQuiz lmsSubmissonQuiz = new LMSSubmissonQuiz();
        MyPageMemberLecture memberLecture = memberLectureRepository.findByMemberMemberNoAndLectureLectureNo(memberNo, lectureNo);
        if(answer == correctAnswer) {
            result = "Y";
            gradingResult = true;
        } else {
            result = "N";
        }
        lmsSubmissonQuiz.setAnswerStatus(result);
        lmsSubmissonQuiz.setMemberLectureNo(memberLecture.getMemberLectureNo());
        lmsSubmissonQuiz.setQuizNo(quizNo);
        lmsSubmissionQuizRepository.save(lmsSubmissonQuiz);
        }

}
