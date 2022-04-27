package com.greedy.rotutee.study.service;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.service.MemberService;
import com.greedy.rotutee.study.dto.StudyDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class StudyServiceTests {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public StudyServiceTests(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @org.junit.jupiter.api.Test
    public void 초기화_테스트() {
        assertNotNull(entityManager);
        assertNotNull(memberService);
    }
    @Test
    public void 스터디_글작성() {

        //given
        StudyDTO newStudy = new StudyDTO();
        MemberDTO member = new MemberDTO();
        newStudy.setStudyNo(1);
        newStudy.setTitle("제목1");
        newStudy.setContent("내용1");
        newStudy.setStartDate(new Date(System.currentTimeMillis()));
        newStudy.setEndDate(Date.valueOf("2022-12-31"));
        newStudy.setWriter(member);
        newStudy.setLimited(5);
        newStudy.setCount(0);
        newStudy.setLinked("discode.djklsdjfla.sdf");
        newStudy.setStatus("Y");

        System.out.println(newStudy);

    }
}
