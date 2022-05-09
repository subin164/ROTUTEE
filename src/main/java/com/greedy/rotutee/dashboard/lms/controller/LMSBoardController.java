package com.greedy.rotutee.dashboard.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.controller
 * fileName : LMSBoardController
 * author : SeoYoung
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 SeoYoung 최초 생성
 */
@Controller
@RequestMapping("/lectureboard")
public class LMSBoardController {

    @GetMapping("/list")
    public ModelAndView findNormalBoardList(ModelAndView mv) {



        mv.setViewName("dashboard/lms/normalBoard");
        return mv;
    }
}
