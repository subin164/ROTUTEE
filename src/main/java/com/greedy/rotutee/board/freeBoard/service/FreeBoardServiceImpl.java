package com.greedy.rotutee.board.freeBoard.service;


import com.greedy.rotutee.board.freeBoard.dto.FreeBoardAnswerDTO;
import com.greedy.rotutee.board.freeBoard.dto.FreeBoardDTO;
import com.greedy.rotutee.board.freeBoard.entity.FreeBoard;
import com.greedy.rotutee.board.freeBoard.entity.FreeBoardAnswer;
import com.greedy.rotutee.board.freeBoard.entity.FreeBoardCategory;
import com.greedy.rotutee.board.freeBoard.entity.FreeBoardMember;
import com.greedy.rotutee.board.freeBoard.repository.FreeBoardCategoryRepository;
import com.greedy.rotutee.board.freeBoard.repository.FreeBoardRepository;
import com.greedy.rotutee.board.freeBoard.repository.FreeBoardAnswerRepository;
import com.greedy.rotutee.board.freeBoard.repository.FreeBoardMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : com.greedy.rotutee.board.service
 * fileName : FreeBoardServiceImpl
 * author : soobeen
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-20          soobeen     최초 생성
 */

@Service
public class FreeBoardServiceImpl implements FreeBoardService{

    private FreeBoardCategoryRepository categoryRepository;
    private FreeBoardRepository freeBoardRepository;
    private FreeBoardMemberRepository memberRepository;
    private FreeBoardAnswerRepository freeBoardAnswerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public FreeBoardServiceImpl(FreeBoardAnswerRepository answerRepository ,FreeBoardCategoryRepository categoryRepository, FreeBoardRepository freeBoardRepository, FreeBoardMemberRepository memberRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.freeBoardRepository = freeBoardRepository;
        this.memberRepository = memberRepository;
        this.freeBoardAnswerRepository = answerRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public Page<FreeBoardDTO> findCategoryBoardList(Pageable pageable, int categoryNo) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,                        // pageing 정보들을 담아
                pageable.getPageSize(),
                Sort.by("boardNo").descending());

        FreeBoardCategory freeBoardCategory = categoryRepository.findById(categoryNo).get();
        List<FreeBoard> freeBoard = (List<FreeBoard>) freeBoardRepository.findByFreeBoardCategoryAndBoardDeleteYN(freeBoardCategory,'Y',pageable);

