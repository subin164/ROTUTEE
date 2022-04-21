package com.greedy.rotutee.lecture.lecture.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.lecture.lecture.dto.*;
import com.greedy.rotutee.lecture.lecture.entity.Chapter;
import com.greedy.rotutee.lecture.lecture.service.LectureMainService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        mv.addObject("lectureList", lectureList);
        mv.setViewName("/lecture/list");
        return mv;
    }

    @GetMapping("search")
    public ModelAndView findApproveLectureBysearchObject(ModelAndView mv, @RequestParam String searchValue, @RequestParam int searchCondition) {

        System.out.println(searchCondition);
        System.out.println(searchValue);

        List<LectureDTO> lectureList = lectureMainService.findApproveLectureBysearchObject(searchCondition, searchValue);

        mv.addObject("lectureList", lectureList);
        mv.setViewName("/lecture/list");
        return mv;
    }

    @GetMapping("detail")
    public ModelAndView findLectureByLectureNo(ModelAndView mv, @RequestParam int lectureNo) {
        System.out.println("lectureNo = " + lectureNo);

        LectureDTO lecture = lectureMainService.findLectureByLectureNo(lectureNo);
        List<ChapterDTO> chapterList = lectureMainService.findChapterListByLectureNo(lectureNo);
        List<LectureReviewDTO> lectureReviewList = lectureMainService.findReviewListByLectureNo(lectureNo);
        int lectureMemberCount = lectureMainService.findMemberCountByLectureNo(lectureNo);
        int gradeAverage = lectureMainService.findGradeAverageByLectureNo(lectureNo);

        System.out.println("lectureReviewList = " + lectureReviewList);

        mv.addObject("gradeAverage", gradeAverage);
        mv.addObject("lectureMemberCount", lectureMemberCount);
        mv.addObject("lectureReviewList", lectureReviewList);
        mv.addObject("chapterList", chapterList);
        mv.addObject("lecture", lecture);
        mv.setViewName("/lecture/detail");

        return mv;
    }

    @PostMapping("writereview")
    public ModelAndView writeReview(ModelAndView mv, @RequestParam(defaultValue = "0") int rating
                                                   , @RequestParam String content
                                                   , @RequestParam int lectureNo
                                                   , @AuthenticationPrincipal CustomUser customUser
                                                   , RedirectAttributes rttr) {
        if(customUser == null) {
            rttr.addFlashAttribute("message", "로그인이 필요한 서비스입니다.");
        } else {

            int memberNo = customUser.getNo();

            if(rating == 0) {
                rttr.addFlashAttribute("message", "평점을 입력해주세요.");
            } else if(customUser != null && lectureMainService.findMemberInLecture(memberNo, lectureNo) == null) {
                rttr.addFlashAttribute("message", "수강하신 회원만 작성할 수 있습니다.");
            } else {
                lectureMainService.registLectureReview(rating, content, lectureNo, memberNo);
                rttr.addFlashAttribute("message", "강의평을 작성하였습니다.");
            }
        }

        mv.setViewName("redirect:/lecture/detail?lectureNo=" + lectureNo);
        return mv;
    }
}
