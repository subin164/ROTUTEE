package com.greedy.rotutee.dashboard.lms.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.dashboard.lms.dto.*;
import com.greedy.rotutee.dashboard.lms.service.LMSDashboardService;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardLectureWatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.controller
 * fileName : LMSDashboardController
 * author : SeoYoung
 * date : 2022-04-19
 * description : LMS 대시보드로 조회값을 넘겨주는 Controller
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */
@Controller
@RequestMapping("/lms")
@SessionAttributes({"lectureNo"})
public class LMSDashboardController {

    private LMSDashboardService lmsDashboardService;

    @Autowired
    public LMSDashboardController(LMSDashboardService lmsDashboardService) {
        this.lmsDashboardService = lmsDashboardService;
    }

    /**
     * methodName : findLMSDashboard
     * author : SeoYoung Kim
     * description : LMS 대시보드에서 보여주는 정보를 넘겨주는 메소드
     *
     * @param mv : dashboard에서 보여지는 정보를 담는 객체
     * @param customUser 현재 로그인하고 있는 User 정보
     * @param request lectureNo 정보를 담고있음
     * @return modelandview
     */

    @GetMapping("/dashboardlist")
    public ModelAndView findLMSDashboard(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser, HttpServletRequest request){

        int memberNo = customUser.getNo();
        int lectureNo = Integer.parseInt(request.getParameter("no"));

        HttpSession session = request.getSession();
        session.removeAttribute("lectureNo");
        session.setAttribute("lectureNo", lectureNo);

        ToDoDTO todo = new ToDoDTO();
        todo.setMemberNo(memberNo);
        todo.setLectureNo(lectureNo);

        LMSDashboardDTO dashboard = lmsDashboardService.findLMSDashboard(todo);

        mv.addObject("lectureNo", lectureNo);
        mv.addObject("dashboard", dashboard);
        mv.setViewName("dashboard/lms/dashboard");
        return mv;
    }




}
