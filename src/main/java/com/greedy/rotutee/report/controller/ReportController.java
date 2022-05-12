package com.greedy.rotutee.report.controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.report.dto.*;
import com.greedy.rotutee.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName : com.greedy.rotutee.report.controller
 * fileName : ReportController
 * author : SeoYoung
 * date : 2022-05-05
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-05 SeoYoung 최초 생성
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/list")
    public ModelAndView findReportList(HttpServletRequest request, ModelAndView mv, @PageableDefault Pageable pageable) {

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        System.out.println("searchCondition = " + searchCondition);
        System.out.println("searchValue = " + searchValue);

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Page<ReportDTO> reports = reportService.findReportList(pageable, searchMap);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(reports);

        mv.addObject("reports", reports);
        mv.addObject("paging", paging);
        mv.setViewName("report/reportlist");

        return mv;
    }

    @GetMapping(value = "/detail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String findReportDetail(HttpServletRequest request, @AuthenticationPrincipal CustomUser customUser) {
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        List<ReportDTO> reports = reportService.findReportDetail(boardNo);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls().disableHtmlEscaping()
                .create();

        return gson.toJson(reports);

    }
    @GetMapping(value = "/answerdetail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String findReportAnswerDetail(HttpServletRequest request) {
        String mapString = "answerNo";
        int answerNo = getRequestNo(request, mapString);

        List<ReportDTO> reports = reportService.findReportAnswerDetail(answerNo);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls().disableHtmlEscaping()
                .create();

        return gson.toJson(reports);

    }
    @GetMapping(value = "/modifyboard", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String modifyBoardReport(HttpServletRequest request, @AuthenticationPrincipal CustomUser customUser) {
        String boardNoMap = "boardNo";
        int boardNo = getRequestNo(request, boardNoMap);
        int memberNo = customUser.getNo();
        String clickBtn = request.getParameter("clickBtn");

        boolean result = false;
        if( clickBtn.equals("승인")) {
            result = reportService.approveBoardReport(boardNo, memberNo);
        } else if(clickBtn.equals("거절")) {
            result = reportService.rejectBoardReport(boardNo, memberNo);
        } else if(clickBtn.equals("철회")) {
            result = reportService.withdrawBoardReport(boardNo, memberNo);
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls().disableHtmlEscaping()
                .create();

        return gson.toJson(result);
    }

    @GetMapping(value = "/modifyanswer", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String modifyAnswerReport(HttpServletRequest request, @AuthenticationPrincipal CustomUser customUser) {

        String answerNoMap = "answerNo";
        int memberNo = customUser.getNo();
        int answerNo = getRequestNo(request, answerNoMap);
        String clickBtn = request.getParameter("clickBtn");

        boolean result = false;
        if( clickBtn.equals("승인")) {
            result =  reportService.approveAnswerReport(answerNo, memberNo);
        } else if(clickBtn.equals("거절")) {
            result = reportService.rejectAnswerReport(answerNo, memberNo);
        } else if(clickBtn.equals("철회")) {
            result = reportService.withdrawAnswerReport(answerNo, memberNo);
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls().disableHtmlEscaping()
                .create();

        return gson.toJson(result);
    }

    @GetMapping(value = "/board", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String registBoardReport(HttpServletRequest request, @AuthenticationPrincipal CustomUser customUser){

        //신고한사람
        int memberNo = customUser.getNo();
        String division = request.getParameter("division");
        //글 작성자
        int writerNo = Integer.parseInt(request.getParameter("writerNo"));
        String content = request.getParameter("content");
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        int reasonNo = Integer.parseInt(request.getParameter("reasonNo"));
        String title = request.getParameter("title");
        String processStatus = "대기";

        ReportDTO report = new ReportDTO();
        ReportMemberDTO member = new ReportMemberDTO();
        ReportMemberDTO accusedMember = new ReportMemberDTO();
        ReportBoardDTO board = new ReportBoardDTO();
        ReportCategoryDTO reportCategory = new ReportCategoryDTO();

        member.setNo(memberNo);
        accusedMember.setNo(writerNo);
        board.setBoardNo(boardNo);
        board.setBoardTitle(title);
        reportCategory.setReportCategoryNo(reasonNo);
        report.setMember(member);
        report.setDivision(division);
        report.setAccusedMember(accusedMember);
        report.setContent(content);
        report.setBoard(board);
        report.setProcessStatus(processStatus);
        report.setReportCategory(reportCategory);


        boolean result = reportService.registBoardReport(report);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls().disableHtmlEscaping()
                .create();

        return gson.toJson(result);

    }

    @GetMapping(value = "/answer", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String registAnswerReport(HttpServletRequest request, @AuthenticationPrincipal CustomUser customUser){

        int memberNo = customUser.getNo();
        String division = request.getParameter("division");
        int writerNo = Integer.parseInt(request.getParameter("writerNo"));
        String content = request.getParameter("content");
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        int reasonNo = Integer.parseInt(request.getParameter("reasonNo"));
        int answerNo = Integer.parseInt(request.getParameter("answerNo"));
        String answerContent = request.getParameter("answerContent");
        String processStatus = "대기";

        ReportDTO report = new ReportDTO();
        ReportMemberDTO member = new ReportMemberDTO();
        ReportMemberDTO accusedMember = new ReportMemberDTO();
        ReportBoardDTO board = new ReportBoardDTO();
        ReportCategoryDTO reportCategory = new ReportCategoryDTO();
        ReportBoardAnswerDTO answer = new ReportBoardAnswerDTO();

        member.setNo(memberNo);//신고하는사람
        accusedMember.setNo(writerNo);//댓글쓴사람
        board.setBoardNo(boardNo);
        reportCategory.setReportCategoryNo(reasonNo);
        answer.setAnswerNo(answerNo);
        answer.setAnswerContent(answerContent);
        report.setMember(member);
        report.setDivision(division);
        report.setAccusedMember(accusedMember);
        report.setContent(content);
        report.setBoard(board);
        report.setProcessStatus(processStatus);
        report.setReportCategory(reportCategory);
        report.setBoardAnswer(answer);


        boolean result = reportService.registAnswerReport(report);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls().disableHtmlEscaping()
                .create();

        return gson.toJson(result);

    }



    private int getRequestNo(HttpServletRequest request, String mapString) {

        int requestNo = Integer.parseInt(request.getParameter(mapString));

        return requestNo;
    }


}


