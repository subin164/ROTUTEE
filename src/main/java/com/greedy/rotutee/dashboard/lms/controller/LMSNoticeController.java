package com.greedy.rotutee.dashboard.lms.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.dashboard.lms.dto.LMSNormalBoardDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSNoticeBoardDTO;
import com.greedy.rotutee.dashboard.lms.service.LMSNoticeBoardService;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
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

        int lectureNo = 0;
        HttpSession session = request.getSession();
        if(session.getAttribute("lectureNo") != null){
            lectureNo = Integer.parseInt(String.valueOf(session.getAttribute("lectureNo")));
        } else {
            lectureNo = Integer.parseInt(request.getParameter("lectureNo"));
        }


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

    @GetMapping("/detail")
    public ModelAndView findNoticeDetail(ModelAndView mv, HttpServletRequest request) {

        int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));

        LMSNoticeBoardDTO noticeDetail = lmsNoticeBoardService.findNoticeDetail(noticeNo);

        mv.addObject("noticeDetail", noticeDetail);
        mv.setViewName("dashboard/lms/notice/detail");

        return mv;

    }
    @GetMapping("/regist")
    public ModelAndView registNotice(ModelAndView mv, HttpServletRequest request) {

        HttpSession session = request.getSession();
        int lectureNo = (Integer)(session.getAttribute("lectureNo"));

        mv.addObject("lectureNo", lectureNo);
        mv.setViewName("dashboard/lms/notice/regist");

        return mv;
    }
    @PostMapping("/regist")
    public String registNotice(@ModelAttribute LMSNoticeBoardDTO notice, RedirectAttributes rttr, HttpServletRequest request
    , @AuthenticationPrincipal CustomUser customUser) {

        int lectureNo = Integer.parseInt(request.getParameter("lectureNo"));
        int writerMemberNo = customUser.getNo();

        DashboardMemberDTO member = new DashboardMemberDTO();
        member.setMemberNo(writerMemberNo);

        notice.setLectureNo(lectureNo);
        notice.setMember(member);
        notice.setCreatedDate(new Date(System.currentTimeMillis()));
        notice.setDeleteStatus("N");
        notice.setCount(0);
        notice.setCategoryNo(10);
        notice.setReportCount(0);
        notice.setSecretStatus("N");

        lmsNoticeBoardService.registNotice(notice);

        rttr.addFlashAttribute("message", "게시글이 등록되었습니다.");

        return "redirect:/lecturenotice/list";
    }

    @GetMapping("/modify")
    public ModelAndView modifyNotice(ModelAndView mv, HttpServletRequest request) {

        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        LMSNoticeBoardDTO noticeDetail = lmsNoticeBoardService.findNoticeDetail(boardNo);

        mv.addObject("noticeDetail", noticeDetail);
        mv.setViewName("dashboard/lms/notice/modify");

        return mv;

    }
    @PostMapping("/modify")
    public String modifyNotice(@ModelAttribute LMSNoticeBoardDTO notice, RedirectAttributes rttr, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();
        notice.setModifiedDate(new Date(System.currentTimeMillis()));

        lmsNoticeBoardService.modifyNotice(notice);

        rttr.addFlashAttribute("message", "게시글이 수정되었습니다.");
        return "redirect:/lecturenotice/modify?boardNo=" + notice.getBoardNo();

    }

    @GetMapping("/remove")
    public String removeNotice(HttpServletRequest request, RedirectAttributes rttr) {

        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        lmsNoticeBoardService.removeNotice(boardNo);
        rttr.addFlashAttribute("message", "게시글이 삭제되었습니다.");

        return "redirect:/lecturenotice/list";
    }



}
