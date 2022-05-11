package com.greedy.rotutee.lecture.request.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.lecture.request.dto.*;
import com.greedy.rotutee.lecture.request.service.LectureRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/request")
public class LectureRequestController {

    private final LectureRequestService lectureRequestService;

    @Autowired
    public LectureRequestController(LectureRequestService lectureRequestService) {
        this.lectureRequestService = lectureRequestService;
    }


    @GetMapping("list")
    public ModelAndView findMyLectureList(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();
        List<LectureDTO> lectureList = lectureRequestService.findLectureListBytutorNo(memberNo);

        mv.addObject("lectureList", lectureList);
        mv.setViewName("/request/myrequestlist");

        return mv;
    }

    @GetMapping("lecture")
    public String lectureRegistForm() {

        return "request/lecturerequestform";
    }

    @PostMapping("lecture")
    public ModelAndView registLectureOpeningApplication(ModelAndView mv, @ModelAttribute LectureDTO newLecture
                                                                       , @RequestParam int categoryNo
                                                                       , @RequestParam MultipartFile thumbnailImg
                                                                       , @RequestParam MultipartFile bannerImg
                                                                       , @AuthenticationPrincipal CustomUser customUser
                                                                       , RedirectAttributes rttr) throws IOException {

        List<MultipartFile> requestFileList = new ArrayList<>();
        requestFileList.add(thumbnailImg);

        if(!bannerImg.isEmpty()) {
            requestFileList.add(bannerImg);
        }

        newLecture.setFileList(requestFileList);

        int memberNo = customUser.getNo();
        lectureRequestService.registLectureOpeningApplication(newLecture, categoryNo, memberNo);

        rttr.addFlashAttribute("message", "강의 개설 신청을 성공하였습니다.");
        mv.setViewName("redirect:/request/list");

        return mv;
    }

    @GetMapping("lecturelist")
    public ModelAndView findLectureRequestList(ModelAndView mv , @PageableDefault Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchCondition, @RequestParam(required = false, defaultValue = "") String searchValue) {

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Page<LectureDTO> requestList = lectureRequestService.findAllLecture(pageable, searchMap);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(requestList);

        mv.addObject("requestList",requestList);
        mv.addObject("paging", paging);
        mv.setViewName("/request/adminlecturerequestlist");

        return mv;
    }

    @GetMapping("lecturedetail")
    public ModelAndView findRequestLetureDetail(ModelAndView mv, @RequestParam int lectureNo) {

        LectureDTO lecture = lectureRequestService.findLectureByLectureNo(lectureNo);
        List<ChapterDTO> chapterList = lecture.getChapterList();

        mv.addObject("chapterList", chapterList);
        mv.addObject("lecture", lecture);
        mv.setViewName("request/lecturerequestdetail");

        return mv;
    }

    @PostMapping("approve")
    public ModelAndView approveRequestLecture(ModelAndView mv, @RequestParam int lectureNo, RedirectAttributes rttr) {

        lectureRequestService.modifyLectureApprovalStatus(lectureNo);

        rttr.addFlashAttribute("message", "강의가 개설되었습니다.");
        mv.setViewName("redirect:/request/lecturelist");

        return mv;
    }

    @GetMapping("reject")
    public ModelAndView rejectRequestLecture(ModelAndView mv, @RequestParam int lectureNo
                                                            , @RequestParam int rejectionCategoryNo
                                                            , RedirectAttributes rttr) {

        lectureRequestService.rejectLecture(lectureNo, rejectionCategoryNo);

        rttr.addFlashAttribute("message", "해당 강의를 거절하였습니다.");
        mv.setViewName("redirect:/request/lecturelist");

        return mv;
    }

}
