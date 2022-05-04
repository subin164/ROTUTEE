package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.*;
import com.greedy.rotutee.dashboard.lms.entity.LMSChapter;
import com.greedy.rotutee.dashboard.lms.entity.LMSClass;
import com.greedy.rotutee.dashboard.lms.entity.LMSQuiz;
import com.greedy.rotutee.dashboard.lms.entity.LMSSubmissionQuiz;
import com.greedy.rotutee.dashboard.lms.repository.LMSChapterRepository;
import com.greedy.rotutee.dashboard.lms.repository.LMSClassRepository;
import com.greedy.rotutee.dashboard.lms.repository.LMSQuizRepository;
import com.greedy.rotutee.dashboard.lms.repository.LMSSubmissionQuizRepository;
import com.greedy.rotutee.dashboard.mypage.entity.MyPageMemberLecture;
import com.greedy.rotutee.dashboard.mypage.repository.MypageMemberLectureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

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
    private LMSChapterRepository lmsChapterRepository;
    private LMSClassRepository lmsClassRepository;
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public LMSQUizServiceImpl(LMSQuizRepository lmsQuizRepository, ModelMapper modelMapper, MypageMemberLectureRepository memberLectureRepository, LMSSubmissionQuizRepository lmsSubmissionQuizRepository, LMSChapterRepository lmsChapterRepository, LMSClassRepository lmsClassRepository) {
        this.lmsQuizRepository = lmsQuizRepository;
        this.memberLectureRepository = memberLectureRepository;
        this.modelMapper = modelMapper;
        this.lmsSubmissionQuizRepository = lmsSubmissionQuizRepository;
        this.lmsChapterRepository = lmsChapterRepository;
        this.lmsClassRepository = lmsClassRepository;
    }


    @Override
    public LMSQuizDTO findQuizDetail(int quizNo) {

        LMSQuiz quizDetailEntity = lmsQuizRepository.findById(quizNo).get();
        LMSQuizDTO quizDetail = modelMapper.map(quizDetailEntity, LMSQuizDTO.class);

        return quizDetail;
    }
    @Override
    public boolean confirmQuiz(int quizNo) {

        if(lmsSubmissionQuizRepository.findByQuizNo(quizNo) != null) {
            return true; //퀴즈를 풀어서 제출했다
        } else {
            return false; //퀴즈를 풀지 않은 상태이다
        }

    }

    @Override
    @Transactional
    public boolean gradingQuiz(int answer, int memberNo, int quizNo, int lectureNo) {

        LMSQuiz quizInfo = lmsQuizRepository.findByQuizNo(quizNo);
        int correctAnswer = quizInfo.getAnswer();
        String result = "";
        boolean gradingResult = false;
        LMSSubmissionQuiz lmsSubmissonQuiz = new LMSSubmissionQuiz();
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

    /**
     * methodName : findQuizStatus
     * author : SeoYoung Kim
     * description :
     *
     * @param memberNo
     * @param lectureNo
     * @return lmsQuizStatusDTO
     */
    @Override
    public LMSQuizStatusDTO findQuizStatus(int memberNo, int lectureNo) {
        LMSQuizStatusDTO quizStatusDTO = new LMSQuizStatusDTO();

        /*강의의 챕터 정보*/
        List<LMSChapter> chaptersEntity = lmsChapterRepository.findByLectureNo(lectureNo);
        List<LMSChapterDTO> chapters = chaptersEntity.stream().map(Lms_Chapter -> modelMapper.map(Lms_Chapter, LMSChapterDTO.class)).collect(Collectors.toList());

        /* 회원별 수강번호 */
        MyPageMemberLecture memberLectureEntity = memberLectureRepository.findByLectureLectureNoAndMemberMemberNo(lectureNo, memberNo);
        int memberLectureNo = memberLectureEntity.getMemberLectureNo();

        /* 제출한 퀴즈 채점 정보 */
        List<LMSSubmissionQuiz> lmsSubmissionQuizEntities = lmsSubmissionQuizRepository.findByMemberLectureNoOrderByQuizNoAsc(memberLectureNo);
        List<LMSSubmissionQuizDTO> submissionQuizs = lmsSubmissionQuizEntities.stream().map(Lms_SubmissionQuiz -> modelMapper.map(Lms_SubmissionQuiz, LMSSubmissionQuizDTO.class)).collect(Collectors.toList());
//        System.out.println("submissionQuizs = " + submissionQuizs);
        /*강의의 챕터 별 클래스 정보, 클래스 별 퀴즈 정보*/
        for(int i = 0; i < chapters.size(); i++){
            int chapterNo = chapters.get(i).getChapterNo();
            List<LMSClass> lectureClassesEntity = lmsClassRepository.findByChapterChapterNoOrderByChapterChapterNoAsc(chapterNo);
            List<LMSClassDTO> lectureClasses = lectureClassesEntity.stream().map(Lms_Class -> modelMapper.map(Lms_Class, LMSClassDTO.class)).collect(Collectors.toList());
            chapters.get(i).setClassesList(lectureClasses);

            for(int j = 0; j < lectureClassesEntity.size(); j++){
                int classNo = lectureClassesEntity.get(j).getClassNo();
                LMSQuiz quizEntity = lmsQuizRepository.findByClassNoOrderByQuizNo(classNo);
                LMSQuizDTO quiz = modelMapper.map(quizEntity, LMSQuizDTO.class);

                /* 퀴즈를 제출했는지 안했는지 여부*/
                String quizSubmissionStatus = "";
                int quizNo = quiz.getQuizNo();
                if(lmsSubmissionQuizRepository.findByQuizNo(quizNo) != null) {
                    quizSubmissionStatus = "Y";
                } else {
                    quizSubmissionStatus = "N";
                }

                quiz.setSubmissionStatus(quizSubmissionStatus);

                lectureClasses.get(j).setQuiz(quiz);
            }
        }

        for(int k = 0; k < chapters.size(); k++) {
            List<LMSClassDTO> classList = chapters.get(k).getClassesList();
            for(int j = 0; j < classList.size(); j++) {
                LMSClassDTO LMSclass = classList.get(j);
                for(int g = 0; g < submissionQuizs.size(); g++) {
                    if(submissionQuizs.get(g).getQuizNo() == LMSclass.getQuiz().getQuizNo()) {
                        String answerStatus = submissionQuizs.get(g).getAnswerStatus();
                        LMSclass.getQuiz().setCorrectStatus(answerStatus);
                    }
                }
            }
        }

        /* 전체 퀴즈 중 제출한 퀴즈의 개수, 제출한 퀴즈 중 정답개수 */
        List<LMSSubmissionQuiz> lmsSubmissonQuizEntities = lmsSubmissionQuizRepository.findByMemberLectureNo(memberLectureNo);
        int submissionCount = lmsSubmissonQuizEntities.size();
        int correctCount = 0;

        for(int i = 0; i < lmsSubmissonQuizEntities.size(); i++){
            String answerStatus = lmsSubmissonQuizEntities.get(i).getAnswerStatus();
            if(answerStatus.equals("Y ")){
                correctCount += 1;
            }
        }

        quizStatusDTO.setSubmissionCount(submissionCount);
        quizStatusDTO.setCorrectCount(correctCount);
        quizStatusDTO.setChapters(chapters);

        return quizStatusDTO;
    }

    @Override
    public List<LMSSubmissionQuizDTO> findSubmissionQuiz(int memberNo, int lectureNo) {

        MyPageMemberLecture memberLectureEntity = memberLectureRepository.findByLectureLectureNoAndMemberMemberNo(lectureNo, memberNo);
        int memberLectureNo = memberLectureEntity.getMemberLectureNo();

        List<LMSSubmissionQuiz> submissionQuizEntities =  lmsSubmissionQuizRepository.findByMemberLectureNoOrderByQuizNo(memberLectureNo);
        List<LMSSubmissionQuizDTO> submissionQuizs = submissionQuizEntities.stream().map(Lms_SubmissionQuiz -> modelMapper.map(Lms_SubmissionQuiz, LMSSubmissionQuizDTO.class)).collect(Collectors.toList());

        return submissionQuizs;
    }

}
