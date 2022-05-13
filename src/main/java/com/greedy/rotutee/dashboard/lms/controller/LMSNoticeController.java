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
 * description : LMS 공지사항 게시판 Controller
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

    /**
     * methodName : findNoticeList
     * author : SeoYoung Kim
     * description : LMS 공지사항 리스트 조회
     *
     * @param request : lectureNo 받아옴
     * @param mv : 버튼 paging 정보와, 공지사항 정보 담아줄 객체
     * @param pageable paging 정보
     * @return modelandview
     */
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

    /**
     * methodName : findNoticeDetail
     * author : SeoYoung Kim
     * description : LMS 공지사항 상세조회
     *
     * @param mv : 상세정보를 담아줄 객체
     * @param request 상세정보를 조회하기 위해 noticeNo을 받아옴
     * @return modelandview : 공지사항 상세정보
     */
    @GetMapping("/detail")
    public ModelAndView findNoticeDetail(ModelAndView mv, HttpServletRequest request) {

        int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));

        LMSNoticeBoardDTO noticeDetail = lmsNoticeBoardService.findNoticeDetail(noticeNo);

        mv.addObject("noticeDetail", noticeDetail);
        mv.setViewName("dashboard/lms/notice/detail");

        return mv;

    }

    /**
     * methodName : registNotice
     * author : SeoYoung Kim
     * description : 공지사항 등록 페이지로 이등
     *
     * @param mv lectureNo을 담아줄 객체
     * @param request lectureNo를 받아온다
     * @return modelandview
     */
    @GetMapping("/regist")
    public ModelAndView registNotice(ModelAndView mv, HttpServletRequest request) {

        HttpSession session = request.getSession();
        int lectureNo = (Integer)(session.getAttribute("lectureNo"));

        mv.addObject("lectureNo", lectureNo);
        mv.setViewName("dashboard/lms/notice/regist");

        return mv;
    }

    /**
     * methodName : registNotice
     * author : SeoYoung Kim
     * description : 공지사항 등록
     *
     * @param notice 등록할 공지사항 정보
     * @param rttr redirect 메세지
     * @param request lecutureNo 정보를 담아온다
     * @param customUser 세션정보
     * @return string
     */
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

    /**
     * methodName : modifyNotice
     * author : SeoYoung Kim
     * description : 공지사항 수정 페이지 이동
     *
     * @param mv : 수정 전 내용 담을 객체
     * @param request boardNo을 받아옴
     * @return modelandview
     */
    @GetMapping("/modify")
    public ModelAndView modifyNotice(ModelAndView mv, HttpServletRequest request) {

        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        LMSNoticeBoardDTO noticeDetail = lmsNoticeBoardService.findNoticeDetail(boardNo);

        mv.addObject("noticeDetail", noticeDetail);
        mv.setViewName("dashboard/lms/notice/modify");

        return mv;

    }

    /**
     * methodName : modifyNotice
     * author : SeoYoung Kim
     * description : 공지사항 수정
     *
     * @param notice 수정할 공지사항 내용
     * @param rttr 리다이렉트할 메세지
     * @param customUser 세션에 로그인 된 정보
     * @return string
     */
    @PostMapping("/modify")
    public String modifyNotice(@ModelAttribute LMSNoticeBoardDTO notice, RedirectAttributes rttr, @AuthenticationPrincipal CustomUser customUser) {

        int memberNo = customUser.getNo();
        notice.setModifiedDate(new Date(System.currentTimeMillis()));

        lmsNoticeBoardService.modifyNotice(notice);

        rttr.addFlashAttribute("message", "게시글이 수정되었습니다.");
        return "redirect:/lecturenotice/modify?boardNo=" + notice.getBoardNo();

    }

    /**
     * methodName : removeNotice
     * author : SeoYoung Kim
     * description : 공지사항 삭제
     *
     * @param request boardNo을 받아옴
     * @param rttr 삭제메세지 리다이렉트
     * @return string
     */
    @GetMapping("/remove")
    public String removeNotice(HttpServletRequest request, RedirectAttributes rttr) {

        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        lmsNoticeBoardService.removeNotice(boardNo);
        rttr.addFlashAttribute("message", "게시글이 삭제되었습니다.");

        return "redirect:/lecturenotice/list";
    }



}
