package com.greedy.rotutee.board.freeBoard.model.repository;

import com.greedy.rotutee.board.freeBoard.entity.FreeBoard;
import com.greedy.rotutee.board.freeBoard.entity.FreeBoardCategory;
import com.greedy.rotutee.board.freeBoard.repository.FreeBoardCategoryRepository;
import com.greedy.rotutee.board.freeBoard.repository.FreeBoardRepository;
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
        Integer categoryNo = 6;     // 6 : 공지사항, 5: 질문과 답변, 4:자유

        //when 값을 넣어준것에 대한 로직처리
        FreeBoardCategory categoryEntry = categoryRepository.findById(categoryNo).get();
        System.out.println("!!!"+categoryEntry);

        List<FreeBoard> freeBoardEnityList = freeBoardRepository.findByfreeBoardCategory(categoryEntry);

        //then 결과
        assertNotNull(freeBoardEnityList);
        freeBoardEnityList.forEach(System.out::println);
    }
}
