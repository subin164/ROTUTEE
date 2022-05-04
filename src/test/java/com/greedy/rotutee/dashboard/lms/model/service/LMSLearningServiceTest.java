package com.greedy.rotutee.dashboard.lms.model.service;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.dashboard.lms.dto.LMSChapterDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSClassDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSQuizDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.model.service
 * fileName : LMSServiceTest
 * author : SeoYoung
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 SeoYoung 최초 생성
 */
@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class LMSLearningServiceTest {

    @Autowired
    private LMSChapterRepository lmsChapterRepository;
    @Autowired
    private LMSClassRepository lmsClassRepository;
    @Autowired
    private LMSQuizRepository lmsQuizRepository;
    @Autowired
    private MypageMemberLectureRepository memberLectureRepository;
    @Autowired
    private LMSSubmissionQuizRepository lmsSubmissionQuizRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Test
    public void init() {
        assertNotNull(lmsChapterRepository);
    }
    
    @Test
    public void 챕터_조회_확인() {

        //given
        int lectureNo = 6;
        //when
        List<LMSChapter> chapters = lmsChapterRepository.findByLectureNo(lectureNo);
        //then
        assertNotNull(chapters);
        for (LMSChapter chapter : chapters) {
            System.out.println("chapter = " + chapter);
        }

    }

    @Test
    public void 클래스_조회_확인() {
        //given
        int lectureNo = 6;

        //when
        List<LMSChapter> chaptersEntity = lmsChapterRepository.findByLectureNo(lectureNo);
        List<LMSChapterDTO> chapters = chaptersEntity.stream().map(Lms_Chapter -> modelMapper.map(Lms_Chapter, LMSChapterDTO.class)).collect(Collectors.toList());

        for(int i = 0; i < chapters.size(); i++){
            int chapterNo = chapters.get(i).getChapterNo();
            List<LMSClass> lectureClassesEntity = lmsClassRepository.findByChapterChapterNoOrderByChapterChapterNoAsc(chapterNo);
            List<LMSClassDTO> lectureClasses = lectureClassesEntity.stream().map(Lms_Class -> modelMapper.map(Lms_Class, LMSClassDTO.class)).collect(Collectors.toList());
            chapters.get(i).setClassesList(lectureClasses);
        }

        for (LMSChapterDTO chapter : chapters) {
            System.out.println("chapter = " + chapter);
        }

    }

    @Test
    public void 퀴즈_조회_확인() {
        //given
        int lectureNo = 6;
        int memberNo = 27;

        //when
        List<LMSChapter> chaptersEntity = lmsChapterRepository.findByLectureNo(lectureNo);
        List<LMSChapterDTO> chapters = chaptersEntity.stream().map(Lms_Chapter -> modelMapper.map(Lms_Chapter, LMSChapterDTO.class)).collect(Collectors.toList());


        for(int i = 0; i < chapters.size(); i++){
            int chapterNo = chapters.get(i).getChapterNo();
            List<LMSClass> lectureClassesEntity = lmsClassRepository.findByChapterChapterNoOrderByChapterChapterNoAsc(chapterNo);
            List<LMSClassDTO> lectureClasses = lectureClassesEntity.stream().map(Lms_Class -> modelMapper.map(Lms_Class, LMSClassDTO.class)).collect(Collectors.toList());
            chapters.get(i).setClassesList(lectureClasses);

            for(int j = 0; j < lectureClassesEntity.size(); j++){
                int classNo = lectureClassesEntity.get(j).getClassNo();
                LMSQuiz quizEntity = lmsQuizRepository.findByClassNoOrderByQuizNo(classNo);
                LMSQuizDTO quiz = modelMapper.map(quizEntity, LMSQuizDTO.class);

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

        //then
        for(int i = 0; i < chapters.size(); i++){
            List<LMSClassDTO> classList = chapters.get(i).getClassesList();
            for(int j =0; j < classList.size(); j++) {
                System.out.println(classList.get(j).getQuiz());
            }
        }

    }

    @Test
    public void 퀴즈_제출_확인() {
        int quizNo = 50;

        String quizSubmissionStatus = "";

        if(lmsSubmissionQuizRepository.findByQuizNo(quizNo) != null) {
            quizSubmissionStatus = "Y";
        } else {
            quizSubmissionStatus = "N";
        }

        System.out.println(quizSubmissionStatus);
    }

}
