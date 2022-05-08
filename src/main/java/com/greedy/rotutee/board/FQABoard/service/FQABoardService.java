package com.greedy.rotutee.board.FQABoard.service;

import com.greedy.rotutee.board.FQABoard.dto.BoardDTO;
import com.greedy.rotutee.board.FQABoard.entity.Board;
import com.greedy.rotutee.board.FQABoard.repsositrory.BoardCategoryRepository;
import com.greedy.rotutee.board.FQABoard.repsositrory.BoardRepository;
import com.greedy.rotutee.board.FQABoard.repsositrory.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
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
    private final BoardCategoryRepository boardCategoryRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    @Autowired
    public FQABoardService(BoardRepository boardRepository, BoardCategoryRepository boardCategoryRepository, ModelMapper modelMapper, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.boardCategoryRepository = boardCategoryRepository;
        this.modelMapper = modelMapper;
        this.memberRepository = memberRepository;
    }

    public List<BoardDTO> findAllFQABoardList(int categoryNo) {

        List<Board> boardList = boardRepository.findByBoardCategoryNoAndDeleteYN(categoryNo, 'N');

        return boardList.stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public void modifyFQABoard(BoardDTO board) {

        Board modifyBoard = boardRepository.findById(board.getNo()).get();
        modifyBoard.setTitle(board.getTitle());
        modifyBoard.setContent(board.getContent());
        modifyBoard.setModifiedDate(new Date(System.currentTimeMillis()));
    }

    @Transactional
    public void removeFQABoard(int boardNo) {

        Board removeBoard = boardRepository.findById(boardNo).get();

        removeBoard.setDeleteYN('Y');
        removeBoard.setDeletionDate(new Date(System.currentTimeMillis()));
    }

    @Transactional
    public void registFQABoard(BoardDTO board, int categoryNo, int adminNo) {

        Board registBoard = modelMapper.map(board, Board.class);
        registBoard.setBoardCategory(boardCategoryRepository.findById(categoryNo).get());
        registBoard.setMember(memberRepository.findById(adminNo).get());

        System.out.println("registBoard = " + registBoard);

        boardRepository.save(registBoard);
    }
}
