package com.greedy.rotutee.board.freeboard.service;

import com.greedy.rotutee.board.freeboard.dto.FreeBoardAnswerDTO;
import com.greedy.rotutee.board.freeboard.dto.FreeBoardDTO;
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

   Page<FreeBoardDTO> findSearchBoardList(Pageable pageable, int categoryNo, String searchValue, String searchCondition);

   FreeBoardDTO selectBoardDetail(int boardNo);

   void deleteFreeBoard(int boardNo);

   FreeBoardDTO selectBoardModify(int boardNo);

   void modifyBoard(FreeBoardDTO freeBoard);

   void registNewFreeBoard(FreeBoardDTO newFreeBoard);

   void insertAnswer(FreeBoardAnswerDTO registAnswer);

   void deleteAnswer(int answerNo);

   void updateAnswer(FreeBoardAnswerDTO modifyAnswer);


}
