package com.greedy.rotutee.member.controller;

import com.greedy.rotutee.certification.phone.PhoneConfirm;
import com.greedy.rotutee.member.dto.LectureCategoryDTO;
import com.greedy.rotutee.member.dto.MemberDTO;
import com.greedy.rotutee.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
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


}
