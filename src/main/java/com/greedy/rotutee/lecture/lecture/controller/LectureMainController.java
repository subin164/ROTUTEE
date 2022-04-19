package com.greedy.rotutee.lecture.lecture.controller;

import com.greedy.rotutee.lecture.lecture.dto.LectureDTO;
import com.greedy.rotutee.lecture.lecture.service.LectureMainService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/lecture")
public class LectureMainController {
    
    private final LectureMainService lectureMainService;

    public LectureMainController(LectureMainService lectureMainService) {
        this.lectureMainService = lectureMainService;
    }

    @GetMapping("list")
    public ModelAndView findAllApproveLectures(ModelAndView mv) {

        List<LectureDTO> lectureList = lectureMainService.findAllLecture();
        System.out.println("lectureList = " + lectureList);

        mv.addObject("lectureList", lectureList);
        mv.setViewName("/lecture/list");
        return mv;
    }

}
