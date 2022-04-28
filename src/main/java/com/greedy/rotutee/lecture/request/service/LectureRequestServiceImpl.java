package com.greedy.rotutee.lecture.request.service;

import com.greedy.rotutee.lecture.request.dto.*;
import com.greedy.rotutee.lecture.request.entity.AttachedFile;
import com.greedy.rotutee.lecture.request.entity.Lecture;
import com.greedy.rotutee.lecture.request.entity.LectureCategory;
import com.greedy.rotutee.lecture.request.entity.Member;
import com.greedy.rotutee.lecture.request.repository.RequestAttachedFileRepository;
import com.greedy.rotutee.lecture.request.repository.RequestLectureCategoryRepository;
import com.greedy.rotutee.lecture.request.repository.RequestLectureRepository;
import com.greedy.rotutee.lecture.request.repository.RequestMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureRequestServiceImpl implements LectureRequestService{

    private final ModelMapper modelMapper;
    private final RequestLectureRepository requestLectureRepository;
    private final RequestMemberRepository requestMemberRepository;
    private final RequestAttachedFileRepository requestAttachedFileRepository;
    private final RequestLectureCategoryRepository requestLectureCategoryRepository;

    @Autowired
    public LectureRequestServiceImpl(ModelMapper modelMapper, RequestLectureRepository requestLectureRepository, RequestMemberRepository requestMemberRepository, RequestAttachedFileRepository requestAttachedFileRepository, RequestLectureCategoryRepository requestLectureCategoryRepository) {
        this.modelMapper = modelMapper;
        this.requestLectureRepository = requestLectureRepository;
        this.requestMemberRepository = requestMemberRepository;
        this.requestAttachedFileRepository = requestAttachedFileRepository;
        this.requestLectureCategoryRepository = requestLectureCategoryRepository;
    }

    @Override
    public List<LectureDTO> findLectureListBytutorNo(int memberNo) {

        Member tutor = requestMemberRepository.findByNo(memberNo);

        List<Lecture> lectureList = requestLectureRepository.findByTutor(tutor);

        List<LectureDTO> lectureDTOList = lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
        for(LectureDTO lecture : lectureDTOList) {
            String deletion = "N";
            String division = "강의";
            Lecture lectureEntity = modelMapper.map(lecture, Lecture.class);
            AttachedFile file = requestAttachedFileRepository.findByLectureAndFileDeletionYNAndDivision(lectureEntity, deletion, division);

            if(file != null) {
            AttachedFileDTO fileDTO = modelMapper.map(file, AttachedFileDTO.class);
            }
        }

        return lectureDTOList;
    }

    @Override
    @Transactional
    public void registLectureOpeningApplication(LectureDTO newLecture, int categoryNo, int memberNo) {

        Member tutor = requestMemberRepository.findByNo(memberNo);
        MemberDTO tutorDTO = modelMapper.map(tutor, MemberDTO.class);

        LectureCategory category = requestLectureCategoryRepository.findByLectureCategoryNo(categoryNo);
        LectureCategoryDTO categoryDTO = modelMapper.map(category, LectureCategoryDTO.class);

        newLecture.setTutor(tutorDTO);
        newLecture.setCategory(categoryDTO);
        newLecture.setLectureApprovalStatus("대기");
        newLecture.setApplicationDivision("신청");
        newLecture.setApplicationDate(new Date(System.currentTimeMillis()));

        List<ChapterDTO> chapterList = newLecture.getChapterList();
            for(ChapterDTO chapter : chapterList) {
                chapter.setLecture(newLecture);

            List<ClassDTO> classDTOList = chapter.getClassList();
            for(ClassDTO classDTO : classDTOList) {
                    classDTO.setVideoPath("www.naver.com");
                    classDTO.setChapter(chapter);

                List<QuizDTO> quizList = classDTO.getQuizList();
                    for(QuizDTO quiz : quizList) {
                        quiz.setClassEntity(classDTO);
                        quiz.setQuizType("객관식");
                    }
                }
            }


        requestLectureRepository.save(modelMapper.map(newLecture, Lecture.class));


    }

    @Override
    public List<LectureDTO> findStatusOfLectureIsWaiting() {

        String status = "대기";
        List<Lecture> lectureList = requestLectureRepository.findByLectureApprovalStatus(status);

        return lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<LectureDTO> findStatusOfLectureIsNotWaiting() {

        String status1 = "승인";
        String status2 = "거절";
        List<Lecture> lectureList = requestLectureRepository.findByLectureApprovalStatusOrLectureApprovalStatus(status1, status2);

        return lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
    }

    @Override
    public LectureDTO findLectureByLectureNo(int lectureNo) {

        Lecture lectuer = requestLectureRepository.findByLectureNo(lectureNo);

        return modelMapper.map(lectuer, LectureDTO.class);
    }

    @Override
    @Transactional
    public void modifyLectureApprovalStatus(int lectureNo) {

        Lecture lecture = requestLectureRepository.findByLectureNo(lectureNo);
        lecture.setLectureApprovalStatus("승인");
    }

}
