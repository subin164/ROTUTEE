package com.greedy.rotutee.coupon.controller;

import com.greedy.rotutee.coupon.dto.CouponDTO;
import com.greedy.rotutee.coupon.service.CouponService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String couponRegist(CouponDTO couponDTO) {

        couponDTO.setCouponStatus("N");

        couponDTO.setCouponRegistDate(new Date(System.currentTimeMillis()));

        couponService.couponRegist(couponDTO);


        return "redirect:/coupon/list";
    }

    @PostMapping(value = "/remove")
    @ResponseBody
    public void couponRemove(
            @RequestParam(value = "couponArray[]") List<String> couponArray) {

        couponService.findRemoveList(couponArray);
    }

    @GetMapping("/modify")
    @ResponseBody
    public String couponModify(CouponDTO couponDTO) {

        System.out.println("couponDTO : " + couponDTO);

        couponService.couponModifyPrev(couponDTO);

        return "redirect:/coupon/list";
    }

}
