package com.greedy.rotutee.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @PostMapping("/login")
    public String loginError(RedirectAttributes rttr, HttpServletRequest request) {

        String messgae = (String) request.getAttribute("message");

        System.out.println("messgae = " + messgae);

        if("".equals(messgae) || messgae == null) {
            rttr.addFlashAttribute("message", "아이디 혹은 비밀번호를 확인해주세요.");
        } else {
            rttr.addFlashAttribute("message", messgae);
        }

        return "redirect:/member/login";
    }

    @GetMapping("/denied")
    public String accessDenied(){
        return "/error/denied";
    }

    @GetMapping("/logout")
    public String logout(){
        return "/error/logout";
    }

}
