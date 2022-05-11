package com.greedy.rotutee.notice.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.notice.dto.NoticeDTO;
import com.greedy.rotutee.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * packageName : com.greedy.rotutee.notice.controller
 * fileName : NoticeController
 * author : SeoYoung
 * date : 2022-05-11
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-11 SeoYoung 최초 생성
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    private NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/list")
    public ModelAndView findNoticeList(ModelAndView mv, @PageableDefault Pageable pageable, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();

        Page<NoticeDTO> notices = noticeService.findNoticeList(memberNo, pageable);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(notices);

        mv.addObject("notices", notices);
        mv.addObject("paging", paging);
        mv.setViewName("notice/list");

        return mv;
    }
}
