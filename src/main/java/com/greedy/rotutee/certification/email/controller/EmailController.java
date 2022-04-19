package com.greedy.rotutee.certification.email.controller;

import com.greedy.rotutee.certification.email.service.EmailService;
import com.greedy.rotutee.member.controller.MemberController;
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
    public void emailConfirm(@RequestParam String email) throws Exception {
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
        System.out.println("code match : "+ EmailService.ePw.equals(checkCode));
        if(EmailService.ePw.equals(checkCode)) {
            result =1;
        }

        return result;
    }

}
