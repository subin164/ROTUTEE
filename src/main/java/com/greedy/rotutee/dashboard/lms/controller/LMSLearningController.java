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
 * description : 강의시청 페이지로 정보를 넘겨주는 Controller
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

    /**
     * methodName : findlecturePlay
     * author : SeoYoung Kim
     * description :
     *
     * @param mv : lecture정보를 담을 객체
     * @param customUser 현재 로그인하고 있는 회원 정보
     * @param request 시청할 강의의 번호를 담은 요청
     * @return ModelAndView
     */
    @GetMapping("/play")
    public ModelAndView findlecturePlay(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser, HttpServletRequest request){
        int memberNo = customUser.getNo();
        HttpSession session = request.getSession();
        int lectureNo = (Integer)session.getAttribute("lectureNo");
//        int lectureNo = Integer.parseInt(request.getParameter("lectureNo"));
        LecturePlayDTO lecturePlay = lmsLearningService.findLecturePlay(lectureNo, memberNo);

        mv.addObject("lecturePlay", lecturePlay);
        mv.setViewName("lecture/lectureplay");
        return mv;

    }
}
