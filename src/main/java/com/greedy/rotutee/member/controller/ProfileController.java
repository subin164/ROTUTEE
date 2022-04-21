package com.greedy.rotutee.member.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.member.dto.*;
import com.greedy.rotutee.member.service.FileHandler;
import com.greedy.rotutee.member.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    private final FileHandler fileHandler;

    @Autowired
    public ProfileController(ProfileService profileService, FileHandler fileHandler) {
        this.profileService = profileService;
        this.fileHandler = fileHandler;
    }

    @GetMapping("/profile")
    public ModelAndView profilePage(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();

        MemberDTO memeber = profileService.memberProfile(memberNo);
        AttachedFileDTO attachedFile = profileService.imgTest(0);
        AchievementDTO achievement = profileService.findMemberAchievement(memberNo);

        if(customUser.getMemberRoleList().get(0).getRole().getName().equals("ROLE_TUTOR")){
           TutorInfoDTO tutorInfo = profileService.findTutorInfo(memberNo);

           tutorInfo.setAddress(tutorInfo.getAddress().replace("&", " "));

           mv.addObject("tutorInfo", tutorInfo);
        }

        mv.addObject("achievement", achievement);
        mv.addObject("attachedFile", attachedFile);
        mv.addObject("member", memeber);
        mv.setViewName("/profile/profile");

        return mv;
    }

    @GetMapping("/modify")
    public ModelAndView modifyPage(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();

        MemberDTO memeber = profileService.memberProfile(memberNo);
        AttachedFileDTO attachedFile = profileService.imgTest(0);
        AchievementDTO achievement = profileService.findMemberAchievement(memberNo);

        if(customUser.getMemberRoleList().get(0).getRole().getName().equals("ROLE_TUTOR")){
            TutorInfoDTO tutorInfo = profileService.findTutorInfo(memberNo);
            AddressDTO address = new AddressDTO();

            String[] addresss = tutorInfo.getAddress().split("&");

            address.setZipCode(addresss[0]);
            address.setAddress1(addresss[1]);
            address.setAddress2(addresss[2]);

            System.out.println("addresss = " + addresss);
            
            mv.addObject("address", address);
            mv.addObject("tutorInfo", tutorInfo);
        }

        mv.addObject("achievement", achievement);
        mv.addObject("attachedFile", attachedFile);
        mv.addObject("member", memeber);
        mv.setViewName("/profile/modify");

        return mv;
    }

    @GetMapping(value = "/achievementCategory",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<AchievementDTO> findAchievementCategoryList() {

        return profileService.findAllAchievementCategory();
    }

    @PostMapping("/modify")
    public String modifyProfile(@RequestParam("file") List<MultipartFile> files, @AuthenticationPrincipal CustomUser loginMember,
                                @ModelAttribute MemberDTO member, @ModelAttribute TutorInfoDTO tutorInfo, @ModelAttribute AddressDTO address,
                                RedirectAttributes rttr) throws Exception {

        tutorInfo.setAddress(address.getZipCode() + "&" + address.getAddress1() + "&" + address.getAddress2());

        profileService.modifyProfile(loginMember, member, tutorInfo);

        rttr.addFlashAttribute("message", "프로필 변경에 성공하셨습니다.");

        return "redirect:/profile/profile";
    }

    @GetMapping("/img")
    public ModelAndView imgTest(ModelAndView mv) {

        AttachedFileDTO attachedFile = profileService.imgTest(5);

        System.out.println("attachedFile = " + attachedFile);

        mv.addObject("attachedFile", attachedFile);
        mv.setViewName("/profile/test");

        return mv;
    }

}
