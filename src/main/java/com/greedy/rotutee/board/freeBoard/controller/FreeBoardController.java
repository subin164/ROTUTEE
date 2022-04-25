package com.greedy.rotutee.board.freeBoard.controller;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.Authentication.service.AuthenticationService;
import com.greedy.rotutee.board.freeBoard.dto.FreeBoardAnswerDTO;
import com.greedy.rotutee.board.freeBoard.dto.FreeBoardCategoryDTO;
import com.greedy.rotutee.board.freeBoard.dto.FreeBoardDTO;
import com.greedy.rotutee.board.freeBoard.dto.FreeBoardMemberDTO;
import com.greedy.rotutee.board.freeBoard.service.FreeBoardService;
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
@RequestMapping("freeBoard")
public class FreeBoardController {


    private FreeBoardService freeBoardService;
    private final MessageSource messageSource;
    private final AuthenticationService authenticationService;

    @Autowired
    public FreeBoardController(FreeBoardService freeBoardService, MessageSource messageSource, AuthenticationService authenticationService) {
        this.freeBoardService = freeBoardService;
        this.messageSource = messageSource;
        this.authenticationService= authenticationService;
    }

    @GetMapping(value = "/list")//, produces = "application/json; charset=UTF-8"
    public ModelAndView FreeBoardSelectList(ModelAndView mv, @PageableDefault Pageable pageable){

        Page<FreeBoardDTO> boardList = freeBoardService.findCategoryBoardList(pageable,6);

        System.out.println( "list"+ boardList);
        mv.addObject("boardList", boardList);
        mv.setViewName("board/freeBoard/list");
        return mv;

    }

