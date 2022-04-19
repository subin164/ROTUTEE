package com.greedy.rotutee.lecture.lecture.service;

import com.greedy.rotutee.lecture.lecture.dto.LectureDTO;
import com.greedy.rotutee.lecture.lecture.entity.Lecture;
import com.greedy.rotutee.lecture.lecture.repository.LectureMainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureMainServiceImpl implements  LectureMainService{

    private final LectureMainRepository lectureMainRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LectureMainServiceImpl(LectureMainRepository lectureMainRepository, ModelMapper modelMapper) {
        this.lectureMainRepository = lectureMainRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LectureDTO> findAllLecture() {

        String lectureApprovalStatus = "승인";

        List<Lecture> lectureList = lectureMainRepository.findBylectureApprovalStatus(lectureApprovalStatus);

        return lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
    }
}
