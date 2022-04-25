package com.greedy.rotutee.dashboard.lms.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.dashboard.lms.dto.LMSDashboardDTO;
import com.greedy.rotutee.dashboard.lms.service.LMSDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.controller
 * fileName : LMSDashboardController
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */
@Controller
@RequestMapping("/lms")
public class LMSDashboardController {

    private LMSDashboardService lmsDashboardService;

    @Autowired
    public LMSDashboardController(LMSDashboardService lmsDashboardService) {
        this.lmsDashboardService = lmsDashboardService;
    }

    @GetMapping("/dashboard")
    public ModelAndView findLMSDashboard(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser, HttpServletRequest request){

        int memberNo = customUser.getNo();
        int lectureNo = Integer.parseInt(request.getParameter("no"));

        LMSDashboardDTO dashboard = lmsDashboardService.findLMSDashboard(memberNo);

        mv.setViewName("dashboard/lms/dashboard");
        return mv;
    }
}
