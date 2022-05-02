package com.greedy.rotutee.lecture.request.repository;

import com.greedy.rotutee.lecture.request.entity.LectureRequestProcessingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName      : com.greedy.rotutee.lecture.request.repository
 * fileName         : RequestLectureRequestProcessingHistoryRepository
 * author           : SEOK
 * date             : 2022-05-02
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-02      SEOK         최초 생성
 */
public interface RequestLectureRequestProcessingHistoryRepository extends JpaRepository<LectureRequestProcessingHistory, Integer> {
}
