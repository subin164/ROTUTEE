package com.greedy.rotutee.main.controller;

import com.greedy.rotutee.main.dto.LectureDTO;
import com.greedy.rotutee.main.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ModelAndView main(ModelAndView mv) {

        List<LectureDTO> recentLectureList = mainService.findRecentLectureList();

//        System.out.println("recentLectureList = " + recentLectureList);

        mv.addObject("recentLectureList", recentLectureList);
        mv.setViewName("/main/main");
        return mv;
    }

    @PostMapping("/")
    public ModelAndView redirectMain(ModelAndView mv) {

        mv.setViewName("redirect:/");
        return mv;
    }
}
