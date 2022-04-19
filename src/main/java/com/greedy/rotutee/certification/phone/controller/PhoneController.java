package com.greedy.rotutee.certification.phone.controller;


import com.greedy.rotutee.certification.phone.service.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/phone")
public class PhoneController {

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {

        this.phoneService = phoneService;
    }


    @PostMapping("/message")
    @ResponseBody
    public void certifiedPhoneNumber(@RequestParam String phoneNum) throws Exception {

        System.out.println("phoneNum = " + phoneNum);

        phoneService.createMessage(phoneNum);
    }

    @PostMapping("/verifyCode")
    @ResponseBody
    public int verifyCode(@RequestParam String checkCode) {

        int result = 0;
        if(phoneService.cerNum.equals(checkCode)) {
            result =1;
        }

        return result;
    }



}
