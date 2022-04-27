package com.greedy.rotutee.dashboard.mypage.mockcustomuser;

import com.greedy.rotutee.dashboard.mypage.controller.MypageDashboardControllerTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.Comparison.Detail;


import java.util.ArrayList;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.mockcustomuser
 * fileName : WithMockCustomUserSecurityContextFactory
 * author : SeoYoung
 * date : 2022-04-25
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-25 SeoYoung 최초 생성
 */
//public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<MypageDashboardControllerTest.WithMockCustomUser> {
//    @Override
//    public SecurityContext createSecurityContext(MypageDashboardControllerTest.WithMockCustomUser customUser) {
//
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        List<GrantedAuthority> grantedAuthorities = new ArrayList();
//        grantedAuthorities.add(new SimpleGrantedAuthority("TUTEE"));
//        User principal = new User(customUser.username(), "1111", true, true, true,true, grantedAuthorities);
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                principal, principal.getPassword(), principal.getAuthorities());
//        authentication.setDetails(new Detail(customUser.username(), "aaaa"));
//        context.setAuthentication(authentication);
//
//        return context;
//    }
//}
