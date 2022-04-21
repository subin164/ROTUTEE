package com.greedy.rotutee.lecture.lecture.controller;

import com.greedy.rotutee.lecture.lecture.dto.ChapterDTO;
import com.greedy.rotutee.lecture.lecture.dto.LectureDTO;
import com.greedy.rotutee.lecture.lecture.dto.LectureReviewDTO;
import com.greedy.rotutee.lecture.lecture.entity.Chapter;
import com.greedy.rotutee.lecture.lecture.service.LectureMainService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}
