package com.greedy.rotutee.member.tutorRequest.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.member.service.MemberService;
import com.greedy.rotutee.member.tutorRequest.dto.CareerDTO;
import com.greedy.rotutee.member.tutorRequest.dto.QualificationDTO;
import com.greedy.rotutee.member.tutorRequest.dto.TutorApplyDTO;
import com.greedy.rotutee.member.tutorRequest.entity.TutorApply;
import com.greedy.rotutee.member.tutorRequest.service.ProofFileHandler;
import com.greedy.rotutee.member.tutorRequest.service.TutorRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView requestList(ModelAndView mv) {

        List<TutorApplyDTO> tutorApplyList = tutorRequestService.findTutorRequestList();

        mv.addObject("tutorApplyList", tutorApplyList);

        return mv;
    }

    @GetMapping("/detail")
    public ModelAndView requestDetail(ModelAndView mv) {

        TutorApplyDTO tutorApply = tutorRequestService.findTutorRequestDetail(21);

        mv.addObject("tutorApply", tutorApply);

        return mv;
    }


    @GetMapping("/request")
    public ModelAndView tutorRequestPage(ModelAndView mv, @AuthenticationPrincipal CustomUser loginMember) {

        MemberDTO member = memberService.findMember(loginMember.getNo());

        mv.addObject("member", member);
        mv.setViewName("/request/request");

        return mv;
    }

    @PostMapping("/request")
    public String tutorRequest(@ModelAttribute TutorApplyDTO tutorApply, @AuthenticationPrincipal CustomUser loginMember,
                               @RequestParam("proofFiles")List<MultipartFile> proofFiles) throws Exception {

        tutorApply.setMember(memberService.findMember(loginMember.getNo()));
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        tutorApply.setApplyDate(date);
        tutorApply.setApplyStatusDate(date);
        tutorApply.setApplyYn("대기");
        List<CareerDTO> careerList = tutorApply.getCareerList();
        List<QualificationDTO> qualificationList = tutorApply.getQualificationList();

        tutorRequestService.tutorRequest(loginMember, tutorApply, careerList, qualificationList);

        if(!proofFiles.isEmpty()) {
            System.out.println("proofFiles = " + proofFiles);
            System.out.println("proofFiles = " + proofFiles.get(0));
            System.out.println("proofFiles = " + proofFiles.size());
            tutorRequestService.proofFileUpload(fileHandler.UserFileUpload(proofFiles, loginMember.getNo()));
        }

        System.out.println("proofFiles = " + proofFiles);
        System.out.println("careerList = " + careerList);
        System.out.println("qualificationList = " + qualificationList);

        return "redirect:/";
    }

}
