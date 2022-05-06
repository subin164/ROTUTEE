package com.greedy.rotutee.board.serviceBoard.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.board.serviceBoard.dto.BoardAnswerDTO;
import com.greedy.rotutee.board.serviceBoard.dto.BoardDTO;
import com.greedy.rotutee.board.serviceBoard.service.ServiceBoardService;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.controller
 * fileName : ServiceBoardController
 * author : 7sang
 * date : 2022-05-04
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-04 7sang 최초 생성
 */

@Controller
@RequestMapping("/serviceBoard")
public class ServiceBoardController {

    private final ServiceBoardService serviceBoardService;

    @Autowired
    public ServiceBoardController(ServiceBoardService serviceBoardService) {
        this.serviceBoardService = serviceBoardService;
    }

    @GetMapping("/list")
    public ModelAndView ServiceBoardList(ModelAndView mv, @PageableDefault Pageable pageable) {

        Page<BoardDTO> boardList = serviceBoardService.findServiceBoardList(pageable);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(boardList);

//        Page<BoardDTO> boardList2 = serviceBoardService.findServiceBoardList2(pageable);
//        PagingButtonInfo paging2 = Pagenation.getPagingButtonInfo(boardList2);

        System.out.println("boardList = " + boardList);
        System.out.println("paging = " + paging);

        mv.addObject("boardList", boardList);
        mv.addObject("paging", paging);
//        mv.addObject("boardList2", boardList2);
//        mv.addObject("paging2", paging2);

        mv.setViewName("/board/serviceBoard/list");

        return mv;
    }

    @GetMapping("/regist")
    public String registPage() {

        return "/board/serviceBoard/regist"; 
    }

    @PostMapping("/regist")
    public String registServiceBoard(@ModelAttribute BoardDTO board, @AuthenticationPrincipal CustomUser loginMember) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        board.setCreationDate(timestamp);
        board.setDeleteYN('N');
        if(board.getBulletinBoardSecretYN() != 'Y') {
            board.setBulletinBoardSecretYN('N');
        }
        board.setBoardCategory(serviceBoardService.findCategoryByNo(7));
        board.setViewCount(0);
        board.setMember(serviceBoardService.findMemberByNo(loginMember.getNo()));
        board.setReportCount(0);

        serviceBoardService.registServiceBoard(board);

        return "/board/serviceBoard/regist";
    }

    @GetMapping("/detail/{boardNo}")
    public ModelAndView detailServiceBoard(ModelAndView mv, @PathVariable("boardNo") int boardNo) {

        BoardDTO detailBoard = serviceBoardService.findBoardByBoardNo(boardNo);
        List<BoardAnswerDTO> detailAnswerList = serviceBoardService.findBoardAnswerList(boardNo);

        mv.addObject("detailAnswerList", detailAnswerList);
        mv.addObject("detailBoard", detailBoard);
        mv.setViewName("/board/serviceBoard/fuckingDetail");

        return mv;
    }

    @GetMapping("/modify/{boardNo}")
    public ModelAndView modifyPage(ModelAndView mv, @PathVariable("boardNo") int boardNo) {

        BoardDTO modifyBoard = serviceBoardService.findBoardByBoardNo(boardNo);

        mv.addObject("modifyBoard", modifyBoard);
        mv.setViewName("/board/serviceBoard/modify");

        return mv;
    }

    @PostMapping("/modify")
    public String modifyServiceBoard(@ModelAttribute BoardDTO board) {

        BoardDTO modifyBoard = serviceBoardService.findBoardByBoardNo(board.getNo());
        modifyBoard.setTitle(board.getTitle());
        modifyBoard.setContent(board.getContent());
        modifyBoard.setModifiedDate(new Date(System.currentTimeMillis()));

        serviceBoardService.modifyServiceBoard(modifyBoard);

        return "redirect:/serviceBoard/detail/" + board.getNo();
    }

    @GetMapping("/remove/{boardNo}")
    public String removeServiceBoard(@PathVariable("boardNo") int boardNo) {

        serviceBoardService.removeServiceBoard(boardNo);

        return "redirect:/serviceBoard/list";
    }

    @GetMapping("/registAnswer")
    public ModelAndView registAnswer(@RequestParam("boardNo") int boardNo, @RequestParam("answerContent") String answerContent,
                             @AuthenticationPrincipal CustomUser loginMember, ModelAndView mv) {

        BoardAnswerDTO boardAnswer = new BoardAnswerDTO();
        boardAnswer.setAnswerYn('N');
        boardAnswer.setContent(answerContent);
        boardAnswer.setCreatedDate(new Date(System.currentTimeMillis()));
        boardAnswer.setMember(serviceBoardService.findMemberByNo(loginMember.getNo()));
        boardAnswer.setReportCount(0);
        boardAnswer.setBoard(serviceBoardService.findBoardByBoardNo(boardNo));

        serviceBoardService.registAnswer(boardAnswer);

        mv.setViewName("jsonView");

        return mv;
    }

    @PostMapping("/modifyAnswer")
    public ModelAndView modifyAnswerContent(@RequestParam("boardNo") int boardNo, @RequestParam("answerNo") int answerNo,
                             @RequestParam("modifyContent") String modifyContent, ModelAndView mv) {

        BoardAnswerDTO boardAnswer = serviceBoardService.findAnswerByAnswerNo(answerNo);

        boardAnswer.setContent(modifyContent);
        boardAnswer.setModifyDate(new Date(System.currentTimeMillis()));

        serviceBoardService.modifyAnswerContent(boardAnswer);

        mv.setViewName("jsonView");

        return mv;
    }

    @PostMapping("/removeAnswer")
    public ModelAndView removeAnswer(@RequestParam("answerNo") int answerNo, ModelAndView mv) {

        serviceBoardService.removeAnswer(answerNo);

        mv.setViewName("jsonView");

        return mv;
    }

    @GetMapping("/search")
    public ModelAndView searchBoardList(@RequestParam("searchValue") String searchValue, @RequestParam("searchCondition") String searchCondition,
                                  @PageableDefault Pageable pageable, ModelAndView mv) {

        System.out.println("searchValue = " + searchValue);
        System.out.println("searchCondition = " + searchCondition);

        Page<BoardDTO> boardList = serviceBoardService.findSearchServiceBoardList(searchValue, searchCondition, pageable);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(boardList);

        mv.addObject("paging", paging);
        mv.addObject("boardList", boardList);
        mv.setViewName("/board/serviceBoard/list");

        return mv;
    }
}
