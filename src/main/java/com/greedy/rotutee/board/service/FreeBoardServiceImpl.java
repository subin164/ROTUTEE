package com.greedy.rotutee.board.service;


import com.greedy.rotutee.board.dto.FreeBoardDTO;
import com.greedy.rotutee.board.entity.FreeBoard;
import com.greedy.rotutee.board.repository.CategoryRepository;
import com.greedy.rotutee.board.repository.FreeBoardRepository;
import com.greedy.rotutee.board.repository.FreeBoardMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    private CategoryRepository categoryRepository;
    private FreeBoardRepository freeBoardRepository;
    private FreeBoardMemberRepository memberRepository;
    private ModelMapper modelMapper;

    @Autowired
    public FreeBoardServiceImpl(CategoryRepository categoryRepository, FreeBoardRepository freeBoardRepository, FreeBoardMemberRepository memberRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.freeBoardRepository = freeBoardRepository;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<FreeBoardDTO> findByBoardCategoryNo(int categoryNo) {
       // List<FreeBoard> freeBoardEntityList = freeBoardRepository.findByBoardCategoryNo(categoryNo);

        /*List<FreeBoardDTO> boardList = freeBoardEntityList.stream().map(freeBoard -> modelMapper.map(freeBoard, FreeBoardDTO.class)).collect(Collectors.toList());
        return boardList;*/
        return null;
    }

    @Override
    public void registNewFreeBoard(FreeBoardDTO newFreeBoard) {

        //List<FreeBoard> freeBoardEntityList = freeBoardRepository.findByBoardCategoryNo(categoryNo);


    }

}
