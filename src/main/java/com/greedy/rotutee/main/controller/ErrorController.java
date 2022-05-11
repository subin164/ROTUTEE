package com.greedy.rotutee.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @PostMapping("/login")
    public String loginError(RedirectAttributes rttr) {

        rttr.addFlashAttribute("message", "아이디 혹은 비밀번호를 확인해주세요.");
        return "redirect:/member/login";
    }

    @GetMapping("/denied")
    public String accessDenied(){
        return "/error/denied";
    }

}
