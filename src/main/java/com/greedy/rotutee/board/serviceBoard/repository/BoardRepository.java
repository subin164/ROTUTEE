package com.greedy.rotutee.board.serviceBoard.repository;

import com.greedy.rotutee.board.serviceBoard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.repository
 * fileName : BoardRepository
 * author : 7sang
 * date : 2022-05-04
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-04 7sang 최초 생성
 */

@Repository(value = "ServiceBoard_BoardRepository")
public interface BoardRepository extends JpaRepository<Board, Integer> {

    List<Board> findByBoardCategoryNoAndDeleteYN(int categoryNo, char status);

    Page<Board> findByBoardCategoryNoAndDeleteYN(int categoryNo, char status, Pageable pageable);

//    @Query("select DISTINCT a from ServiceBoard_SerivceBoard a join fetch a.boardAnswerList b where a.no = :boardNo and b.answerYn = :answerYn")
//    Board findByNoAndBoardAnswerListAnswerYn(int boardNo, char answerYn);

    Page<Board> findByBoardCategoryNoAndDeleteYNAndMemberNameContaining(int categoryNo, char status, String searchValue, Pageable pageable);

    Page<Board> findByBoardCategoryNoAndDeleteYNAndTitleContaining(int categoryNo, char status, String searchValue, Pageable pageable);

    Page<Board> findByBoardCategoryNoAndDeleteYNAndContentContaining(int categoryNo, char status, String searchValue, Pageable pageable);

    @Query("select DISTINCT a from ServiceBoard_SerivceBoard a left join fetch a.boardCategory where a.no = :boardNo")
    Board findByNo(int boardNo);
}
