package com.greedy.rotutee.board.controller;

import com.greedy.rotutee.board.dto.FreeBoardDTO;
import com.greedy.rotutee.board.service.FreeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.board.controller
 * fileName : FreeBoardController
 * author : soobeen
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-20          soobeen     최초 생성
 */

@Controller
@RequestMapping("freeBoard")
public class FreeBoardController {


    private FreeBoardService freeBoardService;

    @Autowired
    public FreeBoardController(FreeBoardService freeBoardService) {
        this.freeBoardService = freeBoardService;
    }

    @GetMapping(value = "/list")//, produces = "application/json; charset=UTF-8"
    public ModelAndView FreeBoardSelectList(HttpServletRequest request, ModelAndView mv){

        //int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
        List<FreeBoardDTO> boardList = freeBoardService.findByBoardCategoryNo(4);

        mv.addObject("boardList", boardList);
        mv.setViewName("/board/freeboard/list");
        return mv;

    }

    @GetMapping(value = "/regist")
    public void FreeBoardPage(){
    }

    @PostMapping(value="/regist")
    public ModelAndView FreeBoardRegist(ModelAndView mv, FreeBoardDTO newFreeBoard, RedirectAttributes rttr){

        freeBoardService.registNewFreeBoard(newFreeBoard);
        rttr.addFlashAttribute("registSuccessMessage", "게시판 등록이 성공하였습니다.");
        mv.setViewName("redirect");

        return mv;
    }

    @GetMapping(value = "/detail")
    public ModelAndView FreeBoardDetail(ModelAndView mv){

        mv.setViewName("board/freeBoard/detail");
        return mv;

    }


}
