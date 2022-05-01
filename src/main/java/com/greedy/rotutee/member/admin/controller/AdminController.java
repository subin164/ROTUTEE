package com.greedy.rotutee.member.admin.controller;

import com.greedy.rotutee.common.paging.Pagenation;
import com.greedy.rotutee.common.paging.PagingButtonInfo;
import com.greedy.rotutee.member.admin.dto.MemberDTO;
import com.greedy.rotutee.member.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * packageName : com.greedy.rotutee.member.admin.controller
 * fileName : AdminController
 * author : 7sang
 * date : 2022-05-01
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-01 7sang 최초 생성
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/list")
    public ModelAndView findAdminList(ModelAndView mv, @PageableDefault Pageable pageable) {

        Page<MemberDTO> adminList = adminService.findAllAdmin(pageable);

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(adminList);

        mv.addObject("adminList", adminList);
        mv.addObject("paging", paging);

        mv.setViewName("/admin/list");

        return mv;
    }

    @PostMapping(value = "/remove", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void removeAdmin(@RequestParam("searchValue") String searchValue) {

        adminService.removeAdmin(searchValue);
    }

    @PostMapping(value = "/regist", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void registAdmin(@RequestParam("searchValue") String searchValue) {

        adminService.registAdmin(searchValue);
    }

    @GetMapping(value = "/search", produces = "application/json; charset=UTF-8")
    @ResponseBody
    private MemberDTO findSearchMember(@RequestParam("searchValue") String searchValue) {

        MemberDTO searchMember = adminService.findSearchMember(searchValue);

        return searchMember;
    }
}
