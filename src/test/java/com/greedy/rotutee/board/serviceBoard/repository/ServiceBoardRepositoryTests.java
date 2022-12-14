package com.greedy.rotutee.board.serviceBoard.repository;

import com.greedy.rotutee.board.serviceBoard.entity.Board;
import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.repository
 * fileName : ServiceBoardReopsitoryTests
 * author : 7sang
 * date : 2022-05-04
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-04 7sang 최초 생성
 */

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class ServiceBoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void 고객센터_게시판_전체_조회_테스트_메서드() {

        //given
        int categoryNo = 7;
        char status = 'N';

        //when
        List<Board> boardList = boardRepository.findByBoardCategoryNoAndDeleteYN(categoryNo, status);

        //then
        assertNotNull(boardList);
        boardList.forEach(System.out::println);
    }

    @Test
    public void 고객센터_게시판_상세_보기_테스트_메서드() {

        //given
        int boardNo = 148;
        
        //when
//        Board fountBoard = boardRepository.findByNoAndBoardAnswerListAnswerYn(boardNo, 'N');
        
        //then
//        assertNotNull(fountBoard);
//        System.out.println("fountBoard.getBoardAnswerList() = " + fountBoard.getBoardAnswerList());
    }

    @Test
    public void 고객센터_게시판_검색_테스트_메서드() {

        //given
        String searchCondition = "제목";
        String searchValue = "test";

        //when
        List<Board> searchBoardList = null;

//        if (searchCondition == "작성자") {
//            searchBoardList = boardRepository.findByMemberName(searchValue);
//        } else if(searchCondition == "제목") {
//            searchBoardList = boardRepository.findByTitle(searchValue);
//        }

        //then
    }
}
