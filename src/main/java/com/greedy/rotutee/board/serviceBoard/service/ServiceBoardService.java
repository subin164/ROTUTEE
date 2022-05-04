package com.greedy.rotutee.board.serviceBoard.service;

import com.greedy.rotutee.board.serviceBoard.dto.BoardCategoryDTO;
import com.greedy.rotutee.board.serviceBoard.dto.BoardDTO;
import com.greedy.rotutee.board.serviceBoard.dto.MemberDTO;
import com.greedy.rotutee.board.serviceBoard.entity.Board;
import com.greedy.rotutee.board.serviceBoard.repository.BoardCategoryRepository;
import com.greedy.rotutee.board.serviceBoard.repository.BoardRepository;
import com.greedy.rotutee.board.serviceBoard.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final BoardCategoryRepository boardCategoryRepositroy;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    @Autowired
    public ServiceBoardService(BoardRepository boardRepository, BoardCategoryRepository boardCategoryRepositroy, ModelMapper modelMapper, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.boardCategoryRepositroy = boardCategoryRepositroy;
        this.modelMapper = modelMapper;
        this.memberRepository = memberRepository;
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

    public BoardCategoryDTO findCategoryByNo(int categoryNo) {

        return modelMapper.map(boardCategoryRepositroy.findById(categoryNo), BoardCategoryDTO.class);
    }

    @Transactional
    public void registServiceBoard(BoardDTO board) {

        boardRepository.save(modelMapper.map(board, Board.class));
    }

    public MemberDTO findMemberByNo(int memberNo) {

        return modelMapper.map(memberRepository.findById(memberNo), MemberDTO.class);
    }
}
