package com.greedy.rotutee.lecture.request.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.lecture.request.dto.*;
import com.greedy.rotutee.lecture.request.entity.ViewQuiz;
import com.greedy.rotutee.lecture.request.service.LectureRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/request")
public class LectureRequestController {

    private final LectureRequestService lectureRequestService;

    @Autowired
    public LectureRequestController(LectureRequestService lectureRequestService) {
        this.lectureRequestService = lectureRequestService;
    }


    @GetMapping("/list")
    public ModelAndView findMyLectureList(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();
        System.out.println("memberNo = " + memberNo);
        List<LectureDTO> lectureList = lectureRequestService.findLectureListBytutorNo(memberNo);

        mv.addObject("lectureList", lectureList);
        mv.setViewName("/request/myrequestlist");

        return mv;
    }

    @GetMapping("/lecture")
    public String lectureRegistForm() {

        return "/request/lecturerequestform";
    }

    @PostMapping("/lecture")
    public ModelAndView registLectureOpeningApplication(ModelAndView mv, @ModelAttribute LectureDTO newLecture
                                                                       , @ModelAttribute List<ChapterDTO> chapterList
                                                                       , @ModelAttribute List<ClassDTO> classDTOList
                                                                       , @ModelAttribute List<QuizDTO> quizList
                                                                       , @ModelAttribute List<ViewQuizDTO> viewQuizList
                                                                       , @AuthenticationPrincipal CustomUser customUser) {

        System.out.println("newLecture = " + newLecture);
        System.out.println("chapterList = " + chapterList);
        System.out.println("classDTOList = " + classDTOList);
        System.out.println("quizList = " + quizList);
        System.out.println("viewQuizList = " + viewQuizList);

        mv.addObject("redirect:/request/list");
        return mv;
    }
}