        return (Page<FreeBoardDTO>) freeBoard.stream().map(FreeBoard -> modelMapper.map(FreeBoard, FreeBoardDTO.class)).collect(Collectors.toList());
    }
    @Override
    public Page<FreeBoardDTO> findSearchBoardList(Pageable pageable, int categoryNo, String searchValue, String searchCondition) {
        return null;
    }

    @Override

    public FreeBoardDTO selectBoardDetail(int boardNo){

        FreeBoard freeBoard = freeBoardRepository.findById(boardNo).get();
        freeBoard.setBoardViewCount(freeBoard.getBoardViewCount() + 1);
        freeBoardRepository.save(freeBoard);

        FreeBoardDTO boardDTO = modelMapper.map(freeBoard, FreeBoardDTO.class);

        return boardDTO;
    }

    @Override
    public FreeBoardDTO selectBoardModify(int boardNo) {
        FreeBoard freeBoard = freeBoardRepository.findById(boardNo).get();
        FreeBoardDTO boardDTO = modelMapper.map(freeBoard, FreeBoardDTO.class);

        return boardDTO;
    }

    @Override
    @Transactional
    public void modifyBoard(FreeBoardDTO freeBoard) {

        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        FreeBoardCategory freeBoardCategory = categoryRepository.findById(freeBoard.getFreeBoardCategory().getBoardCategoryNo()).get();

        FreeBoard modifyBoard = freeBoardRepository.findById(freeBoard.getBoardNo()).get();
        modifyBoard.setBoardTitle(freeBoard.getBoardTitle());
        modifyBoard.setBoardContent(freeBoard.getBoardContent());
        modifyBoard.setBoardModifiedDate(sqlDate);
        modifyBoard.setFreeBoardCategory(freeBoardCategory);
        modifyBoard.setBulletinBoardSecretYN(freeBoard.getBulletinBoardSecretYN());
        freeBoardRepository.save(modifyBoard);
    }

    @Override
    @Transactional
    public void registNewFreeBoard(FreeBoardDTO freeBoard) {

        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        FreeBoardMember freeBoardMember = modelMapper.map(freeBoard.getFreeBoardMember(),FreeBoardMember.class);

        FreeBoardCategory freeBoardCategory = modelMapper.map(freeBoard.getFreeBoardCategory(),FreeBoardCategory.class);

        FreeBoard newBoard = new FreeBoard();

        newBoard.setBoardTitle(freeBoard.getBoardTitle());
        newBoard.setBoardContent(freeBoard.getBoardContent());
        newBoard.setBoardCreationDate(sqlDate);
        newBoard.setBoardViewCount(0);
        newBoard.setFreeBoardCategory(freeBoardCategory);
        newBoard.setFreeBoardMember(freeBoardMember);
        newBoard.setBoardReportCount(0);
        newBoard.setBulletinBoardSecretYN(freeBoard.getBulletinBoardSecretYN());

        freeBoardRepository.save(newBoard);
    }

    @Override
    public void deleteFreeBoard(int boardNo) {
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        FreeBoard deleteBoard = freeBoardRepository.findById(boardNo).get();
        deleteBoard.setBoardDeleteYN('Y');
        deleteBoard.setBoardDeletionDate(sqlDate);
        freeBoardRepository.save(deleteBoard);

    }

    @Override
    public void deleteAnswer(int answerNo) {
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        FreeBoardAnswer deleteAnswer = freeBoardAnswerRepository.findById(answerNo).get();
        deleteAnswer.setAnswerYN('Y');
        deleteAnswer.setAnswerDeleteDate(sqlDate);
        freeBoardAnswerRepository.save(deleteAnswer);
    }

    @Override
    public void updateAnswer(FreeBoardAnswerDTO modifyAnswer) {
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        FreeBoardMember freeBoardMember = modelMapper.map(modifyAnswer.getFreeBoardMember(),FreeBoardMember.class);

        FreeBoard freeBoard = modelMapper.map(modifyAnswer.getFreeBoard(),FreeBoard.class);

        FreeBoardAnswer freeBoardAnswer = freeBoardAnswerRepository.findById(modifyAnswer.getAnswerNo()).get();
        freeBoardAnswer.setAnswerContent(modifyAnswer.getAnswerContent());
        freeBoardAnswer.setAnswerYN(modifyAnswer.getAnswerYN());
        freeBoardAnswer.setAnswerReportCount(modifyAnswer.getAnswerReportCount());
        freeBoardAnswer.setAnswerModifyDate(sqlDate);
        freeBoardAnswer.setFreeBoardMember(freeBoardMember);
        freeBoardAnswer.setFreeBoard(freeBoard);

        freeBoardAnswerRepository.save(freeBoardAnswer);

    }

    @Override
    public void insertAnswer(FreeBoardAnswerDTO registAnswer) {

        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        FreeBoard freeBoard = modelMapper.map(registAnswer.getFreeBoard(),FreeBoard.class);
        FreeBoardMember freeBoardMember =  modelMapper.map(registAnswer.getFreeBoardMember(),FreeBoardMember.class);

        FreeBoardAnswer newRegistAnswer = new FreeBoardAnswer();

        newRegistAnswer.setAnswerContent(registAnswer.getAnswerContent());
        newRegistAnswer.setAnswerNo('N');
        newRegistAnswer.setAnswerReportCount(0);
        newRegistAnswer.setAnswerCreatedDate(sqlDate);
        newRegistAnswer.setFreeBoard(freeBoard);
        newRegistAnswer.setFreeBoardMember(freeBoardMember);

        freeBoardAnswerRepository.save(newRegistAnswer);
    }

}
