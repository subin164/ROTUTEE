package com.greedy.rotutee.basket.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.basket.dto.ClassBasketDTO;
import com.greedy.rotutee.basket.service.BasketService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * packageName      : com.greedy.rotutee.basket.controller
 * fileName         : BasketController
 * author           : SEOK
 * date             : 2022-05-03
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-03      SEOK         최초 생성
 */
@Controller
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/regist/{lectureNo}")
    public ModelAndView addLecture(ModelAndView mv, @PathVariable int lectureNo
                                                  , @AuthenticationPrincipal CustomUser customUser
                                                  , RedirectAttributes rttr) {

        int memberNo = customUser.getNo();
        basketService.registLectureToCart(lectureNo, memberNo);

        rttr.addFlashAttribute("message", "수강바구니에 추가되었습니다.");
        mv.setViewName("redirect:/lecture/detail?lectureNo=" + lectureNo);
        return mv;
    }

    @GetMapping("/list")
    public ModelAndView findBasketList(ModelAndView mv, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();
        List<ClassBasketDTO> cartList = basketService.findByMemberNo(memberNo);

        System.out.println(cartList);

        mv.setViewName("basket/basketlist");
        return mv;
    }

}
