package com.greedy.rotutee.dashboard.lms.model.service;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.dashboard.lms.dto.LMSChapterDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSClassDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSQuizDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSQuizStatusDTO;
import com.greedy.rotutee.dashboard.lms.entity.LMSChapter;
import com.greedy.rotutee.dashboard.lms.entity.LMSClass;
import com.greedy.rotutee.dashboard.lms.entity.LMSQuiz;
import com.greedy.rotutee.dashboard.lms.entity.LMSSubmissonQuiz;
import com.greedy.rotutee.dashboard.lms.repository.LMSChapterRepository;
import com.greedy.rotutee.dashboard.lms.repository.LMSClassRepository;
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

import java.util.List;
import java.util.stream.Collectors;

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
    private LMSChapterRepository lmsChapterRepository;
    @Autowired
    private LMSClassRepository lmsClassRepository;
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
    @Test
    public void 퀴즈_풀었는지_확인() {
        //given
        int quizNo = 2;
        boolean result = false;

        //when
        if(lmsSubmissionQuizRepository.findByQuizNo(quizNo) != null) {
            result = true;
        } else {
            result = false; //퀴즈를 풀지 않은 상태이다
        }

        //then
        System.out.println("result = " + result);
    }

    @Test
    public void 퀴즈_현황() {
        //given

        int lectureNo = 6;
        int memberNo = 27;

        //when
        LMSQuizStatusDTO quizStatusDTO = new LMSQuizStatusDTO();

        /*강의의 챕터 정보*/
        List<LMSChapter> chaptersEntity = lmsChapterRepository.findByLectureNo(lectureNo);
        List<LMSChapterDTO> chapters = chaptersEntity.stream().map(Lms_Chapter -> modelMapper.map(Lms_Chapter, LMSChapterDTO.class)).collect(Collectors.toList());

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

        /* 회원별 수강번호 */
        MyPageMemberLecture memberLectureEntity = memberLectureRepository.findByLectureLectureNoAndMemberMemberNo(lectureNo, memberNo);
        int memberLectureNo = memberLectureEntity.getMemberLectureNo();

        /* 전체 퀴즈 중 제출한 퀴즈의 개수, 제출한 퀴즈 중 정답개수 */
        List<LMSSubmissonQuiz> lmsSubmissonQuizEntities = lmsSubmissionQuizRepository.findByMemberLectureNo(memberLectureNo);
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

        //then
        System.out.println("submissionCount = " + submissionCount);
        System.out.println("correctCount = " + correctCount);
        for (LMSChapterDTO chapter : chapters) {
            System.out.println("chapter = " + chapter);
        }


    }




}
