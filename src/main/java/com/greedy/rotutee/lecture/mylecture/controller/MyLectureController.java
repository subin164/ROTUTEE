package com.greedy.rotutee.lecture.mylecture.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.dashboard.lms.dto.LMSAttachmentDTO;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardLectureDTO;
import com.greedy.rotutee.lecture.mylecture.dto.MyLectureDTO;
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
 * description : 나의 강의 리스트를 return 하는 Controller
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


    /**
     * methodName : findMyLearningList
     * author : SeoYoung Kim
     * description : 튜티 수강신청한 강의 리스트 조회
     *
     * @param mv
     * @param customUser
     * @return model and view
     */
    @GetMapping("/tuteelist")
    public ModelAndView findMyLearningList(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();
        List<DashboardLectureDTO> learning = myLectureService.findLearningList(memberNo);

        mv.addObject("learning", learning);
        mv.setViewName("dashboard/mypage/mylearninglist");

        return mv;
    }

    /**
     * methodName : findMyLectureList
     * author : SeoYoung Kim
     * description : 튜터 신청강의 리스트
     *
     * @param mv
     * @param customUser
     * @return model and view
     */
    @GetMapping("/tutorlist")
    public ModelAndView findMyLectureList(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();
        List<MyLectureDTO> lectures = myLectureService.findMyLectureList(memberNo);

        mv.addObject("lectures", lectures);
        mv.setViewName("dashboard/mypage/mylecturelist");

        return mv;
    }
}
