package com.greedy.rotutee.board.freeBoard.repository;

import com.greedy.rotutee.board.freeBoard.entity.FreeBoard;
import com.greedy.rotutee.board.freeBoard.entity.FreeBoardAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.board.repository
 * fileName : FreeBoardAnswerRepository
 * author : soobeen
 * date : 2022-04-24
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-24          soobeen     최초 생성
 */

public interface FreeBoardAnswerRepository extends JpaRepository<FreeBoardAnswer, Integer> {

    List<FreeBoardAnswer> findByFreeBoardBoardNoAndAnswerYN(int boardNo, char answerStatus);
}
