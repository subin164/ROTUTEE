package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.LMSChapterDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSClassDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSQuizDTO;
import com.greedy.rotutee.dashboard.lms.dto.LecturePlayDTO;
import com.greedy.rotutee.dashboard.lms.entity.LMSChapter;
import com.greedy.rotutee.dashboard.lms.entity.LMSClass;
import com.greedy.rotutee.dashboard.lms.entity.LMSQuiz;
import com.greedy.rotutee.dashboard.lms.repository.LMSChapterRepository;
import com.greedy.rotutee.dashboard.lms.repository.LMSClassRepository;
import com.greedy.rotutee.dashboard.lms.repository.LMSQuizRepository;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardLecture;
import com.greedy.rotutee.dashboard.mypage.repository.DashboardLectureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.service
 * fileName : LMSServiceImpl
 * author : SeoYoung
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 SeoYoung 최초 생성
 */
@Service
public class LMSLearningServiceImpl implements LMSLearningService {

    private LMSChapterRepository lmsChapterRepository;
    private DashboardLectureRepository lectureRepository;
    private LMSClassRepository lmsClassRepository;
    private LMSQuizRepository lmsQuizRepository;
    private ModelMapper modelMapper;

    public LMSLearningServiceImpl(LMSChapterRepository lmsChapterRepository, DashboardLectureRepository lectureRepository, LMSClassRepository lmsClassRepository, LMSQuizRepository lmsQuizRepository, ModelMapper modelMapper) {
        this.lmsChapterRepository = lmsChapterRepository;
        this.lectureRepository = lectureRepository;
        this.lmsClassRepository = lmsClassRepository;
        this.lmsQuizRepository = lmsQuizRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LecturePlayDTO findLecturePlay(int lectureNo) {

        /*강의 소개*/
        DashboardLecture lectureEntity = lectureRepository.findByLectureNo(lectureNo);
        String lectureSummary = lectureEntity.getSummary();

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
                LMSQuiz quizEntity = lmsQuizRepository.findByClassNo(classNo);
                LMSQuizDTO quiz = modelMapper.map(quizEntity, LMSQuizDTO.class);
                lectureClasses.get(j).setQuiz(quiz);
            }
        }

        LecturePlayDTO lecturePlay = new LecturePlayDTO();
        lecturePlay.setLectureSummary(lectureSummary);
        lecturePlay.setChapters(chapters);

        return lecturePlay;
    }
}
