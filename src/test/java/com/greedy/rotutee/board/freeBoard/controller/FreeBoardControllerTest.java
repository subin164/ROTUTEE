package com.greedy.rotutee.board.freeBoard.controller;

import com.greedy.rotutee.board.freeBoard.entity.FreeBoard;
import com.greedy.rotutee.board.freeBoard.entity.FreeBoardCategory;
import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName : com.greedy.rotutee.board.freeBoard.controller
 * fileName : FreeBoardControllerTest
 * author : soobeen
 * date : 2022-04-21
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-21        soobeen     최초 생성
 */

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class FreeBoardControllerTest {

    private FreeBoardController freeBoardController;
    private MockMvc mockMvc;

    @Autowired
    public FreeBoardControllerTest(FreeBoardController freeBoardController){
        this.freeBoardController = freeBoardController;
    }

    @BeforeEach
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(freeBoardController).build();
    }

    @Test
    public void testInit() {
        assertNotNull(freeBoardController);
        assertNotNull(mockMvc);
    }


    @Test
    public void 커뮤니티_목록_카테고리별_컨트롤러_동작_확인_테스트()throws Exception{
        //given
        int categoryNo = 4;


        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/freeBoard/list?category="+categoryNo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("board/freeBoard/boardList"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 커뮤니티_상세조회_컨트롤러_동작_확인_테스트()throws Exception{
        //given
        int boardNo = 30;

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/freeBoard/detail?boardNo="+boardNo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/freeBoard/detail"))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void 커뮤니티_수정조회_컨트롤러_동작_확인_테스트()throws Exception{
        //given
        int boardNo = 30;

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/freeBoard/modify"+boardNo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/freeBoard/modify"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 커뮤니티_수정_컨트롤러_동작_확인_테스트()throws Exception{
        //given
        int boardNo = 30;
        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/freeBoard/modify/"+boardNo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/freeBoard/detail/"+boardNo))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void 커뮤니티_생성_컨트롤러_동작_확인_테스트()throws Exception{
        //given
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        FreeBoardCategory category = new FreeBoardCategory(4,"자유게시판",1);
        FreeBoard freeBoard = new FreeBoard(1,"제목","내용",sqlDate,null,null,'N',10,category,null,1,'Y',null);


        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/freeBoard/modify/" + freeBoard.getFreeBoardCategory().getBoardCategoryNo()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/freeBoard/detail/" + freeBoard.getFreeBoardCategory().getBoardCategoryNo()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 댓글_수정_컨트롤러_동작_확인_테스트()throws Exception{
        //given


        //when

        //then

    }

    @Test
    public void 댓글_생성성_컨트롤러_동작_확인_테스()throws Exception{
        //given


        //when

        //then

    }

}
