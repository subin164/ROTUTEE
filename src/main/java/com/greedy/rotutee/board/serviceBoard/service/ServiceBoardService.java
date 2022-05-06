package com.greedy.rotutee.board.serviceBoard.service;

import com.greedy.rotutee.board.serviceBoard.dto.BoardAnswerDTO;
import com.greedy.rotutee.board.serviceBoard.dto.BoardCategoryDTO;
import com.greedy.rotutee.board.serviceBoard.dto.BoardDTO;
import com.greedy.rotutee.board.serviceBoard.dto.MemberDTO;
import com.greedy.rotutee.board.serviceBoard.entity.Board;
import com.greedy.rotutee.board.serviceBoard.entity.BoardAnswer;
import com.greedy.rotutee.board.serviceBoard.repository.BoardAnswerRepository;
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

import java.util.List;
import java.util.stream.Collectors;

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
    private final BoardAnswerRepository boardAnswerRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    @Autowired
    public ServiceBoardService(BoardRepository boardRepository, BoardCategoryRepository boardCategoryRepositroy, BoardAnswerRepository boardAnswerRepository, ModelMapper modelMapper, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.boardCategoryRepositroy = boardCategoryRepositroy;
        this.boardAnswerRepository = boardAnswerRepository;
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

    @Transactional
    public BoardDTO findBoardByBoardNo(int boardNo) {

        Board detailBoard = boardRepository.findById(boardNo).get();

        detailBoard.setViewCount(detailBoard.getViewCount() + 1);

        return modelMapper.map(detailBoard, BoardDTO.class);
    }

    public void registAnswer(BoardAnswerDTO boardAnswer) {

        boardAnswerRepository.save(modelMapper.map(boardAnswer, BoardAnswer.class));
    }

    public BoardAnswerDTO findAnswerByAnswerNo(int answerNo) {

        return modelMapper.map(boardAnswerRepository.findByNo(answerNo), BoardAnswerDTO.class);
    }

    @Transactional
    public void modifyAnswerContent(BoardAnswerDTO boardAnswer) {

        boardAnswerRepository.save(modelMapper.map(boardAnswer, BoardAnswer.class));
    }

    @Transactional
    public void removeAnswer(int boardAnswer) {

        BoardAnswer removeBoardAnswer = boardAnswerRepository.findById(boardAnswer).get();
        removeBoardAnswer.setAnswerYn('Y');
    }

    public Page<BoardDTO> findSearchServiceBoardList(String searchValue, String searchCondition, Pageable pageable) {

        int categoryNo = 7;
        char status = 'N';

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("no").descending());

        Page<Board> searchBoardList = null;

        if (searchCondition.equals("member")) {
            searchBoardList = boardRepository.findByBoardCategoryNoAndDeleteYNAndMemberNameContaining(categoryNo, status, searchValue, pageable);
        } else if(searchCondition.equals("title")) {
            searchBoardList = boardRepository.findByBoardCategoryNoAndDeleteYNAndTitleContaining(categoryNo, status, searchValue ,pageable);
        } else if(searchCondition.equals("content")) {
            searchBoardList = boardRepository.findByBoardCategoryNoAndDeleteYNAndContentContaining(categoryNo, status, searchValue, pageable);
        }

        return searchBoardList.map(board -> modelMapper.map(board, BoardDTO.class));
    }

    @Transactional
    public void removeServiceBoard(int boardNo) {

        Board removeBoard = boardRepository.findById(boardNo).get();
        removeBoard.setDeleteYN('Y');
    }

    @Transactional
    public void modifyServiceBoard(BoardDTO modifyBoard) {

        boardRepository.save(modelMapper.map(modifyBoard, Board.class));
    }

    public List<BoardAnswerDTO> findBoardAnswerList(int boardNo) {

        List<BoardAnswer> boardAnswerList = boardAnswerRepository.findByBoardNo(boardNo);

        return boardAnswerList.stream().map(boardAnswer -> modelMapper.map(boardAnswer, BoardAnswerDTO.class)).collect(Collectors.toList());
    }
}
