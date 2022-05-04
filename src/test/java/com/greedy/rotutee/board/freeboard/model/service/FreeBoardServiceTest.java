package com.greedy.rotutee.board.freeboard.model.service;

import com.greedy.rotutee.board.freeboard.dto.FreeBoardDTO;
import com.greedy.rotutee.board.freeboard.service.FreeBoardService;
import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName : com.greedy.rotutee.board.freeboard.model.service
 * fileName : FreeBoardServiceTest
 * author : soobeen
 * date : 2022-04-21
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-21          soobeen     최초 생성
 */


@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class FreeBoardServiceTest {
/*
    @Autowired
    private FreeBoardCategoryRepository categoryRepository;

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Autowired
    private FreeBoardMemberRepository memberRepository;

    @Autowired
    private FreeBoardAnswerRepository answerRepository;*/

    private ModelMapper modelMapper;
    private FreeBoardService freeBoardService;

  /*  @Test
    public void 레퍼지토리_의존성_테스트(){
        assertNotNull(categoryRepository);
        assertNotNull(freeBoardRepository);
        assertNotNull(memberRepository);
        assertNotNull(answerRepository);
        System.out.println("freeBoardCategoryRepository = " + categoryRepository);
        System.out.println("freeBoardRepository = " + freeBoardRepository);
        System.out.println("freeBoardMemberRepository = " + memberRepository);
        System.out.println("freeBoardAnswerRepository = " + answerRepository);
    }*/


    @Test
    public void 커뮤니티_카테고리_목록조회_서비스(){
        //given 값을 넣어주는 곳
        int categoryNo = 6;     // 6 : 공지사항, 5: 질문과 답변, 4:자유\
        Pageable pageable = PageRequest.of(0, 5);

        //when 값을 넣어준것에 대한 로직처리

        Page<FreeBoardDTO> boardList = freeBoardService.findCategoryBoardList(pageable, categoryNo);

        //then 결과
        boardList.forEach(System.out::println);
        assertNotNull(boardList);
    }


    @Test
    public void 커뮤니티_검색_제목_결과_조회(){

        //given
        Integer categoryNo = 6;     // 6 : 공지사항, 5: 질문과 답변, 4:자유\
        String searchValue = "1";
        String searchCondition = "writer";
        Pageable pageable = PageRequest.of(0, 5);

        //when
        Page<FreeBoardDTO> boardList = freeBoardService.findSearchBoardList(pageable, categoryNo,searchValue, searchCondition);

        //then
        boardList.forEach(System.out::println);
        assertNotNull(boardList);
    }


}
