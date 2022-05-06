package com.greedy.rotutee.lecture.request.model.repository;


import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.lecture.request.repository.RequestLectureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, BeanConfiguration.class, JPAConfiguration.class})
public class RequestLectureRepositoryTest {

    @Autowired
    private RequestLectureRepository requestLectureRepository;

    @Test
    public void 레포지토리_의존성_테스트() {

        assertNotNull(requestLectureRepository);
        System.out.println("requestLectureRepository = " + requestLectureRepository);
    }

}
