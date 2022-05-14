package com.greedy.rotutee.coupon.controller;

import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.coupon.dto.CouponDTO;
import com.greedy.rotutee.coupon.service.CouponService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

/**
 * The type CouponController.
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {

    /**
     * The Coupon service.
     */
    private final CouponService couponService;

    /**
     * Instantiates a new Coupon controller.
     *
     * @param couponService the coupon service
     */
    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }


    /**
     * methodName : couponList
     * author : SeoYoung Kim
     * description :
     *
     * @param mv
     * @param coupon   dto
     * @param pageable
     * @return model and view
     */
    @GetMapping("/list")
    public ModelAndView couponList(ModelAndView mv, CouponDTO couponDTO, Pageable pageable) {


        Page<CouponDTO> couponList = couponService.findCouponList(couponDTO, pageable);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(couponList);


        mv.addObject("couponList", couponList);
        mv.addObject("paging", paging);
        mv.setViewName("/coupon/list");

        return mv;
    }

    /**
     * methodName : couponRegist
     * author : SeoYoung Kim
     * description :
     *
     * @param coupon dto
     * @return string
     */
    @PostMapping("/regist")
    public String couponRegist(CouponDTO couponDTO) {

        couponDTO.setPublishCouponStatus("N");

        couponDTO.setPublishCouponRegistDate(new Date(System.currentTimeMillis()));

        couponService.couponRegist(couponDTO);


        return "redirect:/coupon/list";
    }

    /**
     * methodName : couponRemove
     * author : SeoYoung Kim
     * description :
     *
     * @param coupon array
     */
    @PostMapping(value = "/remove")
    @ResponseBody
    public void couponRemove(
            @RequestParam(value = "couponArray[]") List<String> couponArray) {

        couponService.findRemoveList(couponArray);
    }

    /**
     * methodName : couponModify
     * author : SeoYoung Kim
     * description :
     *
     * @param coupon dto
     * @return string
     */
    @PostMapping("/modify")
    public String couponModify(CouponDTO couponDTO) {

        System.out.println("couponDTO : " + couponDTO);

        couponService.couponModify(couponDTO);

        return "redirect:/coupon/list";
    }

    /**
     * methodName : publishCoupon
     * author : SeoYoung Kim
     * description :
     *
     * @param coupon no list
     * @return string
     */
    @PostMapping("/publish")
    public String publishCoupon(@RequestParam(value = "publishCouponNo") List<String> couponNoList,
                                @RequestParam(value = "publishSelect") int publishSelect,
                                @RequestParam(value = "publishToPersonalMember") String publishToPersonalMember) {

        System.out.println("퍼블리싱ㅋ ㅜ폰 욫어옴 : " + couponNoList);

        System.out.println("publishSelect : " + publishSelect);
        if(publishSelect  == 1){
            System.out.println("전체쿠폰 발행");
        couponService.couponPublishAll(couponNoList);

        }else if(publishSelect == 2){
            System.out.println("개인회원에게 발행");
            System.out.println("개인회원 아이디 : " + publishToPersonalMember);
            couponService.couponSelectPersonal(couponNoList, publishToPersonalMember);
        }

        return "redirect:/coupon/list";
    }

}
