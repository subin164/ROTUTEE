package com.greedy.rotutee.study.repository;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.entity.Study;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.Persistence;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, BeanConfiguration.class, JPAConfiguration.class})

public class StudyRepositoryTest{

    @Autowired
    private StudyRepository studyRepository;

    @Test
    public void 모집글_전체_목록_조회(){

        //given

        Study study = new Study();

        study.setStudyNo(1);
        study.setTitle("모집글1호");
        study.setContent("모집글1호 입니다.");
        study.setStartDate(Date.valueOf("2022-04-21"));
        study.setEndDate(Date.valueOf("2022-05-05"));
        study.setMemberNo(13);
        study.setWriter("공당근");
        study.setLimited(5);
        study.setCount(1);
        study.setLinked("discod.12312.cd");
        study.setStatus("Y");
        study.setTagNo(5);

        //when



        //then
        assertNotNull(study);
        System.out.println(study);

    }


}
