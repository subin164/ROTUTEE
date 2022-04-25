package com.greedy.rotutee.study.controller;


import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.Authentication.service.AuthenticationService;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.dto.TagDTO;
import com.greedy.rotutee.study.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/study")
public class StudyController {

    private final StudyService studyService;
    private final AuthenticationService authenticationService;
    private final MessageSource messageSource;

    @Autowired
    public StudyController(StudyService studyService, AuthenticationService authenticationService, MessageSource messageSource) {
        this.studyService = studyService;
        this.authenticationService = authenticationService;
        this.messageSource = messageSource;
    }

    //    모집글 조회
    @GetMapping("/list")
    public ModelAndView studyList(ModelAndView mv, @PageableDefault Pageable pageable) {

        Page<StudyDTO> studyList = studyService.findStudyList(pageable);

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(studyList);

        List<TagDTO> studyTagList = studyService.findStudyTagList();


        mv.addObject("studyList", studyList);
        mv.addObject("paging", paging);
        mv.addObject("studyTagList", studyTagList);

        mv.setViewName("study/list");

        return mv;
    }

    //    모집글 상세페이지
    @GetMapping("/detail")
    public ModelAndView detailPage(ModelAndView mv, HttpServletRequest request, StudyDTO studyDTO) {

        int studyNo = Integer.parseInt(request.getParameter("no"));

        studyDTO.setStudyNo(studyNo);

        StudyDTO studyDetail = studyService.findDetailByStudyNo(studyNo);


        mv.addObject("studyDetail", studyDetail);
        mv.setViewName("study/detail");


        return mv;
    }

    //    모집글 작성
    @PostMapping("/regist")
    @ResponseBody
    public ModelAndView studyRegist(ModelAndView mv, StudyDTO studyDTO, String inputTag) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User loaduser = (User) principal;

        CustomUser user = (CustomUser) authenticationService.loadUserByUsername(loaduser.getUsername());

        List<String> addTagList = new ArrayList<>();

        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setNo(user.getNo());
        studyDTO.setStartDate(new Date(System.currentTimeMillis()));
        studyDTO.setStatus("Y");

        String tagArray[] = inputTag.split("#");

        System.out.println("tagaaa" + tagArray);

        for (int i = 0; i < tagArray.length; i++) {
            addTagList.add(tagArray[i]);
        }

        System.out.println("adfffffffffff" + addTagList);

        mv.setViewName("redirect:/study/list");

        studyService.studyRegist(studyDTO, addTagList, memberDTO);

        return mv;
    }


    // 모집글 삭제
    @GetMapping("/remove")
    public ModelAndView studyRemove(ModelAndView mv, HttpServletRequest request) {

        int studyNo = Integer.parseInt(request.getParameter("no"));

        studyService.studyRemove(studyNo);

        mv.setViewName("/study/list");

        return mv;
    }
}
