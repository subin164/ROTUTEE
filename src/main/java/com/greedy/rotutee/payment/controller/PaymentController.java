package com.greedy.rotutee.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * packageName : com.greedy.rotutee.payment.controller
 * fileName : PaymentController
 * author : soobeen
 * date : 2022-05-07
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-07          soobeen     최초 생성
 */

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping(value="/request")
    public String PaymentRequset(){

        return "payment/paymentRequest";
    }


}
