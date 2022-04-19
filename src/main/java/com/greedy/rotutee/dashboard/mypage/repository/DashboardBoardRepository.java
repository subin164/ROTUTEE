package com.greedy.rotutee.dashboard.mypage.repository;

import com.greedy.rotutee.dashboard.mypage.entity.DashboardBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.board.repository
 * fileName : Board
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */


public interface DashboardBoardRepository extends JpaRepository<DashboardBoard, Integer> {

    List<DashboardBoard> findBymemberNo(int memberNo);
}
