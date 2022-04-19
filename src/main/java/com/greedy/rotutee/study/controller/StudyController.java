package com.greedy.rotutee.study.controller;


import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.Authentication.service.AuthenticationService;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/study")
public class StudyController {

    private final StudyService studyService;
    private final AuthenticationService authenticationService;

    @Autowired
    public StudyController(StudyService studyService, AuthenticationService authenticationService) {
        this.studyService = studyService;
        this.authenticationService = authenticationService;
    }


    @GetMapping("/list")
    public ModelAndView studyList(ModelAndView mv, @PageableDefault Pageable pageable){

        System.out.println("pageable : " + pageable);

        Page<StudyDTO> studyList = studyService.findStudyList(pageable);

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(studyList);

        System.out.println("리스트 : " + studyList);

        mv.addObject("studyList", studyList);
        mv.addObject("paging", paging);
        mv.setViewName("study/studyList");

        return mv;
    }

    @PostMapping("/regist")
    public String studyRegist(StudyDTO studyDTO, HttpServletRequest request) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User loaduser = (User) principal;

        CustomUser user = (CustomUser) authenticationService.loadUserByUsername(loaduser.getUsername());

        int memberNo = user.getNo();

        java.sql.Date nowDate = Date.valueOf(LocalDate.now());

        studyDTO.setMemberNo(memberNo);
        studyDTO.setWriteDate(nowDate);
        studyDTO.setTag(studyDTO.getStudyNo());
        studyDTO.setStatus("Y");

        studyService.studyRegist(studyDTO);

        return "redirect:/study/list";


    }
}
