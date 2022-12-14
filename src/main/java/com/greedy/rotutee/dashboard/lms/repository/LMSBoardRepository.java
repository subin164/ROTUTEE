package com.greedy.rotutee.dashboard.lms.repository;

import com.greedy.rotutee.dashboard.lms.entity.LMSBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.repository
 * fileName : LMSDashboardRepository
 * author : SeoYoung
 * date : 2022-04-26
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-26 SeoYoung 최초 생성
 */
public interface LMSBoardRepository extends JpaRepository<LMSBoard, Integer> {

    List<LMSBoard> findByCategoryNoOrderByBoardNoDesc(int categoryNo);

    List<LMSBoard> findByCategoryNoAndLectureNoOrderByBoardNoDesc(int categoryNo, int lectureNo);
}
