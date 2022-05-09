package com.greedy.rotutee.board.freeboard.service.repository;

import com.greedy.rotutee.board.freeboard.entity.FreeBoardAnswer;
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
