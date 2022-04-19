package com.greedy.rotutee.member.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.member.dto.MemberDTO;
import com.greedy.rotutee.member.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * packageName : com.greedy.rotutee.profile.controller
 * fileName : ProfileController
 * author : 7sang
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 7sang 최초 생성
 */

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public ModelAndView profilePage(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();

        System.out.println("memberNo = " + memberNo);

        MemberDTO memeber = profileService.memberProfile(memberNo);

        System.out.println("memeber = " + memeber);

        mv.addObject("member", memeber);
        mv.setViewName("/profile/profile");

        return mv;
    }
}