    @GetMapping(value = "/list/{categoryNo}")//
    public ModelAndView CategoryFreeBoardList(HttpServletRequest request, ModelAndView mv, @PageableDefault Pageable pageable)  {

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));

        System.out.println(categoryNo);
        //Todo 뷰에서 값을 받아오면  밑에 값을 지운다. categoryNo = 4;
        Page<FreeBoardDTO> boardList = null;
        if(searchCondition != null && !"".equals(searchCondition)) {
            boardList = freeBoardService.findSearchBoardList(pageable, categoryNo,searchValue, searchCondition);
        }else{
            boardList = freeBoardService.findCategoryBoardList(pageable, categoryNo);
        }
        System.out.println("조회한 내용 목록  : " + boardList.getContent());
        System.out.println("총 페이지 수 : " + boardList.getTotalPages());
        System.out.println("총 메누 수 : " + boardList.getTotalElements());
        System.out.println("해당 페이지에 표시될 요소 수 : " + boardList.getSize());
        System.out.println("해당 페이지의 실제 요소 수 : " + boardList.getNumberOfElements());
        System.out.println("첫 페이지ㅣ 여부  : " + boardList.isFirst());
        System.out.println("마지막 페이지 여부 : " + boardList.isLast());
        System.out.println("정렬 방식 : " + boardList.getSort());
        System.out.println("여러 페이지 중 현재 인텍스 : "+ boardList.getNumber());


        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(boardList);

        mv.addObject("paging", paging);

        mv.addObject("searchCondition", searchCondition);
        mv.addObject("searchValue", searchValue);

        mv.addObject("boardList", boardList);

        mv.setViewName("board/freeBoard/list");

        return mv;
    }

    @GetMapping(value = "/detail/{boardNo}")
    public ModelAndView FreeBoardDetail(HttpServletRequest request, ModelAndView mv){
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        FreeBoardDTO board = freeBoardService.selectBoardDetail(boardNo);

        mv.addObject("board",board);
        mv.setViewName("freeBoard/detail");
        return mv;
    }

    @GetMapping(value = "/modify/{boardNo}")
    public ModelAndView FreeBoardmMdifyPage(HttpServletRequest request, ModelAndView mv){
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        FreeBoardDTO freeBoard = freeBoardService.selectBoardModify(boardNo);

        mv.addObject("freeBoard",freeBoard);
        mv.setViewName("freeBoard/modify");

        return mv;
    }

    @PostMapping(value = "/modify")
    public void FreeBoardModify(@ModelAttribute FreeBoardDTO freeBoard, RedirectAttributes rttr){

        freeBoardService.modifyBoard(freeBoard);
        rttr.addFlashAttribute("successMessage", "수정 완료");
    }

    @GetMapping("/regist")
    public void registBoard() {}

    @PostMapping(value = "/regist")
    public ModelAndView FreeBoardmRegistPage(@ModelAttribute FreeBoardDTO freeBoard, @AuthenticationPrincipal CustomUser customUser
            , RedirectAttributes rttr ,HttpServletRequest request, ModelAndView mv){

        int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));

        FreeBoardMemberDTO memberDTO = new FreeBoardMemberDTO();
        memberDTO.setMemberNo(customUser.getNo());
        memberDTO.setMemberName(customUser.getName());

        FreeBoardCategoryDTO categoryDTO = new FreeBoardCategoryDTO();
        categoryDTO.setBoardCategoryNo(categoryNo);

        freeBoard.setFreeBoardMember(memberDTO);
        freeBoard.setFreeBoardCategory(categoryDTO);
        freeBoardService.registNewFreeBoard(freeBoard);

        rttr.addFlashAttribute("successMessage", "생성 완료");
        mv.setViewName("redirect:/freeBoard/list?" +freeBoard.getFreeBoardCategory().getBoardCategoryNo());
        return mv;
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteBoard(HttpServletRequest request, RedirectAttributes rttr, ModelAndView mv){
        int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        freeBoardService.deleteFreeBoard(boardNo);

        rttr.addFlashAttribute("successMessage", "삭제 완료");

        mv.setViewName("redirect:/freeBoard/list?" + categoryNo);

        return mv;
    }

    @PostMapping(value = "/modifyAnswer")
    public ModelAndView updateAnswer( ModelAndView mv, HttpServletRequest request, RedirectAttributes rttr, @ModelAttribute FreeBoardAnswerDTO modifyAnswer, @AuthenticationPrincipal CustomUser customUser){
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        freeBoardDTO.setBoardNo(boardNo);

        FreeBoardMemberDTO freeBoardMemberDTO = new FreeBoardMemberDTO();
        freeBoardMemberDTO.setMemberNo(customUser.getNo());

        modifyAnswer.setFreeBoardMember(freeBoardMemberDTO);
        modifyAnswer.setFreeBoard(freeBoardDTO);

        freeBoardService.updateAnswer(modifyAnswer);

        rttr.addFlashAttribute("successMessage", "댓글 수정 완료");

        mv.setViewName("redirect:/freeBoard/detail?"+ modifyAnswer.getFreeBoard().getBoardNo());

        return mv;
    }

    @PostMapping(value = "/registAnswer")
    public ModelAndView modifyAnswer(@ModelAttribute  FreeBoardAnswerDTO registAnswer, @AuthenticationPrincipal CustomUser customUser,
                                     HttpServletRequest request, RedirectAttributes rttr,ModelAndView mv){

        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        FreeBoardMemberDTO freeBoardMemberDTO = new FreeBoardMemberDTO();
        freeBoardMemberDTO.setMemberNo(customUser.getNo());

        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        freeBoardDTO.setBoardNo(boardNo);

        registAnswer.setFreeBoardMember(freeBoardMemberDTO);
        registAnswer.setFreeBoard(freeBoardDTO);
        freeBoardService.insertAnswer(registAnswer);

        rttr.addFlashAttribute("successMessage", "댓글 생성 완료");
        mv.setViewName("redirect:/freeBoard/detail?"+ registAnswer.getFreeBoard().getBoardNo());

        return mv;
    }

    @PostMapping(value = "/deleteAnswer")
    public ModelAndView deleteAnswer(HttpServletRequest request, RedirectAttributes rttr, ModelAndView mv){
        int answerNo = Integer.parseInt(request.getParameter("answerNo"));
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        freeBoardService.deleteAnswer(answerNo);

        rttr.addFlashAttribute("successMessage", "댓글 삭제제 완료");

        mv.setViewName("redirect:/freeBoard/detail?"+ boardNo);

        return mv;
    }
}
