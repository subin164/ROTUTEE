package com.greedy.rotutee.lecture.request.service;

import com.greedy.rotutee.lecture.request.dto.LectureDTO;

import java.util.List;

public interface LectureRequestService {
    List<LectureDTO> findLectureListBytutorNo(int memberNo);
}
