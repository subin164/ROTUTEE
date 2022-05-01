package com.greedy.rotutee.lecture.request.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.lecture.request.dto.*;
import com.greedy.rotutee.lecture.request.entity.Lecture;
import com.greedy.rotutee.lecture.request.service.LectureRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        for(LectureDTO lecture : lectureList) {
            System.out.println("lecture.getImageList().size() = " + lecture.getImageList().size());

            List<AttachedFileDTO> fileList = lecture.getImageList();
            for(AttachedFileDTO file : fileList) {
                System.out.println("file.getStorageFile() = " + file.getStorageFile());
            }

        }

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
                                                                       , @AuthenticationPrincipal CustomUser customUser) throws IOException {

        List<MultipartFile> requestFileList = new ArrayList<>();
        requestFileList.add(thumbnailImg);
        if(!bannerImg.isEmpty()) {
            requestFileList.add(bannerImg);
        }

        newLecture.setFileList(requestFileList);

        int memberNo = customUser.getNo();
        lectureRequestService.registLectureOpeningApplication(newLecture, categoryNo, memberNo);

        mv.setViewName("redirect:/request/list");
        return mv;
    }

    @GetMapping("lecturelist")
    public ModelAndView findLectureRequestList(ModelAndView mv) {

        List<LectureDTO> requestList = lectureRequestService.findStatusOfLectureIsWaiting();
        List<LectureDTO> recordList = lectureRequestService.findStatusOfLectureIsNotWaiting();

        mv.addObject("requestList",requestList);
        mv.addObject("recordList", recordList);
        mv.setViewName("request/adminlecturerequestlist");
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
    public ModelAndView approveRequestLecture(ModelAndView mv, @RequestParam int lectureNo) {

        lectureRequestService.modifyLectureApprovalStatus(lectureNo);

        mv.setViewName("redirect:/request/lecturelist");
        return mv;
    }
}
