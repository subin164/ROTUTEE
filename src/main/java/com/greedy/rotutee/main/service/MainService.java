package com.greedy.rotutee.main.service;

import com.greedy.rotutee.main.dto.LectureDTO;

import java.util.List;

/**
 * packageName      : com.greedy.rotutee.main.controller
 * fileName         : MainService
 * author           : SEOK
 * date             : 2022-05-04
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-04      SEOK         최초 생성
 */
public interface MainService {
    List<LectureDTO> findRecentLectureList();

    List<LectureDTO> findPopularLectureList();
}
