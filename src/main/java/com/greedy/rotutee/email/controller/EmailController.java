package com.greedy.rotutee.email.controller;

import com.greedy.rotutee.email.service.EmailService;
import com.greedy.rotutee.email.service.EmailServiceImpl;
import com.greedy.rotutee.main.controller.member.controller.MemberController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class EmailController {

    @Autowired
    EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @PostMapping("/mail")
    @ResponseBody
    public void emailConfirm(@RequestParam String email)throws Exception {
        logger.info("post emailConfirm");
        System.out.println("전달 받은 이메일 : " + email);
        emailService.sendSimpleMessage(email);
    }
    @PostMapping("/verifyCode")
    @ResponseBody
    public int verifyCode(@RequestParam  String checkCode) {
        logger.info("Post verifyCode");

        int result = 0;
        System.out.println("code : "+checkCode);
        System.out.println("code match : "+ EmailServiceImpl.ePw.equals(checkCode));
        if(EmailServiceImpl.ePw.equals(checkCode)) {
            result =1;
        }

        return result;
    }

}
