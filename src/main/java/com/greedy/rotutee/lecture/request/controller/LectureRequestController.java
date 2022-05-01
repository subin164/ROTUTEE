package com.greedy.rotutee.lecture.request.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.lecture.request.dto.*;
import com.greedy.rotutee.lecture.request.service.LectureRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
        System.out.println("썸네일 사진 : " + thumbnailImg.getOriginalFilename());
        System.out.println("배너 사진 : " + bannerImg.getOriginalFilename());

        List<MultipartFile> requestFileList = new ArrayList<>();
        requestFileList.add(thumbnailImg);
        if(!bannerImg.isEmpty()) {
            requestFileList.add(bannerImg);
        }
        System.out.println("배열 길이 : " + requestFileList.size());
        newLecture.setFileList(requestFileList);
        List<ChapterDTO> chapterList = newLecture.getChapterList();
        for(ChapterDTO chapter : chapterList) {
            List<ClassDTO> classList = chapter.getClassList();

            for(ClassDTO classDTO : classList) {
                List<MultipartFile> fileList = classDTO.getFileList();

                for(MultipartFile file : fileList) {
                    System.out.println("파일 이름 : " + file.getOriginalFilename());
                }
            }
        }


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
