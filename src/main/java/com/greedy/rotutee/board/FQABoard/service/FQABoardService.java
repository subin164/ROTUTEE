package com.greedy.rotutee.board.FQABoard.service;

import com.greedy.rotutee.board.FQABoard.dto.BoardDTO;
import com.greedy.rotutee.board.FQABoard.entity.Board;
import com.greedy.rotutee.board.FQABoard.repsositrory.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : com.greedy.rotutee.board.FQABoard.service
 * fileName : FQABoardService
 * author : 7sang
 * date : 2022-05-06
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-06 7sang 최초 생성
 */

@Service
public class FQABoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FQABoardService(BoardRepository boardRepository, ModelMapper modelMapper) {
        this.boardRepository = boardRepository;
        this.modelMapper = modelMapper;
    }

    public List<BoardDTO> findAllFQABoardList(int categoryNo) {

        List<Board> boardList = boardRepository.findByBoardCategoryNo(categoryNo);

        return boardList.stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
    }
}
