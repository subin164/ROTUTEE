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
        String status = "N";

        //when
        List<Board> boardList = boardRepository.findByBoardCategoryNoAndDeleteYN(categoryNo, status);

        //then
        assertNotNull(boardList);
        boardList.forEach(System.out::println);
    }
}
