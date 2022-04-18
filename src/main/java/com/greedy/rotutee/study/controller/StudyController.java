package com.greedy.rotutee.study.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.Authentication.service.AuthenticationService;
import com.greedy.rotutee.member.dto.MemberDTO;
import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/regist")
    public ModelAndView studyRegist(ModelAndView mv, StudyDTO study){
//
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        User loaduser = (User) principal;
//
//        CustomUser user = (CustomUser) authenticationService.loadUserByUsername(loaduser.getUsername());
//
//        int memberNo = user.getNo();
//
//        MemberDTO member = new MemberDTO();
//
//        member.setNo(user.getNo());
//        member.setNickname(user.getNickname());
//        System.out.println(memberNo);
//
//        System.out.println(study);
//
//        studyService.studyRegist(study);

        System.out.println("스터디 작성 요청");
        System.out.println(study);

        return null;
    }
}
