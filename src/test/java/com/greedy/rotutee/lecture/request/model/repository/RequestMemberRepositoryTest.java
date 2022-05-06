package com.greedy.rotutee.lecture.request.model.repository;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.lecture.request.entity.Member;
import com.greedy.rotutee.lecture.request.repository.RequestMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, BeanConfiguration.class, JPAConfiguration.class})
public class RequestMemberRepositoryTest {

    @Autowired
    private RequestMemberRepository requestMemberRepository;

    @Test
    public void 레포지토리_의존성_테스트() {

        assertNotNull(requestMemberRepository);
        System.out.println("requestMemberRepository = " + requestMemberRepository);
    }

    @Test
    public void 회원조회_테스트() {

        int memberNo = 16;

        Member member = requestMemberRepository.findByNo(memberNo);
        assertNotNull(member);

    }
}
