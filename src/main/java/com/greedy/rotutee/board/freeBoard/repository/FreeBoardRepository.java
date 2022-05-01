package com.greedy.rotutee.board.freeBoard.repository;

import com.greedy.rotutee.board.freeBoard.entity.FreeBoard;

import com.greedy.rotutee.board.freeBoard.entity.FreeBoardCategory;
import com.sun.xml.bind.v2.schemagen.episode.SchemaBindings;
import org.springframework.data.domain.Page;
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


    Page<FreeBoard> findByFreeBoardCategoryAndBoardDeleteYN(FreeBoardCategory freeBoardCategory, char boardDeleteYN, Pageable pageable);

    Page<FreeBoard> findByBoardTitleContainingAndFreeBoardCategoryAndBoardDeleteYN(String searchValue, FreeBoardCategory freeBoardCategory, char boardDeleteYN, Pageable pageable);

    Page<FreeBoard> findByBoardContentContainingAndFreeBoardCategoryAndBoardDeleteYN(String searchValue, FreeBoardCategory freeBoardCategory, char boardDeleteYN, Pageable pageable);

    Page<FreeBoard> findByFreeBoardMemberMemberNameContainingAndFreeBoardCategoryAndBoardDeleteYN(String searchValue, FreeBoardCategory freeBoardCategory, char boardDeleteYN, Pageable pageable);

    Page<FreeBoard> findByBoardDeleteYN(char boardDeleteYN, Pageable pageable);
}

