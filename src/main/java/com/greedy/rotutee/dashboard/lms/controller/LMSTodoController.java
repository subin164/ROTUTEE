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
 * description :
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


}
