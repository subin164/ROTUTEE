package com.greedy.rotutee.board.serviceBoard.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
