package com.greedy.rotutee.lecture.lecture.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, BeanConfiguration.class, JPAConfiguration.class})
public class LectureMainControllerTests {

    @Autowired
    private LectureMainController lectureMainController;
    private MockMvc mockMvc;

    @BeforeEach
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(lectureMainController).build();
    }

    @Test
    public void testInit() {
        assertNotNull(lectureMainController);
        assertNotNull(mockMvc);
    }

    @Test
    @Transactional
    public void 수강가능한_강의_조회_테스트() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/lecture/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/lecture/lecturelist"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void 강의_검색_테스트() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/lecture/search")
                        .param("searchCondition", "1")
                        .param("searchValue", "완성"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.forwardedUrl("/lecture/lecturelist"))
                        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void 강의_상세조회_테스트() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/lecture/detail")
                        .param("lectureNo", "2"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.forwardedUrl("/lecture/lecturedetail"))
                        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void 강의평_작성_테스트() throws Exception {
        //given
        MultiValueMap<String , String > params =new LinkedMultiValueMap<>();
        params.add("rating", "1");
        params.add("content", "강의가 좋네요");
        params.add("lectureNo", "2");
        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/lecture/writereview").params(params))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/lecture/detail"))
                .andExpect(MockMvcResultMatchers.flash().attribute("message", "강의평을 작성하였습니다."))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void 강의평_수정_테스트() throws Exception{
        //given
        MultiValueMap<String , String > params =new LinkedMultiValueMap<>();
        params.add("lectureReviewNo", "1");
        params.add("lectureReviewContent", "강의가 좋네요");
        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/lecture/modifyreview").params(params))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/lecture/detail"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void 강의평_삭제_테스트() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/lecture/removereview")
                .param("lectureReviewNo", "1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/lecture/detail"))
                .andExpect(MockMvcResultMatchers.flash().attribute("message", "강의평을 삭제하였습니다."))
                .andDo(MockMvcResultHandlers.print());
    }
}
