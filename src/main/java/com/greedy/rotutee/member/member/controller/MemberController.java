package com.greedy.rotutee.member.member.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.member.member.dto.LectureCategoryDTO;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {

        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public void memberLoginPage() {}

    @GetMapping("/regist")
    public void memberRegistPage() {}

    @PostMapping("/regist")
    public String registMember(@ModelAttribute MemberDTO member, RedirectAttributes rttr) {

        memberService.registMember(member);

        rttr.addFlashAttribute("message", "회원가입에 성공하셨습니다 로그인을 해주세요");

        return "redirect:/member/login";
    }

    @GetMapping(value = "/lecture/category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<LectureCategoryDTO> findLectureCategoryList() {

        return memberService.findLectureCategoryList();
    }

    @GetMapping("/duplicate")
    @ResponseBody
    public boolean duplicateEmail(@RequestParam String checkEmail) {

        return memberService.duplicateEmail(checkEmail);
    }

    @GetMapping("/findpwd")
    public void findMemberPwd() {}

    @PostMapping("/findpwd")
    public String findMemberPwd(@ModelAttribute MemberDTO member, RedirectAttributes rttr) {

        memberService.findMemberPwd(member);

        rttr.addFlashAttribute("message", "비밀번호 변경에 성공하셨습니다 로그인을 해주세요");

        return "redirect:/member/login";
    }

    @GetMapping("/modifypwd")
    public void modifypwdPage() {}

    @PostMapping("/modifypwd")
    public String modifyPassword(@RequestParam("password") String password, @RequestParam("modifyPassword") String modifyPassword,
                                 @AuthenticationPrincipal CustomUser loginMember, RedirectAttributes rttr) {

        if(!passwordEncoder.matches(password, loginMember.getPwd())) {

            rttr.addFlashAttribute("message", "비밀번호가 일치하지 않습니다.");

            return "redirect:/member/modifypwd";
        }

        memberService.modifyPassword(loginMember, modifyPassword);

        rttr.addFlashAttribute("message", "비밀번호가 변경되었습니다 다시 로그인 해주세요");

        return "redirect:/member/logout";
    }

    @GetMapping("/list")
    public ModelAndView findMemberList(ModelAndView mv, @PageableDefault Pageable pageable) {

        Page<MemberDTO> memberList = memberService.findAllMember(pageable);

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(memberList);

        mv.addObject("memberList", memberList);
        mv.addObject("paging", paging);
        mv.setViewName("/member/list");

        return mv;
    }

    @GetMapping("/detail/{memberNo}")
    public ModelAndView memberDetail(ModelAndView mv, @PathVariable int memberNo) {

        MemberDTO member = memberService.findMember(memberNo);

        mv.addObject("member", member);
        mv.setViewName("/member/detail");

        return mv;
    }
}
