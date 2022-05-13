package com.greedy.rotutee.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * packageName : com.greedy.rotutee.main.controller
 * fileName : asd
 * author : 7sang
 * date : 2022-05-11
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-11 7sang 최초 생성
 */

@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {

//    private static final Logger logger = LoggerFactory.getLogger(WebAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ade)
            throws IOException, ServletException {

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication = " + authentication);

//        String referer = (String)request.getHeader("REFERER");
        response.sendRedirect("/error/denied");
    }

}


