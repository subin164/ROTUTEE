package com.greedy.rotutee.lecture.lecture.service;

import com.greedy.rotutee.lecture.lecture.dto.LectureDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LectureMainService {
    List<LectureDTO> findAllLecture();
}
