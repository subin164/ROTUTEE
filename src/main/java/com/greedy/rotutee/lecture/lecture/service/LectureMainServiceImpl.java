package com.greedy.rotutee.lecture.lecture.service;

import com.greedy.rotutee.lecture.lecture.dto.ChapterDTO;
import com.greedy.rotutee.lecture.lecture.dto.LectureDTO;
import com.greedy.rotutee.lecture.lecture.entity.Chapter;
import com.greedy.rotutee.lecture.lecture.entity.Lecture;
import com.greedy.rotutee.lecture.lecture.repository.ChapterRepository;
import com.greedy.rotutee.lecture.lecture.repository.LectureMainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureMainServiceImpl implements  LectureMainService{

    private final LectureMainRepository lectureMainRepository;
    private final ModelMapper modelMapper;
    private final ChapterRepository chapterRepository;

    @Autowired
    public LectureMainServiceImpl(LectureMainRepository lectureMainRepository, ModelMapper modelMapper, ChapterRepository chapterRepository) {
        this.lectureMainRepository = lectureMainRepository;
        this.modelMapper = modelMapper;
        this.chapterRepository = chapterRepository;
    }

    @Override
    public List<LectureDTO> findAllLecture() {

        String lectureApprovalStatus = "승인";

        List<Lecture> lectureList = lectureMainRepository.findBylectureApprovalStatus(lectureApprovalStatus);

        return lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<LectureDTO> findApproveLectureBysearchObject(int searchCondition, String searchValue) {

        List<Lecture> lectureList = new ArrayList<>();

        if(searchCondition == 1) {
            lectureList = lectureMainRepository.findBylectureNameContaining(searchValue);

        } else if(searchCondition == 2) {
            lectureList = lectureMainRepository.findLecturesByTutorName(searchValue);
        }

        return lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
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
}
