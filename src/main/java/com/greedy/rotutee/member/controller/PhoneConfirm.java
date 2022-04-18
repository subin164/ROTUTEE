package com.greedy.rotutee.member.controller;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class PhoneConfirm {

    public static void certifiedPhoneNumber(String phoneNumber, String cerNum) {

        String apiKey = "NCSBXB8ZBYC7ENPQ";
        String apiSecret = "YU8JRYALJRGYY0ALV69RWM1BGDEB33TU";
        Message coolsms = new Message(apiKey, apiSecret);

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phoneNumber);
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
}
