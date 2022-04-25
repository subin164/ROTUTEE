package com.greedy.rotutee.board.freeBoard.service;

import com.greedy.rotutee.board.freeBoard.dto.FreeBoardAnswerDTO;
import com.greedy.rotutee.board.freeBoard.dto.FreeBoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : com.greedy.rotutee.board.service
 * fileName : FreeBoardService
 * author : soobeen
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-20          soobeen     최초 생성
 */

@Service
public interface FreeBoardService {

   Page<FreeBoardDTO> findCategoryBoardList(Pageable pageable, int categoryNo);

   FreeBoardDTO selectBoardDetail(int boardNo);

   FreeBoardDTO selectBoardModify(int boardNo);

   void modifyBoard(FreeBoardDTO freeBoard);

   void registNewFreeBoard(FreeBoardDTO newFreeBoard);

   void deleteFreeBoard(int boardNo);

   void deleteAnswer(int answerNo);

   void updateAnswer(FreeBoardAnswerDTO modifyAnswer);

   void insertAnswer(FreeBoardAnswerDTO registAnswer);

   Page<FreeBoardDTO> findSearchBoardList(Pageable pageable, int categoryNo, String searchValue, String searchCondition);
}
