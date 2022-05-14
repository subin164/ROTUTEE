package com.greedy.rotutee.point.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.point.dto.CouponDTO;
import com.greedy.rotutee.point.dto.MemberDTO;
import com.greedy.rotutee.point.dto.PointHistoryDTO;
import com.greedy.rotutee.point.dto.PointProductDTO;
import com.greedy.rotutee.point.entity.Member;
import com.greedy.rotutee.point.entity.PointHistory;
import com.greedy.rotutee.point.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {

        this.pointService = pointService;
    }

    @GetMapping("/list")
    public ModelAndView pointProductList(ModelAndView mv, @AuthenticationPrincipal CustomUser loginMember) {

        List<PointProductDTO> productList = pointService.findAllPointProductList();
        int memberPoint = pointService.findMemberPoint(loginMember.getNo());

        mv.addObject("memberPoint", memberPoint);
        mv.addObject("productList", productList);
        mv.setViewName("/point/list");

        return mv;
    }

    @GetMapping("/roulette")
    public ModelAndView roulettePage(ModelAndView mv, @AuthenticationPrincipal CustomUser loginMember) {

        int memberPoint = pointService.findMemberPoint(loginMember.getNo());
        MemberDTO member = pointService.findMember(loginMember.getNo());

        mv.addObject("memberPoint", memberPoint);
        mv.addObject("member", member);
        mv.setViewName("/point/roulette");

        return mv;
    }

    @PostMapping(value = "/roulette", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int uesRoulette(@AuthenticationPrincipal CustomUser loginMember) {

        int randPoint = (int) (Math.random() * 100 ) + 1;
        pointService.playRoulette(loginMember.getNo(), randPoint);

        return randPoint;
    }

    @GetMapping("/history")
    public ModelAndView pointHistory(ModelAndView mv, @AuthenticationPrincipal CustomUser loginMember, @PageableDefault Pageable pageable) {

        Page<PointHistoryDTO> achieveHistoryList = pointService.findAchievePointHistory(loginMember.getNo(), pageable);
        Page<PointHistoryDTO> useHistoryList = pointService.findUsePointHistory(loginMember.getNo(), pageable);

        System.out.println("achieveHistoryList = " + achieveHistoryList);
        System.out.println("useHistoryList = " + useHistoryList);

        PagingButtonInfo achievePaging = Pagenation.getPagingButtonInfo(achieveHistoryList);
        PagingButtonInfo usePaging = Pagenation.getPagingButtonInfo(useHistoryList);

        mv.addObject("achieveHistoryList", achieveHistoryList);
        mv.addObject("useHistoryList", useHistoryList);
        mv.addObject("achievePaging", achievePaging);
        mv.addObject("usePaging", usePaging);

        mv.setViewName("/point/history");

        return mv;
    }

    @GetMapping("/product/{productNo}")
    public String productExchange(@AuthenticationPrincipal CustomUser longinMember, @PathVariable int productNo, RedirectAttributes rttr) {

        pointService.productExchange(longinMember.getNo(), productNo);

        rttr.addFlashAttribute("message", "상품 교환에 성공하셨습니다.");

        return "redirect:/point/list";
    }

    @GetMapping("/coupons")
    @ResponseBody
    public List<CouponDTO> findCouponList() {

        return pointService.findAllCouponList();
    }

    @PostMapping("/regist")
    public String registPointProduct(@ModelAttribute PointProductDTO pointProduct, @RequestParam("couponNo") int couponNo
                                                                                 , RedirectAttributes rttr) {

        pointProduct.setRemainingNumber(pointProduct.getTotalSalesCount());
        pointProduct.setProductStatus("Y");

        pointService.registPointProduct(pointProduct, couponNo);

        rttr.addFlashAttribute("message", "상품이 등록되었습니다.");

        return "redirect:/point/list";
    }

    @GetMapping("/remove/{productNo}")
    public String removePointProduct(@PathVariable("productNo") int productNo, RedirectAttributes rttr) {

        pointService.removePointProduct(productNo);

        rttr.addFlashAttribute("message", "상품이 삭제되었습니다.");

        return "redirect:/point/list";
    }

    @PostMapping("/modify")
    public String modifyPointProduct(@ModelAttribute PointProductDTO pointProduct, RedirectAttributes rttr) {

        System.out.println("pointProduct = " + pointProduct);

        pointService.modifyPointProduct(pointProduct);

        rttr.addFlashAttribute("message", "상품 정보가 수정되었습니다.");

        return "redirect:/point/list";
    }

}
