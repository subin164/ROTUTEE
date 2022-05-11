package com.greedy.rotutee.dashboard.lms.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.dashboard.lms.dto.ToDoDTO;
import com.greedy.rotutee.dashboard.lms.service.LMSTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
