package com.greedy.rotutee.dashboard.mypage.repository;

import com.greedy.rotutee.board.serviceBoard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.repository
 * fileName : DashboardPostRepository
 * author : 7sang
 * date : 2022-05-11
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-11 7sang 최초 생성
 */

public interface DashboardPostRepository extends JpaRepository<Board, Integer> {

    Page<Board> findByMemberNo(int memberNo, Pageable pageable);
}
