package com.greedy.rotutee.dashboard.mypage.controller;

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

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class MypageDashboardControllerTest {

    @Autowired
    private MyPageDashboardController dashboardController;
    private MockMvc mockMvc;

    @BeforeEach
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(dashboardController).build();
    }

    @Test
    public void testInit() {
        assertNotNull(dashboardController);
        assertNotNull(mockMvc);
    }

//    @Test
//    public void 튜티_대시보드_조회() throws Exception {
//        //given
//
//        //when
//
//        //then
//        mockMvc.perform(MockMvcRequestBuilders.get("/dashboard/tuteedashboard"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.forwardedUrl("dashboard/mypage/dashboard"))
//                .andDo(MockMvcResultHandlers.print());
//    }
}
