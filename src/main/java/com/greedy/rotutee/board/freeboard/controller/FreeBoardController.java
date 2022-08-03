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
 * description : 커뮤니티 게시판 기능들의 요청을 주고 요청값받아 넘겨준다 controller
 * ===========================================================
 * DATE || AUTHOR || NOTE
 * -----------------------------------------------------------
 * 2022-04-20 || soobeen  || 최초 생성
 */

@Controller
@RequestMapping("/freeboard")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @Autowired
    public FreeBoardController(FreeBoardService freeBoardService, MessageSource messageSource,
                               AuthenticationService authenticationService) {
        this.freeBoardService = freeBoardService;
    }

    /**
     * methodName : CategoryFreeBoardList
     * author : Soo Been Park
     * description : 뷰에서 커뮤니티 게시판 에서 보여주는 정보를 넘겨주는 메소드
     * @param request 현재 카테고리 정보 , 검색카테고리 정보, 검색 정보 값을 받습니다..
     * @param mv : 패이징정보 ,검색 카테고리 , 검색 정보 ,카테고리 번호 ,게시판 정보 커뮤니티 게시판 에서 보여지는 정보를 담는 객체
     * @param pageable  페이지 정보를 담고있음
     * @return ModelAndView
     */
    @GetMapping(value = "/list")
    public ModelAndView CategoryFreeBoardList(HttpServletRequest request, ModelAndView mv, @PageableDefault Pageable pageable)  {
        int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        Page<FreeBoardDTO> boardList = null;

        /* 검색 유 무의 조회 페이징 처리 요청 */
        if(searchCondition != null && !"".equals(searchCondition)) {
            boardList = freeBoardService.getSearchBaordInfo(pageable, categoryNo,searchValue, searchCondition);
        }else {
            boardList = freeBoardService.getBoardInfo(pageable, categoryNo);
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

    /**
     * methodName : FreeBoardDetail
     * author : Soo Been Park
     * description : 뷰에서 커뮤니티 상세 게시판 에서 보여주는 정보를 넘겨주는 메소드
     *
     * @param request 현재 카테고리 정보 , 검색카테고리 정보, 검색 정보 값을 받습니다.
     * @param mv : 커뮤니티 상세 게시판 에서 보여지는 정보를 담는 객체 (정보 : 게시판 정보, 댓글 정보)
     * @return ModelAndView
     */
    @GetMapping(value = "/detail")
    public ModelAndView FreeBoardDetail(HttpServletRequest request, ModelAndView mv){
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        FreeBoardDTO freeBoardDTO = freeBoardService.selectBoardDetail(boardNo);
        if(freeBoardDTO.getFreeBoardAnswerList() != null) {
            List<FreeBoardAnswerDTO> answer = freeBoardDTO.getFreeBoardAnswerList();
            mv.addObject("answer",answer);
        }

        mv.addObject("board",freeBoardDTO);
        mv.setViewName("board/freeboard/detail");
        return mv;
    }


    /**
     * methodName : deleteBoard
     * author : Soo Been Park
     * description : 뷰애서 해당 게시글을 삭제 요청을 보내는 메소드
     *
     * @param request: 현재 카테고리 정보 , 게시판 번호 정보을 받습니다.
     * @param rttr : view에 메세지 정보를 보내준다. (정보 : 메세지 정보)
     * @param mv : 커뮤니티 상세 게시판 에서 보여지는 정보를 담는 객체 (정보 : 현재 수정 게시판 정보, 댓글 정보)
     * @return ModelAndView
     */
    @PostMapping(value = "/delete")
    public ModelAndView deleteBoard(HttpServletRequest request, RedirectAttributes rttr, ModelAndView mv){

        int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        freeBoardService.deleteFreeBoard(boardNo);

        rttr.addFlashAttribute("successMessage", "삭제 완료");

        mv.setViewName("redirect:/freeboard/list?categoryNo=" + categoryNo);

        return mv;
    }

    /**
     * methodName : FreeBoardModifyPage
     * author : Soo Been Park
     * description : 뷰에서 커뮤니티 수정 페이지 에서 보여주는 정보를 넘겨주는 메소드
     *
     * @param request 현재 게시글 번호 정보 값을 넘겨줍니다.
     * @param mv : 커뮤니티 상세 게시판 에서 보여지는 정보를 담는 객체 (정보 : 수정 게시글 정보)
     * @return ModelAndView
     */
    @GetMapping(value = "/modify")
    public ModelAndView FreeBoardModifyPage(HttpServletRequest request, ModelAndView mv){
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        FreeBoardDTO boardList = freeBoardService.selectBoardModify(boardNo);

        mv.addObject("boardList",boardList);
        mv.setViewName("board/freeboard/modify");

        return mv;
    }

    /**
     * methodName : FreeBoardModify
     * author : Soo Been Park
     * description : 뷰에서 커뮤니티 수정 게시판 에서 정보 수정 요청하는  메소드
     *
     * @param freeBoard 수정한 게시글의 정보 값을 넘겨줍니다.
     * @param request 현재 카테고리 값을 넘겨줍니다.
     * @param rttr 현재 메세지 정보 값을 넘겨줍니다.
     * @param mv : 뷰페이지 정보를 담아 값을 넘겨줍니다.
     * @return ModelAndView
     */
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


    /**
     * methodName : registBoard
     * author : Soo Been Park
     * description : 게시글 생성 페이지 주소 페이지로 넘겨주는 메소드
     * @return ModelAndView
     */
    @GetMapping("/regist")
    public String registBoard() {

        return "board/freeboard/regist";
    }


    /**
     * methodName : FreeBoardRegistPage
     * author : Soo Been Park
     * description : 뷰에서 커뮤니티 상세 게시판에서 정보를 받아 요청한 정보를 넘겨주는 메소드
     *
     * @param freeBoard 현재 생성 게시글의 정보 값을 담아 값을 넘겨줍니다.
     * @param customUser 현재 springSecurity 사용자의 정보을 넘겨줍니다.
     * @param rttr 요청완료시 alert창 메새지 정보를 넘겨줍니다.
     * @param request 카테고리 정보를 넘겨줍니다.
     * @param mv : 커뮤니티 상세 게시판 에서 작성한 정보를 담는 객체 (정보 : 게시판 정보, 댓글 정보)
     * @return ModelAndView
     */
    @PostMapping(value = "/regist")
    public ModelAndView FreeBoardRegistPage(@ModelAttribute FreeBoardDTO freeBoard, @AuthenticationPrincipal CustomUser customUser
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

        rttr.addFlashAttribute("message", "게시물 등록에 성공하였습니다.");
        mv.setViewName("redirect:/freeboard/list?categoryNo=" +categoryNo);
        return mv;
    }


    /**
     * methodName : modifyAnswer
     * author : Soo Been Park
     * description : 뷰에서 커뮤니티 상세 게시판 에서 댓글 작성 정보를넘겨주는 메소드
     *
     * @param freeBoard 현재 작성한 댓글 정보값을 넘겨줍니다.
     * @param customUser : 현재 springSecurity 사용자의 정보을 넘겨줍니다.
     * @param request : 커뮤니티 상세 페이지에서 댓글 작성 내용과 해당 게시글의 번호를 담아 넘겨줍니다.
     * @param rttr : 요청 완료시 alert창 메새지 정보를 넘겨줍니다.
     * @param mv : 댓글 작성완료시 해당 주소 값을 넘겨줍니다.
     * @return ModelAndView
     */
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

        mv.setViewName("redirect:/freeboard/detail?boardNo="+ boardNo);

        return mv;
    }


    /**
     * methodName : deleteAnswer
     * author : Soo Been Park
     * description : 커뮤니티 상세 페이지 뷰에서 댓글의 삭제 요청를 넘겨주는 메소드
     *
     * @param request 댓글 번호, 게시글 번호 값을 넘겨줍니다.
     * @param rttr : 요청 완료시 alert 창 메새지 정보를 넘겨줍니다.
     * @param mv : 댓글 작성완료시 해당 주소 값을 넘겨줍니다.
     * @return ModelAndView
     */
    @PostMapping(value = "/deleteAnswer")
    public ModelAndView deleteAnswer(HttpServletRequest request, RedirectAttributes rttr, ModelAndView mv){
        int answerNo = Integer.parseInt(request.getParameter("answerNo"));
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        freeBoardService.deleteAnswer(answerNo);

        rttr.addFlashAttribute("successMessage", "댓글 삭제 완료");

        mv.setViewName("redirect:/freeboard/detail?boardNo="+ boardNo);

        return mv;
    }

    /**
     * methodName : updateAnswer
     * author : Soo Been Park
     * description : 커뮤니티 상세 페이지 뷰에서 댓글의 수정 요청를 넘겨주는 메소드
     *
     * @param request 댓글 번호, 게시글 번호 값을 넘겨줍니다.
     * @param rttr : 요청 완료시 alert 창 메새지 정보를 넘겨줍니다.
     * @param customUser : 현재 springSecurity 사용자의 정보을 넘겨줍니다.
     * @param mv : 댓글 작성완료시 해당 주소 값을 넘겨줍니다.
     * @return ModelAndView
     */
    @PostMapping(value = "/modifyAnswer")
    public ModelAndView updateAnswer( HttpServletRequest request, RedirectAttributes rttr
                                     , @AuthenticationPrincipal CustomUser customUser, ModelAndView mv){

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
