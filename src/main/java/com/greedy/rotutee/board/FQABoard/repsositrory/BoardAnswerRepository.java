package com.greedy.rotutee.board.FQABoard.repsositrory;

import com.greedy.rotutee.board.FQABoard.entity.BoardAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.repository
 * fileName : BoardAnswerRepository
 * author : 7sang
 * date : 2022-05-05
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-05 7sang 최초 생성
 */

@Repository(value = "FQABoard_BoardAnswerRepository")
public interface BoardAnswerRepository extends JpaRepository<BoardAnswer, Integer> {

}
