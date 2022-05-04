package com.greedy.rotutee.coupon.controller;

import com.greedy.rotutee.coupon.dto.CouponDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/coupon")
public class CouponController {


    @GetMapping("/list")
    public String couponList(){
        return "/coupon/list";
    }

    @PostMapping("/regist")
    public ModelAndView couponRegist(ModelAndView mv, CouponDTO couponDTO){

        couponDTO.setCouponStatus("N");
        java.sql.Date expirationDate;
        System.out.println("couponDTO : " + couponDTO);

        mv.setViewName("/coupon/list");

        return mv;
    }
}
