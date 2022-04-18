package com.greedy.rotutee.study.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.Authentication.service.AuthenticationService;
import com.greedy.rotutee.member.dto.MemberDTO;
import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.entity.Study;
import com.greedy.rotutee.study.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
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
    public ModelAndView studyList(ModelAndView mv){

        List<StudyDTO> studyList = studyService.findStudyList();

        System.out.println(studyList);

        return mv;
    }


    @PostMapping("/regist")
    public ModelAndView studyRegist(ModelAndView mv, StudyDTO study){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User loaduser = (User) principal;

        CustomUser user = (CustomUser) authenticationService.loadUserByUsername(loaduser.getUsername());

        int memberNo = user.getNo();

        java.sql.Date nowDate = Date.valueOf(LocalDate.now());

        study.setWriteDate(nowDate);
        study.setLimited(5);
        study.setStartDate(nowDate);
        study.setStatus("Y");
        study.setLinked("jk231hkjdsfiops");
        study.setMemberNo(memberNo);
        study.setWriter(new MemberDTO());


        System.out.println(study);
        studyService.studyRegist(study);


        return null;
    }
}
