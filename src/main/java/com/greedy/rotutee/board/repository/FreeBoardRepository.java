package com.greedy.rotutee.board.repository;

import com.greedy.rotutee.board.entity.FreeBoard;

import com.greedy.rotutee.board.entity.FreeBoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.board.repository
 * fileName : FreeBoardRepository
 * author : soobeen
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-20          soobeen     최초 생성
 */

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer> {

     List<FreeBoard> findByBoardCategoryNo(FreeBoardCategory categoryNo);
}

