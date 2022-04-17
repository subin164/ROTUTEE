package com.greedy.rotutee.point.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/point")
public class PointController {

    @GetMapping("/productlist")
    public String pointProduct() {

        return "/point/list";
    }

}
