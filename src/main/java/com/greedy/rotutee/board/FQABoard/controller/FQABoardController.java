package com.greedy.rotutee.board.FQABoard.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.board.FQABoard.dto.BoardDTO;
import com.greedy.rotutee.board.FQABoard.service.FQABoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
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

//        for(BoardDTO board : boardList) {
//            board.setContent(board.getContent().replaceAll("\r\n", "<br>"));
//        }

        mv.addObject("boardList", boardList);
        mv.addObject("categoryNo", categoryNo);
        mv.setViewName("/board/FQABoard/list");

        return mv;
    }

    @GetMapping("/regist")
    public String registFQABoard(@ModelAttribute BoardDTO board, @RequestParam("categoryNo") int categoryNo,
                                 @AuthenticationPrincipal CustomUser admin) {

        board.setCreationDate(new Date(System.currentTimeMillis()));
        board.setDeleteYN('N');
        board.setBulletinBoardSecretYN('N');
        board.setReportCount(0);
        board.setViewCount(0);

        System.out.println("board = " + board);
        System.out.println("fqaBoardService = " + fqaBoardService);

        fqaBoardService.registFQABoard(board, categoryNo, admin.getNo());

        return "redirect:/FQABoard/list/" + categoryNo;
    }

    @GetMapping("/modify")
    public String modifyFQABoard(@ModelAttribute BoardDTO board, @RequestParam("categoryNo") int categoryNo) {

        fqaBoardService.modifyFQABoard(board);

        return "redirect:/FQABoard/list/" + categoryNo;
    }

    @GetMapping("/remove")
    public String removeFQABoard(@RequestParam("boardNo") int boardNo, @RequestParam("categoryNo") int categoryNo) {

        fqaBoardService.removeFQABoard(boardNo);

        return "redirect:/FQABoard/list/" + categoryNo;
    }
}
