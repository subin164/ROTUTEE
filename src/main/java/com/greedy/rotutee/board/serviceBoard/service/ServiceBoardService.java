package com.greedy.rotutee.board.serviceBoard.service;

import com.greedy.rotutee.board.serviceBoard.dto.BoardDTO;
import com.greedy.rotutee.board.serviceBoard.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.service
 * fileName : ServiceBoardService
 * author : 7sang
 * date : 2022-05-04
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-04 7sang 최초 생성
 */

@Service
public class ServiceBoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ServiceBoardService(BoardRepository boardRepository, ModelMapper modelMapper) {
        this.boardRepository = boardRepository;
        this.modelMapper = modelMapper;
    }

    public Page<BoardDTO> findServiceBoardList(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("no").descending());

        int categoryNo = 7;
        char status = 'N';

        return boardRepository.findByBoardCategoryNoAndDeleteYN(categoryNo, status, pageable).map(board -> modelMapper.map(board, BoardDTO.class));
    }

    public Page<BoardDTO> findServiceBoardList2(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("no").descending());

        int categoryNo = 8;
        char status = 'N';

        return boardRepository.findByBoardCategoryNoAndDeleteYN(categoryNo, status, pageable).map(board -> modelMapper.map(board, BoardDTO.class));
    }
}
