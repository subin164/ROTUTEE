package com.greedy.rotutee.board.FQABoard.repsositrory;


import com.greedy.rotutee.board.FQABoard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
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

@Repository(value = "FQABoard_BoardRepository")
public interface BoardRepository extends JpaRepository<Board, Integer> {

    List<Board> findByBoardCategoryNo(int categoryNo);
}
