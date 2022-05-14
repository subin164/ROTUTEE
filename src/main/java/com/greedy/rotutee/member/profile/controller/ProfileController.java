package com.greedy.rotutee.member.profile.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.dto.AchievementDTO;
import com.greedy.rotutee.member.member.dto.AddressDTO;
import com.greedy.rotutee.member.profile.dto.AttachedFileDTO;
import com.greedy.rotutee.member.profile.dto.TutorInfoDTO;
import com.greedy.rotutee.member.profile.service.ProfileFileHandler;
import com.greedy.rotutee.member.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
    private final AuthenticationManager authenticationManager;

    @Autowired
    public ProfileController(ProfileService profileService, ProfileFileHandler profileFileHandler, AuthenticationManager authenticationManager) {
        this.profileService = profileService;
        this.profileFileHandler = profileFileHandler;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/profile")
    public ModelAndView profilePage(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();

        //회원 정보 조회
        MemberDTO member = profileService.memberProfile(memberNo);
        //회원 프로필사진 정보 조회
        AttachedFileDTO attachedFile = profileService.findMemberProfile(memberNo);

        //조회된 회원이 튜터일 경우 튜터 정보도 같이 반환해줌
        if(member.getMemberRoleList().get(0).getRole().getName().equals("ROLE_TUTOR")){
            TutorInfoDTO tutorInfo = profileService.findTutorInfo(memberNo);

            //정보를 아직 입력하지 않은 튜터라면 비어있는 값을 반환 (NPT 방지)
            if(tutorInfo != null) {
                tutorInfo.setAddress(tutorInfo.getAddress().replace("&", " "));
            } else {
                tutorInfo = new TutorInfoDTO();
                tutorInfo.setAddress("미입력");
                tutorInfo.setAccountNumber("미입력");
                tutorInfo.setBankName("미입력");
            }
           mv.addObject("tutorInfo", tutorInfo);
        }

        mv.addObject("attachedFile", attachedFile);
        mv.addObject("member", member);
        mv.setViewName("/profile/profile");

        return mv;
    }


    @GetMapping("/modify")
    public ModelAndView modifyPage(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();

        //회원 정보 조회
        MemberDTO memeber = profileService.memberProfile(memberNo);
        //회원 프로필사진 정보 조회
        AttachedFileDTO attachedFile = profileService.findMemberProfile(memberNo);

        //조회된 회원이 튜터일 경우
        if(customUser.getMemberRoleList().get(0).getRole().getName().equals("ROLE_TUTOR")){
            TutorInfoDTO tutorInfo = profileService.findTutorInfo(memberNo);

            //튜터 정보가 null이 아닐경우
            if(tutorInfo != null) {
                AddressDTO address = new AddressDTO();
                //'&' 기준으로 나뉘어 저장된 정보를 배열로 읽어옴
                String[] addresss = tutorInfo.getAddress().split("&");

                //배열로 나뉘어 주소를 상세히 저장 가능
                address.setZipCode(addresss[0]);
                address.setAddress1(addresss[1]);
                address.setAddress2(addresss[2]);

                mv.addObject("address", address);
                mv.addObject("tutorInfo", tutorInfo);
            }
            //튜터 정보가 null일 경우 미입력 출력
            else {
                tutorInfo = new TutorInfoDTO();
                AddressDTO address = new AddressDTO();

                address.setZipCode("미입력");
                address.setAddress1("미입력");
                address.setAddress2("미입력");

                tutorInfo.setBankName("미입력");
                tutorInfo.setAccountNumber("미입력");

                mv.addObject("address", address);
                mv.addObject("tutorInfo", tutorInfo);
            }

        }

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

        //주소를 상세히 나뉘어 저장
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

        //사진 업로드 핸들러 메소드를 이용해 사진 업로드
        profileService.profileUpload(profileFileHandler.profileFileUpload(uploadFile, loginMember.getNo()));

        rttr.addFlashAttribute("message", "사진 변경에 성공하셨습니다.");

        return "redirect:/profile/modify";
    }


}
