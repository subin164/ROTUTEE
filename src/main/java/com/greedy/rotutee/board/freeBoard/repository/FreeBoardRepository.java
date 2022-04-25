package com.greedy.rotutee.board.freeBoard.repository;

import com.greedy.rotutee.board.freeBoard.entity.FreeBoard;

import com.greedy.rotutee.board.freeBoard.entity.FreeBoardCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer> {

     List<FreeBoard> findByfreeBoardCategory(FreeBoardCategory freeBoardCategory);

     List<FreeBoard> findByFreeBoardCategoryAndBoardDeleteYN(FreeBoardCategory freeBoardCategory, char boardDeleteYN, Pageable pageable);
}

