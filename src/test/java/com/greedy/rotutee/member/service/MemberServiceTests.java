package com.greedy.rotutee.member.service;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.member.member.dto.LectureCategoryDTO;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * packageName : com.greedy.rotutee.member.service
 * fileName : MemberServiceTests
 * author : 7sang
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-22 7sang 최초 생성
 */

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class MemberServiceTests {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public MemberServiceTests(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @Test
    public void init() {
        assertNotNull(entityManager);
        assertNotNull(memberService);
    }

    @Test
    public void 사용자_등록_테스트_메서드() {

        //given
        MemberDTO member = new MemberDTO();

        //when
        memberService.registMember(member);

        //then
    }

    @Test
    public void 모든_강의_카테고리_정보_조회_테스트_메서드() {

        //given

        //when
        List<LectureCategoryDTO> lectureCategoryList = memberService.findLectureCategoryList();

        //then
        assertNotNull(lectureCategoryList);
    }

    @Test
    public void 이메일_중복_확인_테스트_메서드() {

        //given
        String checkEmail = "7sangne@gmail.com";

        //when
        boolean result =  memberService.duplicateEmail(checkEmail);

        //then
        assertTrue(result);
    }

    @Test
    public void 사용자_비밀번호_변경_테스트_메서드() {

        //given
        MemberDTO member = new MemberDTO();
        member.setEmail("7sangne@gail.com");

        //when
        memberService.findMemberPwd(member);
    }

//    @Test
//    public void modifyPassword() {
//
//
//        if(!passwordEncoder.matches(password, loginMember.getPwd())) {
//
//            return "redirect:/member/modifypwd";
//        }
//
//        memberService.modifyPassword(loginMember, modifyPassword);
//
//        return "redirect:/member/logout";
//    }
}
