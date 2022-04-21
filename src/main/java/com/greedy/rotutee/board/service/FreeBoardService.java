package com.greedy.rotutee.board.service;

import com.greedy.rotutee.board.dto.FreeBoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

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

   List<FreeBoardDTO> findByBoardCategoryNo(int categoryNo);

   void registNewFreeBoard(FreeBoardDTO newFreeBoard);
}
