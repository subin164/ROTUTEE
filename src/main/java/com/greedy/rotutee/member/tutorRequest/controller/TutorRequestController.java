package com.greedy.rotutee.member.tutorRequest.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.service.MemberService;
import com.greedy.rotutee.member.tutorRequest.dto.CareerDTO;
import com.greedy.rotutee.member.tutorRequest.dto.QualificationDTO;
import com.greedy.rotutee.member.tutorRequest.dto.TutorApplyDTO;
import com.greedy.rotutee.member.tutorRequest.service.ProofFileHandler;
import com.greedy.rotutee.member.tutorRequest.service.TutorRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.controller
 * fileName : TutorRequestController
 * author : 7sang
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-22 7sang 최초 생성
 */

@Controller
@RequestMapping("/tutorApply")
public class TutorRequestController {

    private final MemberService memberService;
    private final ProofFileHandler fileHandler;
    private final TutorRequestService tutorRequestService;

    @Autowired
    public TutorRequestController(MemberService memberService, ProofFileHandler fileHandler, TutorRequestService tutorRequestService) {
        this.memberService = memberService;
        this.fileHandler = fileHandler;
        this.tutorRequestService = tutorRequestService;
    }

    @GetMapping("/list")
    public ModelAndView requestList(ModelAndView mv, @PageableDefault Pageable pageable) {

        Page<TutorApplyDTO> tutorApplyList = tutorRequestService.findAllRequestList(pageable);

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(tutorApplyList);

        mv.addObject("tutorApplyList", tutorApplyList);
        mv.addObject("paging", paging);

        return mv;
    }

    @GetMapping("/search")
    public ModelAndView requestSearchList(ModelAndView mv, @PageableDefault Pageable pageable,
                                          @RequestParam("searchCondition") String searchCondition, @RequestParam("searchValue") String searchValue) {

        Page<TutorApplyDTO> tutorApplyList = tutorRequestService.findSearchRequestList(pageable, searchCondition, searchValue);

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(tutorApplyList);

        mv.addObject("tutorApplyList", tutorApplyList);
        mv.addObject("paging", paging);
        mv.setViewName("/tutorApply/list");

        return mv;
    }

    @GetMapping("/mylist")
    public ModelAndView myRequestList(ModelAndView mv, @PageableDefault Pageable pageable, @AuthenticationPrincipal CustomUser loginMember) {

        String status = "대기";

        Page<TutorApplyDTO> tutorApplyList = tutorRequestService.findAllMyRequestList(pageable, loginMember.getNo());
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(tutorApplyList);

        mv.addObject("tutorApplyList", tutorApplyList);
        mv.addObject("paging", paging);
        mv.setViewName("/tutorApply/mylist");

        return mv;
    }

    @GetMapping("/detail")
    @ResponseBody
    public TutorApplyDTO requestDetail(ModelAndView mv, @RequestParam("memberNo") int memberNo) {

        TutorApplyDTO tutorApply = tutorRequestService.findTutorRequestDetail(memberNo);

        return tutorApply;

//        mv.addObject("tutorApply", tutorApply);
//        mv.setViewName("/tutorApply/detail");
//
//        return mv;
    }


    @GetMapping("/request")
    public ModelAndView tutorRequestPage(ModelAndView mv, @AuthenticationPrincipal CustomUser loginMember) {

        MemberDTO member = memberService.findMember(loginMember.getNo());

        mv.addObject("member", member);
        mv.setViewName("/tutorApply/request");

        return mv;
    }

    @PostMapping("/request")
    public String tutorRequest(@ModelAttribute TutorApplyDTO tutorApply, @AuthenticationPrincipal CustomUser loginMember,
                               RedirectAttributes rttr) {

        tutorApply.setMember(memberService.findMember(loginMember.getNo()));
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        tutorApply.setApplyDate(date);
        tutorApply.setApplyStatusDate(date);
        tutorApply.setApplyYn("대기");
        List<CareerDTO> careerList = tutorApply.getCareerList();
        List<QualificationDTO> qualificationList = tutorApply.getQualificationList();

        tutorRequestService.tutorRequest(loginMember, tutorApply, careerList, qualificationList);

        rttr.addFlashAttribute("message", "튜터신청에에 성공하셨습니다. 제출하실 증빙서류가 있다면 제출해주세요.");

        return "redirect:/dashboard/tuteedashboard";
    }

    @PostMapping("/filesupload")
    public String tutorApplyFilesUpload(@RequestParam("proofFiles")List<MultipartFile> proofFiles,
                                        @AuthenticationPrincipal CustomUser loginMember) throws Exception {

        tutorRequestService.proofFileUpload(fileHandler.UserFileUpload(proofFiles, loginMember.getNo()));

        return "redirect:/member/myfiles";
    }

    @GetMapping("/reject/{historyNo}")
    public String rejectTutorApply(@PathVariable int historyNo, RedirectAttributes rttr) {

        tutorRequestService.rejectTutorApply(historyNo);

        rttr.addFlashAttribute("message", "튜터신청을 거절하셨습니다.");

        return "redirect:/tutorApply/list";
    }

    @GetMapping("/approved/{historyNo}")
    public String approvedTutorApply(@PathVariable int historyNo,  RedirectAttributes rttr) {

        tutorRequestService.approvedTutorApply(historyNo);

        rttr.addFlashAttribute("message", "튜터신청을 승인하셨습니다.");

        return "redirect:/tutorApply/list";
    }

}
