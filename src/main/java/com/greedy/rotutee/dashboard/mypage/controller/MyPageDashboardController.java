package com.greedy.rotutee.dashboard.mypage.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.board.serviceBoard.dto.BoardDTO;
import com.greedy.rotutee.board.serviceBoard.entity.Board;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.MypageDashboardDTO;
import com.greedy.rotutee.dashboard.mypage.dto.tutor.MypageTutorDTO;
import com.greedy.rotutee.dashboard.mypage.service.MypageDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
@RequestMapping("/dashboard")
public class MyPageDashboardController {

    private MypageDashboardService mypageDashboardService;

    @Autowired
    public MyPageDashboardController(MypageDashboardService mypageDashboardService) {
        this.mypageDashboardService = mypageDashboardService;
    }

    /**
     * methodName : findTuteeDashboard
     * author : SeoYoung Kim
     * description : 마이페이지 튜티 대시보드
     *
     * @param mv
     * @param customUser
     * @return model and view
     */
    @GetMapping("/tuteedashboard")
    public ModelAndView findTuteeDashboard(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();

        MypageDashboardDTO dashboard = mypageDashboardService.findTuteeDashboard(memberNo);

        mv.addObject("dashboard", dashboard);
        mv.setViewName("dashboard/mypage/dashboard");

        return mv;
    }


    /**
     * methodName : findTutorDashboard
     * author : SeoYoung Kim
     * description : 마이페이지 튜터대시보드
     *
     * @param mv
     * @param customUser
     * @return model and view
     */
    @GetMapping("/tutordashboard")
    public ModelAndView findTutorDashboard(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();

        MypageTutorDTO dashboard = mypageDashboardService.findTutorDashboard(memberNo);
        mv.addObject("dashboard", dashboard);
        mv.setViewName("dashboard/mypage/tutordashboard");

        return mv;
    }

    @GetMapping("/mypost")
    public ModelAndView findAllMyPost(ModelAndView mv, @AuthenticationPrincipal CustomUser loginMember, @PageableDefault Pageable pageable) {

        int memberNo = loginMember.getNo();

        Page<BoardDTO> boardList = mypageDashboardService.findAllMyPost(memberNo, pageable);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(boardList);

        mv.addObject("boardList", boardList);
        mv.addObject("paging", paging);
        mv.setViewName("/dashboard/mypage/mypost");

        return mv;
    }

}
