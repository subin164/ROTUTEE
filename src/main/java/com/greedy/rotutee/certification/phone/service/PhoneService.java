package com.greedy.rotutee.certification.phone.service;


import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class PhoneService {

    public static final String cerNum = createKey();

    public void createMessage(String phoneNum) throws Exception {

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

    public static String createKey() {

        Random rand = new Random();
        String numStr = "";

        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }

        return numStr;
    }
}
