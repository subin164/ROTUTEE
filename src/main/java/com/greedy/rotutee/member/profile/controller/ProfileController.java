package com.greedy.rotutee.member.profile.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.dto.AchievementDTO;
import com.greedy.rotutee.member.member.dto.AddressDTO;
import com.greedy.rotutee.member.profile.dto.AttachedFileDTO;
import com.greedy.rotutee.member.profile.entity.TutorInfoDTO;
import com.greedy.rotutee.member.profile.service.ProfileFileHandler;
import com.greedy.rotutee.member.profile.service.ProfileService;
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
    private final ProfileFileHandler profileFileHandler;

    @Autowired
    public ProfileController(ProfileService profileService, ProfileFileHandler profileFileHandler) {
        this.profileService = profileService;
        this.profileFileHandler = profileFileHandler;
    }

    @GetMapping("/profile")
    public ModelAndView profilePage(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();

        MemberDTO memeber = profileService.memberProfile(memberNo);
        AttachedFileDTO attachedFile = profileService.findMemberProfile(memberNo);
//        AchievementDTO achievement = profileService.findMemberAchievement(memberNo);

        if(customUser.getMemberRoleList().get(0).getRole().getName().equals("ROLE_TUTOR")){
           TutorInfoDTO tutorInfo = profileService.findTutorInfo(memberNo);

           tutorInfo.setAddress(tutorInfo.getAddress().replace("&", " "));

           mv.addObject("tutorInfo", tutorInfo);
        }

//        mv.addObject("achievement", achievement);
        mv.addObject("attachedFile", attachedFile);
        mv.addObject("member", memeber);
        mv.setViewName("/profile/profile");

        return mv;
    }


    @GetMapping("/modify")
    public ModelAndView modifyPage(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();

        MemberDTO memeber = profileService.memberProfile(memberNo);
        AttachedFileDTO attachedFile = profileService.findMemberProfile(memberNo);
//        AchievementDTO achievement = profileService.findMemberAchievement(memberNo);

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

//        mv.addObject("achievement", achievement);
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
    public String modifyProfile(@AuthenticationPrincipal CustomUser loginMember, @ModelAttribute MemberDTO member,
                                 @ModelAttribute TutorInfoDTO tutorInfo, @ModelAttribute AddressDTO address,
                                RedirectAttributes rttr) {

        tutorInfo.setAddress(address.getZipCode() + "&" + address.getAddress1() + "&" + address.getAddress2());

        profileService.modifyProfile(loginMember, member, tutorInfo);

        rttr.addFlashAttribute("message", "프로필 변경에 성공하셨습니다.");

        return "redirect:/profile/profile";
    }

    @GetMapping("/imgmodify")
    public void imgModifyPage() {}

    @PostMapping("/imgmodify")
    public String modifyProfileImg(@RequestParam("uploadFile") MultipartFile uploadFile, RedirectAttributes rttr,
                                   @AuthenticationPrincipal CustomUser loginMember) throws Exception {

        profileService.profileUpload(profileFileHandler.profileFileUpload(uploadFile, loginMember.getNo()));

        rttr.addFlashAttribute("message", "사진 변경에 성공하셨습니다.");

        return "redirect:/profile/modify";
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
