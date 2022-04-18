package com.greedy.rotutee.certification.phone.Controller;


import com.greedy.rotutee.certification.email.service.EmailServiceImpl;
import com.greedy.rotutee.certification.phone.PhoneConfirm;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Random;

@Controller
@RequestMapping("/phone")
public class PhoneController {

    public static final String cerNum = createKey();

    @PostMapping("/message")
    @ResponseBody
    public void certifiedPhoneNumber(@RequestParam String phoneNum) {

        System.out.println("phoneNum = " + phoneNum);
        System.out.println("cerNum = " + cerNum);

        String apiKey = "NCSBXB8ZBYC7ENPQ";
        String apiSecret = "YU8JRYALJRGYY0ALV69RWM1BGDEB33TU";
        Message coolsms = new Message(apiKey, apiSecret);

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phoneNum);
        params.put("from", "01032828120");
        params.put("type", "SMS");
        params.put("text", "휴대폰 인증 : 인증번호는" + "[" + cerNum + "]" + "입니다.");
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toJSONString());
        } catch (CoolsmsException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/verifyCode")
    @ResponseBody
    public int verifyCode(@RequestParam String checkCode) {
        int result = 0;
        System.out.println("code : "+checkCode);
        System.out.println("code match : "+ EmailServiceImpl.ePw.equals(checkCode));
        if(PhoneController.cerNum.equals(checkCode)) {
            result =1;
        }

        return result;
    }


    public static String createKey() {

        Random rand = new Random();
        String numStr = "";

        for(int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }

        return numStr;
    }
}
