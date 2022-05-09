package com.greedy.rotutee.dashboard.lms.controller;

import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.dashboard.lms.dto.LMSNormalBoardDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSNoticeBoardDTO;
import com.greedy.rotutee.dashboard.lms.service.LMSNoticeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.controller
 * fileName : LMSNoticeController
 * author : SeoYoung
 * date : 2022-05-09
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-09 SeoYoung 최초 생성
 */
@Controller
@RequestMapping("/lecturenotice")
public class LMSNoticeController {

    private LMSNoticeBoardService lmsNoticeBoardService;

    @Autowired
    public LMSNoticeController(LMSNoticeBoardService lmsNoticeBoardService) {
        this.lmsNoticeBoardService = lmsNoticeBoardService;
    }

    @GetMapping("/list")
    public ModelAndView findNoticeList(HttpServletRequest request, ModelAndView mv, @PageableDefault Pageable pageable) {

        HttpSession session = request.getSession();
        int lectureNo = Integer.parseInt(String.valueOf(session.getAttribute("lectureNo")));

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        System.out.println("searchCondition = " + searchCondition);
        System.out.println("searchValue = " + searchValue);

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Page<LMSNoticeBoardDTO> notices = lmsNoticeBoardService.findNoticeList(pageable, searchMap, lectureNo);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(notices);

        mv.addObject("notices", notices);
        mv.addObject("paging", paging);
        mv.setViewName("dashboard/lms/noticeboard");

        return mv;
    }
}
