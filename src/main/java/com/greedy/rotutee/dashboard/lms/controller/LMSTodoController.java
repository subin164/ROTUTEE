package com.greedy.rotutee.dashboard.lms.controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.dashboard.lms.dto.ToDoDTO;
import com.greedy.rotutee.dashboard.lms.service.LMSTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.controller
 * fileName : LMSTodoController
 * author : SeoYoung
 * date : 2022-05-11
 * description : LMS 대시보드 투두리스트 Controller
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-11 SeoYoung 최초 생성
 */
@Controller
@RequestMapping("/todo")
public class LMSTodoController {

    private LMSTodoService lmsTodoService;

    @Autowired
    public LMSTodoController(LMSTodoService lmsTodoService) {
        this.lmsTodoService = lmsTodoService;
    }

    /**
     * methodName : registTodo
     * author : SeoYoung Kim
     * description : 대시보드 투두 등록
     *
     * @param todo
     * @param customUser
     * @return string
     */
    @PostMapping("/regist")
    public String registTodo(@ModelAttribute ToDoDTO todo, @AuthenticationPrincipal CustomUser customUser){

        int memberNo = customUser.getNo();
        int lectureNo = todo.getLectureNo();
        todo.setMemberNo(memberNo);
        todo.setRegistedDate(new Date(System.currentTimeMillis()));
        todo.setAchievementStatus("N");

        lmsTodoService.registTodo(todo);

        return "redirect:/lms/dashboardlist?no=" + lectureNo;

    }

    /**
     * methodName : modifyTodo
     * author : SeoYoung Kim
     * description : 대시보드 Todo 수정
     *
     * @param request
     * @return string
     */
    @PostMapping("/modify")
    @ResponseBody
    public String modifyTodo(HttpServletRequest request) {

        String content = request.getParameter("content");
        int todoNo = Integer.parseInt(request.getParameter("todoNo"));

        boolean result = lmsTodoService.modifyTodo(content, todoNo);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls().disableHtmlEscaping()
                .create();

        return gson.toJson(result);

    }

    /**
     * methodName : removeTodo
     * author : SeoYoung Kim
     * description : Todo 삭제
     *
     * @param request
     * @return string
     */
    @GetMapping("/remove")
    @ResponseBody
    public String removeTodo(HttpServletRequest request){

        int todoNo = Integer.parseInt(request.getParameter("todoNo"));

        boolean result = lmsTodoService.removeTodo(todoNo);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls().disableHtmlEscaping()
                .create();

        return gson.toJson(result);
    }

    /**
     * methodName : modifyStatus
     * author : SeoYoung Kim
     * description : Todo 상태 변경(완료, 미완료)
     *
     * @param request
     * @return string
     */
    @GetMapping("/status")
    @ResponseBody
    public String modifyStatus(HttpServletRequest request) {

        String status = request.getParameter("status");
        int todoNo = Integer.parseInt(request.getParameter("todoNo"));

        lmsTodoService.modifyStatus(status, todoNo);

        boolean result = true;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls().disableHtmlEscaping()
                .create();

        return gson.toJson(result);
    }


}
