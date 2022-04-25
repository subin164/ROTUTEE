package com.greedy.rotutee.request.repository;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.member.tutorRequest.entity.Career;
import com.greedy.rotutee.member.tutorRequest.entity.TutorApply;
import com.greedy.rotutee.member.tutorRequest.repository.TutorApplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * packageName : com.greedy.rotutee.request.repository
 * fileName : RquestRepositroyTests
 * author : 7sang
 * date : 2022-04-25
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-25 7sang 최초 생성
 */


@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class RequestRepositoryTests {

    private final TutorApplyRepository tutorApplyRepository;

    @Autowired
    public RequestRepositoryTests(TutorApplyRepository tutorApplyRepository) {

        this.tutorApplyRepository = tutorApplyRepository;
    }

    @Test
    public void init() {
        assertNotNull(tutorApplyRepository);
    }

    @Test
    public void 튜터_신청_내역_조회_테스트_메서드() {

        //given

        //when
        List<TutorApply> tutorApplyList = tutorApplyRepository.findAll();

        //then
        assertNotNull(tutorApplyList);
        tutorApplyList.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void 튜터_신청_내역_상세_조회_테스트_메서드() {

        //given
        int historyNo = 21;
        
        //when
        TutorApply tutorApply = tutorApplyRepository.findById(historyNo).get();
        
        //then
        assertNotNull(tutorApply);
        System.out.println("tutorApply = " + tutorApply);
//        tutorApply.getQualificationList().forEach(System.out::println);
//        tutorApply.getCareerList().forEach(System.out::println);
        for(Career career : tutorApply.getCareerList()){

            System.out.println("career = " + career);
        }
    }
}
