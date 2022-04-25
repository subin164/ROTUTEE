package com.greedy.rotutee.lecture.mylecture.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardLectureDTO;
import com.greedy.rotutee.lecture.mylecture.service.MyLectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.lecture.mylecture.controller
 * fileName : MyLectureController
 * author : SeoYoung
 * date : 2022-04-24
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-24 SeoYoung 최초 생성
 */
@Controller
@RequestMapping("/mylecture")
public class MyLectureController {

    private MyLectureService myLectureService;

    @Autowired
    public MyLectureController(MyLectureService myLectureService) {
        this.myLectureService = myLectureService;
    }


    @GetMapping("/tuteelist")
    public ModelAndView findMyLearningList(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();
        List<DashboardLectureDTO> learning = myLectureService.findLearningList(memberNo);

        mv.addObject("learning", learning);
        mv.setViewName("dashboard/mypage/mylearninglist");

        return mv;
    }
}
