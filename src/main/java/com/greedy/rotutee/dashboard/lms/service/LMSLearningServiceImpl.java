package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.LMSChapterDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSClassDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSQuizDTO;
import com.greedy.rotutee.dashboard.lms.dto.LecturePlayDTO;
import com.greedy.rotutee.dashboard.lms.entity.LMSChapter;
import com.greedy.rotutee.dashboard.lms.entity.LMSClass;
import com.greedy.rotutee.dashboard.lms.entity.LMSLatelyViewClass;
import com.greedy.rotutee.dashboard.lms.entity.LMSQuiz;
import com.greedy.rotutee.dashboard.lms.repository.*;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardLecture;
import com.greedy.rotutee.dashboard.mypage.entity.MyPageMemberLecture;
import com.greedy.rotutee.dashboard.mypage.repository.DashboardLectureRepository;
import com.greedy.rotutee.dashboard.mypage.repository.MypageMemberLectureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
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
    private LMSSubmissionQuizRepository lmsSubmissionQuizRepository;
    private LMSLatelyViewRepository lmsLatelyViewRepository;
    private MypageMemberLectureRepository memberLectureRepository;
    private ModelMapper modelMapper;

    public LMSLearningServiceImpl(LMSChapterRepository lmsChapterRepository, DashboardLectureRepository lectureRepository, LMSClassRepository lmsClassRepository, LMSQuizRepository lmsQuizRepository, LMSSubmissionQuizRepository lmsSubmissionQuizRepository, LMSLatelyViewRepository lmsLatelyViewRepository, MypageMemberLectureRepository memberLectureRepository, ModelMapper modelMapper) {
        this.lmsChapterRepository = lmsChapterRepository;
        this.lectureRepository = lectureRepository;
        this.lmsClassRepository = lmsClassRepository;
        this.lmsQuizRepository = lmsQuizRepository;
        this.lmsSubmissionQuizRepository = lmsSubmissionQuizRepository;
        this.lmsLatelyViewRepository = lmsLatelyViewRepository;
        this.memberLectureRepository = memberLectureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LecturePlayDTO findLecturePlay(int lectureNo, int memberNo) {

        /*강의 소개*/
        DashboardLecture lectureEntity = lectureRepository.findByLectureNo(lectureNo);
        String lectureSummary = lectureEntity.getSummary();

        /*memberLectureNo 정보*/
        MyPageMemberLecture memberLectureEntity = memberLectureRepository.findByLectureLectureNoAndMemberMemberNo(lectureNo, memberNo);
        int memberLectureNo = memberLectureEntity.getMemberLectureNo();

        /*강의의 챕터 정보*/
        List<LMSChapter> chaptersEntity = lmsChapterRepository.findByLectureNoOrderByChapterNoAsc(lectureNo);
        List<LMSChapterDTO> chapters = chaptersEntity.stream().map(Lms_Chapter -> modelMapper.map(Lms_Chapter, LMSChapterDTO.class)).collect(Collectors.toList());

        /*강의의 챕터 별 클래스 정보, 클래스 별 퀴즈 정보*/
        for (int i = 0; i < chapters.size(); i++) {
            int chapterNo = chapters.get(i).getChapterNo();
            List<LMSClass> lectureClassesEntity = lmsClassRepository.findByChapterChapterNoOrderByClassNoAsc(chapterNo);
            List<LMSClassDTO> lectureClasses = lectureClassesEntity.stream().map(Lms_Class -> modelMapper.map(Lms_Class, LMSClassDTO.class)).collect(Collectors.toList());
            System.out.println("lectureClasses = " + lectureClasses);
            chapters.get(i).setClassesList(lectureClasses);

            for (int j = 0; j < lectureClassesEntity.size(); j++) {
                int classNo = lectureClassesEntity.get(j).getClassNo();
                LMSLatelyViewClass latelyViewClassEntity = lmsLatelyViewRepository.findByLmsClassClassNo(classNo);
                String completedStatus = latelyViewClassEntity.getCompletedStatus();
                int timeNo = latelyViewClassEntity.getTimeNo();
                LMSQuiz quizEntity = lmsQuizRepository.findByClassNoOrderByQuizNo(classNo);
                LMSQuizDTO quiz = modelMapper.map(quizEntity, LMSQuizDTO.class);

                lectureClasses.get(j).setWhatcingStatus(completedStatus);
                lectureClasses.get(j).setTimeNo(timeNo);

                String quizSubmissionStatus = "";
                int quizNo = quiz.getQuizNo();
                if (lmsSubmissionQuizRepository.findByQuizNo(quizNo) != null) {
                    quizSubmissionStatus = "Y";
                } else {
                    quizSubmissionStatus = "N";
                }
                quiz.setSubmissionStatus(quizSubmissionStatus);

                lectureClasses.get(j).setQuiz(quiz);
            }
        }
        for (LMSChapterDTO chapter : chapters) {
            System.out.println("chapter = " + chapter);
        }

        LecturePlayDTO lecturePlay = new LecturePlayDTO();
        lecturePlay.setLectureSummary(lectureSummary);
        lecturePlay.setChapters(chapters);

        return lecturePlay;
    }

    @Override
    @Transactional
    public void modifyVideoWatchingStatus(int timeNo, String status) {

        LMSLatelyViewClass latelyViewClassEntity = lmsLatelyViewRepository.findById(timeNo).get();
        latelyViewClassEntity.setCompletedStatus(status);
        lmsLatelyViewRepository.save(latelyViewClassEntity);

    }
}
