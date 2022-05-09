package com.greedy.rotutee.lecture.lecture.repository;

import com.greedy.rotutee.lecture.lecture.entity.Member;
import com.greedy.rotutee.lecture.lecture.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName      : com.greedy.rotutee.lecture.lecture.repository
 * fileName         : LecturePointHistoryRepository
 * author           : SEOK
 * date             : 2022-05-09
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-09      SEOK         최초 생성
 */
@Repository
public interface LecturePointHistoryRepository extends JpaRepository<PointHistory, Integer> {

    List<PointHistory> findByMemberOrderByHistoryNoDesc(Member memberEntity);
}
