package com.greedy.rotutee.lecture.lecture.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.lecture.lecture.dto.*;
import com.greedy.rotutee.lecture.lecture.service.LectureMainService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * packageName      : com.greedy.rotutee.lecture.lecture.controller
 * fileName         : LectureMainController
 * author           : SEOK
 * date             : 2022-04-20
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-04-20      SEOK         최초 생성
 */
@Controller
@RequestMapping("/lecture")
public class LectureMainController {
    
    private final LectureMainService lectureMainService;

    public LectureMainController(LectureMainService lectureMainService) {
        this.lectureMainService = lectureMainService;
    }

    /**
     * methodName : findAllApproveLectures
     * author : SEOK
     * description : 사용자가 조회 가능한 강의 목록 조회 기능
     *
     * @Param ModelAndView mv
     * @Param String categoryName 강의 카테고리 이름
     * @Return ModelAndView mv
     * */
    @GetMapping("/list")
    public ModelAndView findAllApproveLectures(ModelAndView mv, @RequestParam(required = false, defaultValue = "null") String categoryName) {

        if(categoryName.equals("null")) {
            List<LectureDTO> lectureList = lectureMainService.findAllLecture();

            mv.addObject("lectureList", lectureList);
            mv.setViewName("lecture/lecturelist");
        } else {
            List<LectureDTO> lectureList = lectureMainService.findLectureByCategoryName(categoryName);

            mv.addObject("lectureList", lectureList);
            mv.setViewName("lecture/lecturelist");
        }

        return mv;
    }

    /**
     * methodName : findApproveLectureBysearchObject
     * author : SEOK
     * description : 검색바를 이용한 강의명 또는 튜터명으로 검색 기능
     *
     * @Param ModelAndView mv
     * @Param String searchValue 검색어
     * @Param int searchCondition 검색조건
     * @Return ModelAndView mv
     * */
    @GetMapping("/search")
    public ModelAndView findApproveLectureBysearchObject(ModelAndView mv, @RequestParam String searchValue, @RequestParam int searchCondition) {

        List<LectureDTO> lectureList = lectureMainService.findApproveLectureBysearchObject(searchCondition, searchValue);

        mv.addObject("lectureList", lectureList);
        mv.setViewName("lecture/lecturelist");

        return mv;
    }

    /**
     * methodName : findLectureByLectureNo
     * author : SEOK
     * description : 강의 번호로 해당 강의의 정보, 강의평, 강의평점 평균 조회 기능
     *
     * @Param ModelAndView mv
     * @Param int lectureNo 강의 번호
     * @Param CustomUser customUser 로그인된 회원 객체
     * @Return ModelAndView mv
     * */
    @GetMapping("/detail")
    public ModelAndView findLectureByLectureNo(ModelAndView mv, @RequestParam int lectureNo, @AuthenticationPrincipal CustomUser customUser) {

        if(customUser == null) {

            LectureDTO lecture = lectureMainService.findLectureByLectureNo(lectureNo);
            List<ChapterDTO> chapterList = lectureMainService.findChapterListByLectureNo(lectureNo);
            List<LectureReviewDTO> lectureReviewList = lectureMainService.findReviewListByLectureNo(lectureNo);
            int lectureMemberCount = lectureMainService.findMemberCountByLectureNo(lectureNo);
            int gradeAverage = lectureMainService.findGradeAverageByLectureNo(lectureNo);

            mv.addObject("gradeAverage", gradeAverage);
            mv.addObject("lectureMemberCount", lectureMemberCount);
            mv.addObject("lectureReviewList", lectureReviewList);
            mv.addObject("chapterList", chapterList);
            mv.addObject("lecture", lecture);
            mv.setViewName("lecture/lecturedetail");

        } else {

            LectureDTO lecture = lectureMainService.findLectureByLectureNo(lectureNo);
            List<ChapterDTO> chapterList = lectureMainService.findChapterListByLectureNo(lectureNo);
            List<LectureReviewDTO> lectureReviewList = lectureMainService.findReviewListByLectureNo(lectureNo);
            int lectureMemberCount = lectureMainService.findMemberCountByLectureNo(lectureNo);
            int gradeAverage = lectureMainService.findGradeAverageByLectureNo(lectureNo);

            lectureMainService.registInterestDegree(customUser.getNo(), lecture.getCategory());

            mv.addObject("gradeAverage", gradeAverage);
            mv.addObject("lectureMemberCount", lectureMemberCount);
            mv.addObject("lectureReviewList", lectureReviewList);
            mv.addObject("chapterList", chapterList);
            mv.addObject("lecture", lecture);
            mv.setViewName("lecture/lecturedetail");

        }

        return mv;
    }

    /**
     * methodName : writeReview
     * author : SEOK
     * description : 강의평 작성 기능
     *
     * @Param ModelAndView mv
     * @Param int rating 강의평점
     * @Param String content 강의평 내용
     * @Param int lectureNo 강의 번호
     * @Param CustomUser customUser 로그인된 회원 객체
     * @Return ModelAndView mv
     * */
    @PostMapping("/writereview")
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
                
            }  else {
                lectureMainService.registLectureReviewAndPoint(rating, content, lectureNo, memberNo);
                rttr.addFlashAttribute("message", "강의평을 작성하였습니다.");

            }
        }

        mv.setViewName("redirect:/lecture/detail?lectureNo=" + lectureNo);

        return mv;
    }

    /**
     * methodName : modifyReview
     * author : SEOK
     * description : 강의평 수정 기능
     *
     * @Param ModelAndView mv
     * @Param int lectureReviewNo 강의평 번호
     * @Param String lectureReviewContent 수정된 강의평 내용
     * @Return ModelAndView mv
     * */
    @PostMapping(value = "/modifyreview", produces = "application/json; charset=UTF-8")
    public ModelAndView modifyReview(ModelAndView mv, @RequestParam int lectureReviewNo
                                                    , @RequestParam String lectureReviewContent) {

        lectureMainService.modifyReviewContent(lectureReviewNo, lectureReviewContent);

        mv.setViewName("jsonView");

        return mv;
    }

    /**
     * methodName : removeReview
     * author : SEOK
     * description : 강의평 삭제 기능
     *
     * @Param ModelAndView mv
     * @Param int lectureReviewNo 강의평 번호
     * @Return ModelAndView mv
     * */
    @GetMapping("/removereview")
    public ModelAndView removeReview(ModelAndView mv, @RequestParam int lectureReviewNo
                                                    , @RequestParam int lectureNo, RedirectAttributes rttr) {

        lectureMainService.removeReview(lectureReviewNo);

        rttr.addFlashAttribute("message", "강의평을 삭제하였습니다.");
        mv.setViewName("redirect:/lecture/detail?lectureNo=" + lectureNo);

        return mv;
    }
}
