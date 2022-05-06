package com.greedy.rotutee.payment.controller;

import org.apache.maven.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpResponse;

/**
 * packageName : com.greedy.rotutee.payment.controller
 * fileName : PaymentController
 * author : soobeen
 * date : 2022-05-05
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-05          soobeen     최초 생성
 */

@Controller
@RequestMapping("/bootpay")
public class PaymentController {

    @GetMapping("/pay/confirm")
    public String BootRequest(@RequestParam("receipt_id") String receipt_id)throws Exception{

        String getDateJson = "";
        BootPayOrderDto dto = null;
        String rest_application_id="발급 받은 id";
        String private_key="발급 받은 인증키";

        BootpayApi api = new BootpayApi(
                rest_application_id,
                private_key
        );
        api.getAccessToken();
        try{
            HttpResponse res = api.verify(recipt_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        long orderId = Long.parseLong(dto.getDate().getOrder_id());
        Order order = orderService.findOrder()

        return "payment/paymentRequest";
    }
}
