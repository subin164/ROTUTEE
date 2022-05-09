package com.greedy.rotutee.board.freeboard.model.repository;

import com.greedy.rotutee.board.freeboard.dto.FreeBoardDTO;
import com.greedy.rotutee.board.freeboard.entity.FreeBoard;
import com.greedy.rotutee.board.freeboard.entity.FreeBoardCategory;
import com.greedy.rotutee.board.freeboard.service.repository.FreeBoardCategoryRepository;
import com.greedy.rotutee.board.freeboard.service.repository.FreeBoardRepository;
import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName : com.greedy.rotutee.board.freeboard.model.repository
 * fileName : FreeBoardRepositoryTest
 * author : soobeen
 * date : 2022-04-21
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-21          soobeen     최초 생성
 */

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, BeanConfiguration.class, JPAConfiguration.class})
public class FreeBoardRepositoryTest {

    @Autowired
    private FreeBoardRepository freeBoardRepository;
    @Autowired
    private FreeBoardCategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Test
    public void 레퍼지토리_의존성_테스트(){
        assertNotNull(freeBoardRepository);
        assertNotNull(categoryRepository);
        System.out.println("freeBoardRepository = " + freeBoardRepository);
        System.out.println("freeBoardRepository = " + categoryRepository);
    }

    @Test
    public void 커뮤니티_카테고리_목록_조회() {

        //given 값을 넣어주는 곳
        Integer categoryNo = 6;     // 6 : 공지사항, 5: 질문과 답변, 4:자유\
        Pageable pageable = PageRequest.of(0, 5);

        //when 값을 넣어준것에 대한 로직처리
        FreeBoardCategory categoryEntry = categoryRepository.findById(categoryNo).get();

        Page<FreeBoardDTO> pageFreeBoards =freeBoardRepository.findByFreeBoardCategoryAndBoardDeleteYN(categoryEntry,'N',pageable)
                .map(FreeBoard -> modelMapper.map(FreeBoard,FreeBoardDTO.class));

        //then 결과
        pageFreeBoards.forEach(System.out::println);
        assertNotNull(pageFreeBoards);
    }

    @Test
    public void 커뮤니티_검색_제목_결과_조회(){

        //given
        Integer categoryNo = 6;     // 6 : 공지사항, 5: 질문과 답변, 4:자유\
        String searchValue = "1";
        Pageable pageable = PageRequest.of(0, 5);

        //when
        FreeBoardCategory categoryEntry = categoryRepository.findById(categoryNo).get();

        Page<FreeBoard> pageFreeBoards = freeBoardRepository.findByBoardTitleContainingAndFreeBoardCategoryAndBoardDeleteYN(searchValue,  categoryEntry, 'N', pageable);

        //then
        pageFreeBoards.forEach(System.out::println);
        assertNotNull(pageFreeBoards);
    }

    @Test
    public void 커뮤니티_검색_작성자_결과_조회(){

        //given
        Integer categoryNo = 6;     // 6 : 공지사항, 5: 질문과 답변, 4:자유\
        String searchValue = "공상";
        Pageable pageable = PageRequest.of(0, 10);

        //when
        FreeBoardCategory categoryEntry = categoryRepository.findById(categoryNo).get();

        Page<FreeBoard> pageFreeBoards = freeBoardRepository.findByFreeBoardMemberMemberNameContainingAndFreeBoardCategoryAndBoardDeleteYN(searchValue,  categoryEntry, 'N', pageable);

        //then
        pageFreeBoards.forEach(System.out::println);
        assertNotNull(pageFreeBoards);
    }

    @Test
    public void 커뮤니티_검색_내용_결과_조회(){

        //given
        Integer categoryNo = 6;     // 6 : 공지사항, 5: 질문과 답변, 4:자유\
        String searchValue = "필수 조회";
        Pageable pageable = PageRequest.of(0, 10);

        //when
        FreeBoardCategory categoryEntry = categoryRepository.findById(categoryNo).get();

        Page<FreeBoard> pageFreeBoards = freeBoardRepository.findByBoardContentContainingAndFreeBoardCategoryAndBoardDeleteYN(searchValue,  categoryEntry, 'N', pageable);

        //then
        pageFreeBoards.forEach(System.out::println);
        assertNotNull(pageFreeBoards);
    }

    @Test
    public void 커뮤니티_상세_내용__조회(){

        //given
        Integer categoryNo = 6;     // 6 : 공지사항, 5: 질문과 답변, 4:자유\
        String searchValue = "필수 조회";
        Pageable pageable = PageRequest.of(0, 10);

        //when
        FreeBoardCategory categoryEntry = categoryRepository.findById(categoryNo).get();

        Page<FreeBoard> pageFreeBoards = freeBoardRepository.findByBoardContentContainingAndFreeBoardCategoryAndBoardDeleteYN(searchValue,  categoryEntry, 'N', pageable);

        //then
        pageFreeBoards.forEach(System.out::println);
        assertNotNull(pageFreeBoards);
    }
}
