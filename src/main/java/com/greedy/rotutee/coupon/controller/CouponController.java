package com.greedy.rotutee.coupon.controller;

import com.greedy.rotutee.coupon.dto.CouponDTO;
import com.greedy.rotutee.coupon.service.CouponService;

import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }


    @GetMapping("/list")
    public ModelAndView couponList(ModelAndView mv, CouponDTO couponDTO, Pageable pageable) {


        Page<CouponDTO> couponList = couponService.findCouponList(couponDTO, pageable);


        mv.addObject("couponList", couponList);
        mv.setViewName("/coupon/list");

        return mv;
    }

    @PostMapping("/regist")
    public ModelAndView couponRegist(ModelAndView mv, CouponDTO couponDTO) {

        couponDTO.setCouponStatus("N");

        couponDTO.setCouponRegistDate(new Date(System.currentTimeMillis()));

        couponService.couponRegist(couponDTO);

        mv.setViewName("/coupon/list");

        return mv;
    }

    @PostMapping("/remove")
    @ResponseBody
    public void couponRemove(HttpServletRequest request){

        System.out.println("요청은 왔느냐");

    }

}
