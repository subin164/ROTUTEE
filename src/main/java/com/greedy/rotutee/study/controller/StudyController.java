package com.greedy.rotutee.study.controller;


import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.Authentication.service.AuthenticationService;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;



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

    //    모집글 전체 조회
    @GetMapping("/list")
    public ModelAndView studyList(ModelAndView mv, @PageableDefault Pageable pageable) {

        Page<StudyDTO> studyList = studyService.findStudyList(pageable);

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(studyList);


        mv.addObject("studyList", studyList);
        mv.addObject("paging", paging);

        mv.setViewName("study/list");

        return mv;
    }

    //    모집글 상세페이지
    @GetMapping("/detail")
    public ModelAndView detailPage(ModelAndView mv, HttpServletRequest request, StudyDTO studyDTO, MemberDTO memberDTO) {

        int studyNo = Integer.parseInt(request.getParameter("no"));

        studyDTO.setStudyNo(studyNo);

        StudyDTO studyDetail = studyService.findDetailByStudyNo(studyNo);


        mv.addObject("studyDetail", studyDetail);
        mv.setViewName("study/detail");

        System.out.println("studyDetail = " + studyDetail);

        return mv;
    }

    @PostMapping("/regist")
    public ModelAndView studyRegist(ModelAndView mv, StudyDTO studyDTO, RedirectAttributes rttr, HttpServletRequest request) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User loaduser = (User) principal;

        CustomUser user = (CustomUser) authenticationService.loadUserByUsername(loaduser.getUsername());

        Date nowDate = new Date(System.currentTimeMillis());


        int memberNo = user.getNo();

        String nickname = user.getNickname();

        studyDTO.setStartDate(Date.valueOf("2022-05-05"));
        studyDTO.setMemberNo(memberNo);
        studyDTO.setWriter(nickname);
        studyDTO.setCount(0);
        studyDTO.setStatus("Y");
        studyDTO.setTagNo(1);

        System.out.println(studyDTO);

        studyService.studyRegist(studyDTO);


        rttr.addFlashAttribute("registSuccessMessage", "모집글 등록에 성공하셨습니다!");

        mv.setViewName("redirect:/study/list");

        return mv;
    }
}
