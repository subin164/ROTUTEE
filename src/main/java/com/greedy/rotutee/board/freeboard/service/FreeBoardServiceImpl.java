package com.greedy.rotutee.board.freeboard.service;


import com.greedy.rotutee.board.freeboard.dto.FreeBoardAnswerDTO;
import com.greedy.rotutee.board.freeboard.dto.FreeBoardDTO;
import com.greedy.rotutee.board.freeboard.entity.FreeBoard;
import com.greedy.rotutee.board.freeboard.entity.FreeBoardAnswer;
import com.greedy.rotutee.board.freeboard.entity.FreeBoardCategory;
import com.greedy.rotutee.board.freeboard.entity.FreeBoardMember;
import com.greedy.rotutee.board.freeboard.service.repository.FreeBoardCategoryRepository;
import com.greedy.rotutee.board.freeboard.service.repository.FreeBoardRepository;
import com.greedy.rotutee.board.freeboard.service.repository.FreeBoardAnswerRepository;
import com.greedy.rotutee.board.freeboard.service.repository.FreeBoardMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

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
    private FreeBoardAnswerRepository answerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public FreeBoardServiceImpl(FreeBoardAnswerRepository answerRepository ,FreeBoardCategoryRepository categoryRepository,
                                FreeBoardRepository freeBoardRepository, FreeBoardMemberRepository memberRepository,
                                ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.freeBoardRepository = freeBoardRepository;
        this.memberRepository = memberRepository;
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
    }
    //검색용
    @Override
    public Page<FreeBoardDTO> findSearchBoardList(Pageable pageable, int categoryNo, String searchValue, String searchCondition) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(), Sort.by("boardNo").descending());

        FreeBoardCategory freeBoardCategory = categoryRepository.findById(categoryNo).get();

        Page<FreeBoard> freeBoards = null;
        if(searchCondition.equals("title")){
            freeBoards = freeBoardRepository.findByBoardTitleContainingAndFreeBoardCategoryAndBoardDeleteYN(searchValue, freeBoardCategory, 'N',pageable);
        }else if (searchCondition.equals("writer")){
            freeBoards = freeBoardRepository.findByFreeBoardMemberMemberNameContainingAndFreeBoardCategoryAndBoardDeleteYN(searchValue, freeBoardCategory, 'N',pageable);

        }else if(searchCondition.equals("content")){
            freeBoards = freeBoardRepository.findByBoardContentContainingAndFreeBoardCategoryAndBoardDeleteYN(searchValue, freeBoardCategory, 'N',pageable);
        }

        return freeBoards.map(FreeBoard -> modelMapper.map(FreeBoard, FreeBoardDTO.class));
    }
    // 카테고리 포함 조회용
    @Override
    public Page<FreeBoardDTO> findCategoryBoardList(@PageableDefault Pageable pageable, int categoryNo) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,                        // pageing 정보들을 담아
                pageable.getPageSize(),
                Sort.by("boardNo").descending());
        FreeBoardCategory freeBoardCategory = categoryRepository.findById(categoryNo).get();

        Page<FreeBoardDTO> pageFreeBoards =freeBoardRepository.findByFreeBoardCategoryAndBoardDeleteYN(freeBoardCategory,'N',pageable).map(FreeBoard -> modelMapper.map(FreeBoard,FreeBoardDTO.class));

        return pageFreeBoards;
    }

    @Override
    @Transactional
    public FreeBoardDTO selectBoardDetail(int boardNo){

        FreeBoard freeBoard = freeBoardRepository.findById(boardNo).get();
        freeBoard.setBoardViewCount(freeBoard.getBoardViewCount() + 1);

        List<FreeBoardAnswer> freeBoardAnswers = answerRepository.findByFreeBoardBoardNoAndAnswerYN(boardNo, 'N');
        if(freeBoardAnswers != null) {
            freeBoardAnswers.forEach(System.out::println);
        }
        freeBoard.setFreeBoardAnswerList(freeBoardAnswers);
        FreeBoardDTO freeBoardDTO = modelMapper.map(freeBoard, FreeBoardDTO.class);
        return freeBoardDTO;
    }


    @Override
    @Transactional
    public void deleteFreeBoard(int boardNo) {
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        FreeBoard deleteBoard = freeBoardRepository.findById(boardNo).get();
        deleteBoard.setBoardDeleteYN('Y');
        deleteBoard.setBoardDeletionDate(sqlDate);
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

        System.out.println("(service)modifyBoard freeBoard: " + modifyBoard );

        System.out.println(modifyBoard.getFreeBoardCategory().getBoardCategoryNo());

        modifyBoard.setBoardTitle(freeBoard.getBoardTitle());
        modifyBoard.setBoardContent(freeBoard.getBoardContent());
        modifyBoard.setBoardModifiedDate(sqlDate);
        modifyBoard.setFreeBoardCategory(freeBoardCategory);
        modifyBoard.setBulletinBoardSecretYN('N');
        freeBoardRepository.save(modifyBoard);
    }
    //        modifyBoard.setBulletinBoardSecretYN('N');비밀글 기능 다시만들어야함

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
        newBoard.setBoardDeleteYN(freeBoard.getBoardDeleteYN());

        freeBoardRepository.save(newBoard);
    }


    @Override
    @Transactional
    public void insertAnswer(FreeBoardAnswerDTO registAnswer) {
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        FreeBoard freeBoard = modelMapper.map(registAnswer.getFreeBoard(),FreeBoard.class);

        FreeBoardMember freeBoardMember =  modelMapper.map(registAnswer.getFreeBoardMember(),FreeBoardMember.class);

        FreeBoardAnswer newRegistAnswer = new FreeBoardAnswer();

        newRegistAnswer.setAnswerContent(registAnswer.getAnswerContent());
        newRegistAnswer.setAnswerYN('N');
        newRegistAnswer.setAnswerReportCount(0);
        newRegistAnswer.setAnswerCreatedDate(timestamp);
        newRegistAnswer.setFreeBoard(freeBoard);
        newRegistAnswer.setFreeBoardMember(freeBoardMember);

        answerRepository.save(newRegistAnswer);
    }

    @Override
    @Transactional
    public void deleteAnswer(int answerNo) {
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        FreeBoardAnswer deleteAnswer = answerRepository.findById(answerNo).get();
        deleteAnswer.setAnswerYN('Y');
        deleteAnswer.setAnswerDeleteDate(timestamp);
        answerRepository.save(deleteAnswer);
    }

    @Override
    @Transactional
    public void updateAnswer(FreeBoardAnswerDTO modifyAnswer) {
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        FreeBoardMember freeBoardMember = modelMapper.map(modifyAnswer.getFreeBoardMember(),FreeBoardMember.class);

        FreeBoard freeBoard = modelMapper.map(modifyAnswer.getFreeBoard(),FreeBoard.class);

        FreeBoardAnswer freeBoardAnswer = answerRepository.findById(modifyAnswer.getAnswerNo()).get();
        freeBoardAnswer.setAnswerContent(modifyAnswer.getAnswerContent());
        freeBoardAnswer.setAnswerYN('N');
        freeBoardAnswer.setAnswerReportCount(modifyAnswer.getAnswerReportCount());
        freeBoardAnswer.setAnswerModifyDate(timestamp);
        freeBoardAnswer.setFreeBoardMember(freeBoardMember);
        freeBoardAnswer.setFreeBoard(freeBoard);

        answerRepository.save(freeBoardAnswer);
    }
}
