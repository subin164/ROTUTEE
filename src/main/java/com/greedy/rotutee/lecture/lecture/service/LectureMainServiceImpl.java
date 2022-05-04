package com.greedy.rotutee.lecture.lecture.service;

import com.greedy.rotutee.lecture.lecture.dto.*;
import com.greedy.rotutee.lecture.lecture.entity.*;
import com.greedy.rotutee.lecture.lecture.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureMainServiceImpl implements  LectureMainService{

    private final LectureMainRepository lectureMainRepository;
    private final ModelMapper modelMapper;
    private final ChapterRepository chapterRepository;
    private final LectureReviewMainRepository lectureReviewMainRepository;
    private final MemberLectureMainRepository memberLectureMainRepository;
    private final LectureAttachedFileRepository lectureAttachedFileRepository;

    @Autowired
    public LectureMainServiceImpl(LectureMainRepository lectureMainRepository, ModelMapper modelMapper, ChapterRepository chapterRepository, LectureReviewMainRepository lectureReviewMainRepository, MemberLectureMainRepository memberLectureMainRepository, LectureAttachedFileRepository lectureAttachedFileRepository) {
        this.lectureMainRepository = lectureMainRepository;
        this.modelMapper = modelMapper;
        this.chapterRepository = chapterRepository;
        this.lectureReviewMainRepository = lectureReviewMainRepository;
        this.memberLectureMainRepository = memberLectureMainRepository;
        this.lectureAttachedFileRepository = lectureAttachedFileRepository;
    }

    @Override
    public List<LectureDTO> findAllLecture() {

        String lectureApprovalStatus = "승인";

        List<Lecture> lectureList = lectureMainRepository.findBylectureApprovalStatus(lectureApprovalStatus);

        List<LectureDTO> lectureDTOList = lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
        for(LectureDTO lecture : lectureDTOList) {
            lecture.setThumbnailPath(lectureAttachedFileRepository.findThumbnailPathBylectureNo(lecture.getLectureNo()));
        }

        return lectureDTOList;
    }

    @Override
    public List<LectureDTO> findApproveLectureBysearchObject(int searchCondition, String searchValue) {

        List<Lecture> lectureList = null;
        List<LectureDTO> lectureDTOList = null;
        if(searchCondition == 1) {
            lectureList = lectureMainRepository.findBylectureNameContaining(searchValue);
            lectureDTOList = lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
            for(LectureDTO lecture : lectureDTOList) {
                lecture.setThumbnailPath(lectureAttachedFileRepository.findThumbnailPathBylectureNo(lecture.getLectureNo()));
            }
        } else if(searchCondition == 2) {
            lectureList = lectureMainRepository.findLecturesByTutorName(searchValue);
            lectureDTOList = lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
            for(LectureDTO lecture : lectureDTOList) {
                lecture.setThumbnailPath(lectureAttachedFileRepository.findThumbnailPathBylectureNo(lecture.getLectureNo()));
            }
        }

        return lectureDTOList;
    }

    @Override
    public LectureDTO findLectureByLectureNo(int lectureNo) {

        Lecture lecture = lectureMainRepository.findById(lectureNo).get();

        return modelMapper.map(lecture, LectureDTO.class);
    }

    @Override
    public List<ChapterDTO> findChapterListByLectureNo(int lectureNo) {

        List<Chapter> chapterList = chapterRepository.findByLectureNo(lectureNo);

        return chapterList.stream().map(chapter -> modelMapper.map(chapter, ChapterDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<LectureReviewDTO> findReviewListByLectureNo(int lectureNo) {

        List<LectureReview> lectureReviewList = lectureReviewMainRepository.findLectureReviewByLectureNoAndLectureReviewRemoveYN(lectureNo);

        return lectureReviewList.stream().map(lectureReview -> modelMapper.map(lectureReview, LectureReviewDTO.class)).collect(Collectors.toList());
    }

    @Override
    public int findMemberCountByLectureNo(int lectureNo) {

        int result = memberLectureMainRepository.countByLectureNo(lectureNo);

        return result;
    }

    @Override
    public int findGradeAverageByLectureNo(int lectureNo) {

        int result = 0;
        Integer avg = lectureReviewMainRepository.findfindGradeAverageByLectureNo(lectureNo);

        if(avg != null) {
            result = avg;
        }

        return result;
    }

    @Override
    public MemberLectureDTO findMemberInLecture(int memberNo, int lectureNo) {

        MemberLecture history = memberLectureMainRepository.findByMemberNoAndLectureNo(memberNo, lectureNo);

        if(history == null) {
            return null;
        }

        return modelMapper.map(history, MemberLectureDTO.class);
    }

    @Override
    @Transactional
    public void registLectureReview(int rating, String content, int lectureNo, int memberNo) {

        MemberDTO writer = new MemberDTO();
        writer.setNo(memberNo);

        LectureReviewDTO lectureReview = new LectureReviewDTO();
        lectureReview.setLectureGrade(rating);
        lectureReview.setLectureReviewContent(content);
        lectureReview.setLectureReviewDate(new Date(System.currentTimeMillis()));
        lectureReview.setLectureReviewRemoveYN("N");
        lectureReview.setLectureNo(lectureNo);
        lectureReview.setWriter(writer);

        lectureReviewMainRepository.save(modelMapper.map(lectureReview, LectureReview.class));

    }

    @Override
    @Transactional
    public void modifyReviewContent(int lectureReviewNo, String lectureReviewContent) {

        LectureReview foundReview = lectureReviewMainRepository.findById(lectureReviewNo).get();
        foundReview.setLectureReviewContent(lectureReviewContent);

    }

    @Override
    @Transactional
    public void removeReview(int lectureReviewNo) {

        String status = "Y";

        LectureReview foundReview = lectureReviewMainRepository.findById(lectureReviewNo).get();
        foundReview.setLectureReviewRemoveYN(status);

    }
}
