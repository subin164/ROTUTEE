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
import java.util.Arrays;
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

    /*
     * writer : 김형경
     * writeDate : 22/04/18 ~ 22/04/26
     * title : 모집글 조회
     * content : 스터디 모집글 작성 컨트롤러 요청으로 들어온 입력값을 서비스로 보냄
     * */
    @GetMapping("list")
    public ModelAndView StudyList(ModelAndView mv, HttpServletRequest request, Pageable pageable) {

        String searchCondition = request.getParameter("searchCondition");
        String searchTag = request.getParameter("searchTag");

        Page<StudyDTO> studyList = studyService.findByStudyList(searchCondition, searchTag, pageable);

        mv.addObject("studyList", studyList);

        mv.setViewName("/study/list");

        return mv;
    }

    /*
     * writer : 김형경
     * writeDate : 22/04/18 ~ 22/04/26
     * title : 모집글 작성
     * content : 스터디 모집글 작성 컨트롤러 요청으로 들어온 입력값을 서비스로 보냄
     * */
    @PostMapping("/regist")
    public ModelAndView studyRegist(ModelAndView mv, StudyDTO studyDTO, String inputTag) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = (User) principal;

        CustomUser customUser = (CustomUser) authenticationService.loadUserByUsername(user.getUsername());

        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setNo(customUser.getNo());

        studyDTO.setStartDate(new Date(System.currentTimeMillis()));
        studyDTO.setWriter(memberDTO);
        studyDTO.setCount(0);
        studyDTO.setStatus("Y");

        String[] tagArray = inputTag.split("#");


        List<String> tagList = new ArrayList<>();

        for (int i = 1; i < tagArray.length; i++) {
            tagList.add(tagArray[i].trim());
        }

        System.out.println("tagList = " + tagList);
        System.out.println("studyDTO 값 확인 : " + studyDTO);

        studyService.studyRegist(studyDTO, tagList);

        mv.setViewName("/study/list");

        return mv;
    }

}