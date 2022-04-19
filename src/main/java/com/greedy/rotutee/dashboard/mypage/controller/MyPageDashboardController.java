package com.greedy.rotutee.dashboard.mypage.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.dashboard.mypage.dto.MypageDashboardDTO;
import com.greedy.rotutee.dashboard.mypage.service.MypageDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.controller
 * fileName : MyPageDashboardController
 * author : SeoYoung
 * date : 2022-04-19
 * description : 마이페이지 초기 권한별 대시보드
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */

@Controller
@RequestMapping("/mypage")
public class MyPageDashboardController {

    private MypageDashboardService mypageDashboardService;

    @Autowired
    public MyPageDashboardController(MypageDashboardService mypageDashboardService) {
        this.mypageDashboardService = mypageDashboardService;
    }

    @GetMapping("tuteedashboard")
    public ModelAndView findTuteeDashboard(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();

        MypageDashboardDTO dashboard = mypageDashboardService.findTuteeDashboard(memberNo);

        return mv;
    }

}
