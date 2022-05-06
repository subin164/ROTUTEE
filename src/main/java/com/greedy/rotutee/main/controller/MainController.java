package com.greedy.rotutee.main.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.main.dto.AttachedFileDTO;
import com.greedy.rotutee.main.dto.LectureDTO;
import com.greedy.rotutee.main.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping(value = {"/", "/main"})
    public ModelAndView main(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        if(customUser == null) {

            List<LectureDTO> recentLectureList = mainService.findRecentLectureList();
            List<LectureDTO> popularLectureList = mainService.findPopularLectureList();

            mv.addObject("popularLectureList", popularLectureList);
            mv.addObject("recentLectureList", recentLectureList);
            mv.setViewName("/main/main");

        } else {

            List<AttachedFileDTO> bannerList = mainService.findBannerListByMemberNo(customUser.getNo());
            List<LectureDTO> recentLectureList = mainService.findRecentLectureList();
            List<LectureDTO> popularLectureList = mainService.findPopularLectureList();

            if(bannerList == null) {
                mv.addObject("recentLectureList", recentLectureList);
                mv.addObject("popularLectureList", popularLectureList);
                mv.setViewName("/main/main");
            } else {
                mv.addObject("bannerList", bannerList);
                mv.addObject("recentLectureList", recentLectureList);
                mv.addObject("popularLectureList", popularLectureList);
                mv.setViewName("/main/main");
            }

        }

        return mv;
    }

    @PostMapping("/")
    public ModelAndView redirectMain(ModelAndView mv) {

        mv.setViewName("redirect:/");
        return mv;
    }
}
