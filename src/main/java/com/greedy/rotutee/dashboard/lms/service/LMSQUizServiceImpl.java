package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.LMSQuizDTO;
import com.greedy.rotutee.dashboard.lms.entity.LMSQuiz;
import com.greedy.rotutee.dashboard.lms.entity.LMSSubmissonQuiz;
import com.greedy.rotutee.dashboard.lms.repository.LMSQuizRepository;
import com.greedy.rotutee.dashboard.lms.repository.LMSSubmissionQuizRepository;
import com.greedy.rotutee.dashboard.mypage.entity.MyPageMemberLecture;
import com.greedy.rotutee.dashboard.mypage.repository.MypageMemberLectureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.service
 * fileName : LMSQUizServiceImpl
 * author : SeoYoung
 * date : 2022-04-29
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-29 SeoYoung 최초 생성
 */
@Service
public class LMSQUizServiceImpl implements LMSQuizService{

    private LMSQuizRepository lmsQuizRepository;
    private MypageMemberLectureRepository memberLectureRepository;
    private LMSSubmissionQuizRepository lmsSubmissionQuizRepository;
    private ModelMapper modelMapper;

    @Autowired
    public LMSQUizServiceImpl(LMSQuizRepository lmsQuizRepository, ModelMapper modelMapper, MypageMemberLectureRepository memberLectureRepository, LMSSubmissionQuizRepository lmsSubmissionQuizRepository) {
        this.lmsQuizRepository = lmsQuizRepository;
        this.memberLectureRepository = memberLectureRepository;
        this.modelMapper = modelMapper;
        this.lmsSubmissionQuizRepository = lmsSubmissionQuizRepository;
    }


    @Override
    public LMSQuizDTO findQuizDetail(int quizNo) {

        LMSQuiz quizDetailEntity = lmsQuizRepository.findById(quizNo).get();
        LMSQuizDTO quizDetail = modelMapper.map(quizDetailEntity, LMSQuizDTO.class);

        return quizDetail;
    }

    @Override
    @Transactional
    public boolean gradingQuiz(int answer, int memberNo, int quizNo, int lectureNo) {

        LMSQuiz quizInfo = lmsQuizRepository.findByQuizNo(quizNo);
        int correctAnswer = quizInfo.getAnswer();
        String result = "";
        boolean gradingResult = false;
        LMSSubmissonQuiz lmsSubmissonQuiz = new LMSSubmissonQuiz();
        System.out.println("answer = " + answer);
        System.out.println("correctAnswer = " + correctAnswer);
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

        return gradingResult;
    }
}
