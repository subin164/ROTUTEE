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

import java.util.ArrayList;
import java.util.List;

/**
 * packageName      : com.greedy.rotutee.main.controller
 * fileName         : MainController
 * author           : SEOK
 * date             : 2022-04-18
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-04-18
 * 2022-05-04      SEOK             메인페이지 배너, 인기강의, 최신강의 추가
 */
@Controller
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    /**
     * methodName : main
     * author : SEOK
     * description : 메인페이지 이동 및 배너, 인기강의, 최신강의 목록 조회 기능
     *
     * @Param ModelAndView mv
     * @Param CustomUser customUser 로그인된 회원 객체
     * @Return ModelAndView mv
     * */
    @GetMapping(value = {"/", "/main"})
    public ModelAndView main(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        List<LectureDTO> recentLectureList = mainService.findRecentLectureList();
        List<LectureDTO> popularLectureList = mainService.findPopularLectureList();
        List<AttachedFileDTO> bannerList = new ArrayList<>();

        if(customUser == null) {
            bannerList = mainService.findRecentBannerList();

            mv.addObject("bannerList", bannerList);
            mv.addObject("popularLectureList", popularLectureList);
            mv.addObject("recentLectureList", recentLectureList);
            mv.setViewName("/main/main");

        } else {
            bannerList = mainService.findBannerListByMemberNo(customUser.getNo());

            if(bannerList == null) {
                bannerList = mainService.findRecentBannerList();

                mv.addObject("bannerList", bannerList);
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
