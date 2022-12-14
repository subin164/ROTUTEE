package com.greedy.rotutee.study.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.Authentication.service.AuthenticationService;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.study.dto.StudyByTagDTO;
import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.dto.StudyReplyDTO;
import com.greedy.rotutee.study.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * The type StudyController.
 */
@Controller
@RequestMapping("/study")
public class StudyController {

    /**
     * The Study service.
     */
    private final StudyService studyService;
    /**
     * The Authentication service.
     */
    private final AuthenticationService authenticationService;


    /**
     * Instantiates a new Study controller.
     *
     * @param studyService          the study service
     * @param authenticationService the authentication service
     */
    @Autowired
    public StudyController(StudyService studyService, AuthenticationService authenticationService) {
        this.studyService = studyService;
        this.authenticationService = authenticationService;
    }

    /**
     * methodName : StudyList
     * author : SeoYoung Kim
     * description :
     *
     * @param mv
     * @param request
     * @param pageable
     * @return model and view
     */
    /*
     * writer : ?????????
     * writeDate : 22/04/18 ~ 22/04/26
     * title : ????????? ??????
     * content : ????????? ????????? ?????? ???????????? ???????????? ????????? ???????????? ???????????? ??????
     * */
    @GetMapping("list")
    public ModelAndView StudyList(ModelAndView mv, HttpServletRequest request, Pageable pageable) {

        String searchCondition = request.getParameter("searchCondition");
        String searchTag = request.getParameter("searchTag");

        Page<StudyDTO> studyList = studyService.findByStudyList(searchCondition, searchTag, pageable);

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(studyList);

        List<StudyByTagDTO> studyTagList = studyService.finByStudyTagList();


        mv.addObject("studyList", studyList);
        mv.addObject("paging", paging);
        mv.addObject("studyTagList", studyTagList);

        mv.setViewName("/study/list");

        return mv;
    }

    /**
     * methodName : studyRegist
     * author : SeoYoung Kim
     * description :
     *
     * @param mv
     * @param study  dto
     * @param input  tag
     * @param custom user
     * @return model and view
     */
    /*
     * writer : ?????????
     * writeDate : 22/04/18 ~ 22/04/26
     * title : ????????? ??????
     * content : ????????? ????????? ?????? ???????????? ???????????? ????????? ???????????? ???????????? ??????
     * */
    @PostMapping("/regist")
    public ModelAndView studyRegist(ModelAndView mv, StudyDTO studyDTO, String inputTag, @AuthenticationPrincipal CustomUser customUser) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = (User) principal;

//        CustomUser customUser = (CustomUser) authenticationService.loadUserByUsername(user.getUsername());

        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setNo(customUser.getNo());

        studyDTO.setStartDate(new Date(System.currentTimeMillis()));
        studyDTO.setWriter(memberDTO);
        studyDTO.setCount(0);
        studyDTO.setRecruitStatus("Y");
        studyDTO.setPostStatus("N");

        System.out.println("inputTag = " + inputTag.trim());

        String[] tagArray = inputTag.split("#");
        for (int i = 0; i < inputTag.length(); i++) {

        }


        List<String> tagList = new ArrayList<>();

        for (int i = 1; i < tagArray.length; i++) {
            tagList.add(tagArray[i].trim());
        }

        System.out.println("tagList = " + tagList);
        System.out.println("studyDTO ??? ?????? : " + studyDTO);

        studyService.studyRegist(studyDTO, tagList);

        mv.setViewName("/study/list");

