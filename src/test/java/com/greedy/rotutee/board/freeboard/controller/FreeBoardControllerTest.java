package com.greedy.rotutee.board.freeboard.controller;

import com.greedy.rotutee.board.controller.FreeBoardController;
import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName : com.greedy.rotutee.board.freeboard.controller
 * fileName : FreeBoardControllerTest
 * author : soobeen
 * date : 2022-04-21
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-21        soobeen     최초 생성
 */

@SpringBootTest
@WebAppConfiguration
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class FreeBoardControllerTest {


    @Autowired
    private FreeBoardController freeBoardController;
    private MockMvc mockMvc;


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
    public void 공지사항_메뉴_조회용_컨트롤러_동작_확인_테스트() throws  Exception{

        //given

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/freeBoard/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/freeBoard/list"))
                .andDo(MockMvcResultHandlers.print());
    }
}
