package com.greedy.rotutee.lecture.lecture.service;

import com.greedy.rotutee.lecture.lecture.dto.ChapterDTO;
import com.greedy.rotutee.lecture.lecture.dto.LectureDTO;

import java.util.List;

public interface LectureMainService {
    List<LectureDTO> findAllLecture();

    List<LectureDTO> findApproveLectureBysearchObject(int searchCondition, String searchValue);

    LectureDTO findLectureByLectureNo(int lectureNo);

    List<ChapterDTO> findChapterListByLectureNo(int lectureNo);
}
