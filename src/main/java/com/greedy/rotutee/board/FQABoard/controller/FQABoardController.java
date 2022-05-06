package com.greedy.rotutee.board.FQABoard.controller;

import com.greedy.rotutee.board.FQABoard.dto.BoardDTO;
import com.greedy.rotutee.board.FQABoard.service.FQABoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.board.FQABoard.controller
 * fileName : FQABoardController
 * author : 7sang
 * date : 2022-05-06
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-06 7sang 최초 생성
 */

@Controller
@RequestMapping("/FQABoard")
public class FQABoardController {

    private final FQABoardService fqaBoardService;

    @Autowired
    public FQABoardController(FQABoardService fqaBoardService) {
        this.fqaBoardService = fqaBoardService;
    }

    @GetMapping("/list/{categoryNo}")
    public ModelAndView FQABoardList(ModelAndView mv, @PathVariable("categoryNo") int categoryNo) {

        List<BoardDTO> boardList = fqaBoardService.findAllFQABoardList(categoryNo);

        mv.addObject("boardList", boardList);
        mv.setViewName("/board/FQABoard/list");

        return mv;
    }
}
