package com.greedy.rotutee.main.service;

import com.greedy.rotutee.main.dto.LectureDTO;
import com.greedy.rotutee.main.entity.Lecture;
import com.greedy.rotutee.main.repository.MainLectureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName      : com.greedy.rotutee.main.model.service
 * fileName         : MainServiceImpl
 * author           : SEOK
 * date             : 2022-05-04
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-04      SEOK         최초 생성
 */
@Service
public class MainServiceImpl implements MainService{

    private final ModelMapper modelMapper;
    private final MainLectureRepository mainLectureRepository;

    @Autowired
    public MainServiceImpl(ModelMapper modelMapper, MainLectureRepository mainLectureRepository) {
        this.modelMapper = modelMapper;
        this.mainLectureRepository = mainLectureRepository;
    }

    @Override
    public List<LectureDTO> findRecentLectureList() {

        List<Lecture> recentLectureList = mainLectureRepository.findRecentLectureList();

        return recentLectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
    }
}
