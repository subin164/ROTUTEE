package com.greedy.rotutee.board.freeboard.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.Authentication.service.AuthenticationService;
import com.greedy.rotutee.board.freeboard.dto.FreeBoardAnswerDTO;
import com.greedy.rotutee.board.freeboard.dto.FreeBoardCategoryDTO;
import com.greedy.rotutee.board.freeboard.dto.FreeBoardDTO;
import com.greedy.rotutee.board.freeboard.dto.FreeBoardMemberDTO;
import com.greedy.rotutee.board.freeboard.service.FreeBoardService;
import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.board.freeBoard.controller
 * fileName : FreeBoardController
 * author : soobeen
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-20          soobeen     최초 생성
 */

@Controller
@RequestMapping("/freeboard")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;
    private final MessageSource messageSource;
    private final AuthenticationService authenticationService;

    @Autowired
    public FreeBoardController(FreeBoardService freeBoardService, MessageSource messageSource,
                               AuthenticationService authenticationService) {
        this.freeBoardService = freeBoardService;
        this.messageSource = messageSource;
        this.authenticationService= authenticationService;
    }


    @GetMapping(value = "/list")
    public ModelAndView CategoryFreeBoardList(HttpServletRequest request, ModelAndView mv, @PageableDefault Pageable pageable)  {

        int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        Page<FreeBoardDTO> boardList = null;
        if(searchCondition != null && !"".equals(searchCondition)) {
            boardList = freeBoardService.findSearchBoardList(pageable, categoryNo,searchValue, searchCondition);
        }else {
            boardList = freeBoardService.findCategoryBoardList(pageable, categoryNo);
        }

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(boardList);

        mv.addObject("paging", paging);
        mv.addObject("searchCondition", searchCondition);
        mv.addObject("searchValue", searchValue);
        mv.addObject("categoryNo", categoryNo);
        mv.addObject("boardList", boardList);

        mv.setViewName("board/freeboard/boardList");

        return mv;
    }


    @GetMapping(value = "/detail")
    public ModelAndView FreeBoardDetail(HttpServletRequest request, ModelAndView mv){
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        FreeBoardDTO freeBoardDTO = freeBoardService.selectBoardDetail(boardNo);

        if(freeBoardDTO.getFreeBoardAnswerList() != null) {
            List<FreeBoardAnswerDTO> answer =freeBoardDTO.getFreeBoardAnswerList();

            mv.addObject("answer",answer);
        }

        mv.addObject("board",freeBoardDTO);
        mv.setViewName("board/freeboard/detail");

        return mv;
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteBoard(HttpServletRequest request, RedirectAttributes rttr, ModelAndView mv){
        int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        freeBoardService.deleteFreeBoard(boardNo);

        rttr.addFlashAttribute("successMessage", "삭제 완료");

        mv.setViewName("redirect:/freeboard/list?categoryNo=" + categoryNo);

        return mv;
    }

    //커뮤니티 게시판 수정 페이지 이동
    @GetMapping(value = "/modify")
    public ModelAndView FreeBoardmMdifyPage(HttpServletRequest request, ModelAndView mv){
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        FreeBoardDTO boardList = freeBoardService.selectBoardModify(boardNo);

        mv.addObject("boardList",boardList);
        mv.setViewName("board/freeboard/modify");

        return mv;
    }

    //커뮤니티 게시글 수정 요청
    @PostMapping(value = "/modify")
    public ModelAndView FreeBoardModify(@ModelAttribute FreeBoardDTO freeBoard, HttpServletRequest request,
                                        RedirectAttributes rttr, ModelAndView mv){

        int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));

        FreeBoardCategoryDTO categoryDTO = new FreeBoardCategoryDTO();
        categoryDTO.setBoardCategoryNo(categoryNo);

        freeBoard.setFreeBoardCategory(categoryDTO);
        freeBoardService.modifyBoard(freeBoard);

        rttr.addFlashAttribute("successMessage", "수정 완료");
        mv.setViewName("redirect:/freeboard/detail?boardNo=" + freeBoard.getBoardNo());
        return mv;
    }

    @GetMapping("/regist")
    public String registBoard() {

        return "board/freeboard/regist";
    }

    @PostMapping(value = "/regist")
    public ModelAndView FreeBoardmRegistPage(@ModelAttribute FreeBoardDTO freeBoard, @AuthenticationPrincipal CustomUser customUser
            , RedirectAttributes rttr ,HttpServletRequest request, ModelAndView mv){

        int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));

        FreeBoardMemberDTO memberDTO = new FreeBoardMemberDTO();

        memberDTO.setMemberNo(customUser.getNo());
        memberDTO.setMemberName(customUser.getName());
        freeBoard.setFreeBoardMember(memberDTO);

        FreeBoardCategoryDTO categoryDTO = new FreeBoardCategoryDTO();
        categoryDTO.setBoardCategoryNo(categoryNo);
        freeBoard.setFreeBoardCategory(categoryDTO);

        freeBoard.setBulletinBoardSecretYN('N');
        freeBoard.setBoardDeleteYN('N');
        freeBoardService.registNewFreeBoard(freeBoard);

        rttr.addFlashAttribute("successMessage", "생성 완료");
        mv.setViewName("redirect:/freeboard/list?categoryNo=" +categoryNo);
        return mv;
    }


    @PostMapping(value = "/registanswer" )
    public ModelAndView modifyAnswer(@ModelAttribute FreeBoardDTO freeBoard,@AuthenticationPrincipal CustomUser customUser,
                                     HttpServletRequest request, RedirectAttributes rttr,ModelAndView mv){

        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        String context = request.getParameter("answerContent");

        FreeBoardMemberDTO freeBoardMemberDTO = new FreeBoardMemberDTO();
        freeBoardMemberDTO.setMemberNo(customUser.getNo());

        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        freeBoardDTO.setBoardNo(boardNo);

        FreeBoardAnswerDTO registAnswer = new FreeBoardAnswerDTO();
        registAnswer.setAnswerContent(context);
        registAnswer.setFreeBoardMember(freeBoardMemberDTO);
        registAnswer.setFreeBoard(freeBoardDTO);

        freeBoardService.insertAnswer(registAnswer);

        rttr.addFlashAttribute("successMessage", "댓글 생성 완료");
        mv.setViewName("redirect:/freeboard/detail?boardNo="+ boardNo);

        return mv;
    }



    @PostMapping(value = "/deleteAnswer")
    public ModelAndView deleteAnswer(HttpServletRequest request, RedirectAttributes rttr, ModelAndView mv){
        int answerNo = Integer.parseInt(request.getParameter("answerNo"));
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        freeBoardService.deleteAnswer(answerNo);

        rttr.addFlashAttribute("successMessage", "댓글 삭제 완료");

        mv.setViewName("redirect:/freeboard/detail?boardNo="+ boardNo);

        return mv;
    }

    @PostMapping(value = "/modifyAnswer")
    public ModelAndView updateAnswer( ModelAndView mv, HttpServletRequest request, RedirectAttributes rttr
            , @AuthenticationPrincipal CustomUser customUser){

        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        int answerNo = Integer.parseInt(request.getParameter("answerNo"));
        String context = request.getParameter("answerContent");

        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        freeBoardDTO.setBoardNo(boardNo);

        FreeBoardMemberDTO freeBoardMemberDTO = new FreeBoardMemberDTO();
        freeBoardMemberDTO.setMemberNo(customUser.getNo());

        FreeBoardAnswerDTO modifyAnswer = new FreeBoardAnswerDTO();

        modifyAnswer.setAnswerNo(answerNo);
        modifyAnswer.setAnswerContent(context);
        modifyAnswer.setFreeBoardMember(freeBoardMemberDTO);
        modifyAnswer.setFreeBoard(freeBoardDTO);

        freeBoardService.updateAnswer(modifyAnswer);

        rttr.addFlashAttribute("successMessage", "댓글 수정 완료");

        mv.setViewName("redirect:/freeboard/detail?boardNo="+ modifyAnswer.getFreeBoard().getBoardNo());

        return mv;
    }

}
