package com.greedy.rotutee.dashboard.lms.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.dashboard.lms.dto.LecturePlayDTO;
import com.greedy.rotutee.dashboard.lms.entity.LMSChapter;
import com.greedy.rotutee.dashboard.lms.service.LMSLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.controller
 * fileName : LMSLearningController
 * author : SeoYoung
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 SeoYoung 최초 생성
 */
@Controller
@RequestMapping("/learning")
public class LMSLearningController {

    private LMSLearningService lmsLearningService;

    @Autowired
    public LMSLearningController(LMSLearningService lmsLearningService) {
        this.lmsLearningService = lmsLearningService;
    }

    @GetMapping("/play")
    public ModelAndView findlecturePlay(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser, HttpServletRequest request){

        int memberNo = customUser.getNo();
        HttpSession session = request.getSession();
        int lectureNo = Integer.parseInt(String.valueOf(session.getAttribute("lectureNo")));

        LecturePlayDTO lecturePlay = lmsLearningService.findLecturePlay(lectureNo, memberNo);

        System.out.println("memberNo = " + memberNo);
        System.out.println("lectureNo = " + lectureNo);

        mv.addObject("lecturePlay", lecturePlay);
        mv.setViewName("lecture/lectureplay");
        return mv;

    }
}