        return mv;
    }

    /**
     * methodName : studyDetail
     * author : SeoYoung Kim
     * description :
     *
     * @param mv
     * @param request
     * @return model and view
     */
    /*
     * writer : ?????????
     * writeDate : 22/04/28 ~ 22/04/28
     * title : ????????? ??????????????? ??????
     * content : ????????? ???????????? ?????? ??????????????? ??????
     * */
    @GetMapping("/detail")
    public ModelAndView studyDetail(ModelAndView mv, HttpServletRequest request) {

        int studyNo = Integer.parseInt(request.getParameter("no"));

        StudyDTO studyDetail = studyService.findStudyDetail(studyNo);
        List<StudyByTagDTO> studyByTagList = studyService.modifyStudyDetailTagList(studyNo);
        List<StudyReplyDTO> studyReplyList = studyService.findStudyReply(studyNo);

        mv.addObject("studyDetail", studyDetail);
        mv.addObject("studyByTagList", studyByTagList);
        mv.addObject("studyReplyList", studyReplyList);
        mv.setViewName("/study/detail");

        return mv;
    }

    /**
     * methodName : studyMddify
     * author : SeoYoung Kim
     * description :
     *
     * @param modify  tag list
     * @param request
     * @param study   dto
     * @return string
     */
    /*
     * writer : ?????????
     * writeDate : 22/04/29 ~ 22/05/01
     * title : ????????? ??????????????? ??????
     * content : ????????? ????????? ?????? ??????
     * */
    @PostMapping("/modify")
    public String studyMddify(@RequestParam(value = "modifyTagList") List<String> modifyTagList,
                              HttpServletRequest request, StudyDTO studyDTO) {

        studyDTO.setTitle(request.getParameter("title"));
        studyDTO.setContent(request.getParameter("content"));
        studyDTO.setEndDate(Date.valueOf(request.getParameter("endDate")));
        studyDTO.setLimited(Integer.parseInt(request.getParameter("limited")));
        studyDTO.setLinked(request.getParameter("linked"));
        studyDTO.setModifyDate(new Date(System.currentTimeMillis()));
        System.out.println("studyDTO = " + studyDTO);

        studyService.studyDetailModify(studyDTO, modifyTagList);



        return "redirect:/study/list";

    }

    /**
     * methodName : studyRemove
     * author : SeoYoung Kim
     * description :
     *
     * @param request
     * @return string
     */
    /*
     * writer : ?????????
     * writeDate : 22/04/28 ~ 22/04/28
     * title : ????????? ??????????????? ??????
     * content : ??????????????? ?????? ??????
     * */
    @GetMapping("/remove")
    public String studyRemove(HttpServletRequest request) {

        int no = Integer.parseInt(request.getParameter("no"));

        studyService.removeStudy(no);

        return "redirect:/study/list";
    }


    /**
     * methodName : studyReplyRegist
     * author : SeoYoung Kim
     * description :
     *
     * @param reply  dto
     * @param custom user
     * @return study reply dto
     */
    @PostMapping("/replyRegist")
    @ResponseBody
    public StudyReplyDTO studyReplyRegist(StudyReplyDTO replyDTO, @AuthenticationPrincipal CustomUser customUser) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = (User) principal;

//        CustomUser customUser = (CustomUser) authenticationService.loadUserByUsername(user.getUsername());

        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setNo(customUser.getNo());

        replyDTO.setReplyStatus("N");
        replyDTO.setReplyWriteDate(new Date(System.currentTimeMillis()));
        replyDTO.setWriter(memberDTO);

        StudyReplyDTO studyReplyDTO = studyService.studyReplyRegist(replyDTO);

        return studyReplyDTO;

    }

    /**
     * methodName : studyReplyRemove
     * author : SeoYoung Kim
     * description :
     *
     * @param reply dto
     */
    @PostMapping("/replyRemove")
    @ResponseBody
    public void studyReplyRemove(StudyReplyDTO replyDTO) {

        studyService.studyReplyRemove(replyDTO);
    }

    /**
     * methodName : studyReplyModify
     * author : SeoYoung Kim
     * description :
     *
     * @param reply   dto
     * @param request
     * @return string
     */
    @PostMapping("/replyModify")
    public String studyReplyModify(StudyReplyDTO replyDTO, HttpServletRequest request){
        replyDTO.setReplyNo(Integer.parseInt(request.getParameter("replyNo")));
        replyDTO.setReplyContent(request.getParameter("replyContent"));
        replyDTO.setReplyModifyDate(new Date(System.currentTimeMillis()));

        System.out.println("replyDTO===== " + replyDTO);

        studyService.studyReplyModify(replyDTO);

        return "redirect:/study/detail?no=" + replyDTO.getStudyNo();

    }


    /**
     * methodName : studyListBack
     * author : SeoYoung Kim
     * description :
     *
     * @return string
     */
    @GetMapping("/listBack")
    public String studyListBack(){

        return "redirect:/study/list";
    }

    /**
     * methodName : recruitStatus
     * author : SeoYoung Kim
     * description :
     *
     * @return string
     */
    @GetMapping("/recruitStatus")
    public  String recruitStatus(){
        System.out.println("?????????????");

        return "redirect:/study/list";
    }
}