package com.greedy.rotutee.lecture.request.model.repository;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.lecture.request.entity.LectureCategory;
import com.greedy.rotutee.lecture.request.repository.RequestLectureCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName      : com.greedy.rotutee.lecture.request.model.repository
 * fileName         : RequestLectureCategoryRepositoryTest
 * author           : SEOK
 * date             : 2022-05-02
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-02      SEOK         최초 생성
 */
@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, BeanConfiguration.class, JPAConfiguration.class})
public class RequestLectureCategoryRepositoryTest {

    @Autowired
    private RequestLectureCategoryRepository requestLectureCategoryRepository;

    @Test
    public void 레포지토리_의존성_테스트() {

        assertNotNull(requestLectureCategoryRepository);
        System.out.println("requestLectureCategoryRepository = " + requestLectureCategoryRepository);
    }

    @Test
    public void 카테고리_번호로_강의_카테고리_객체_조회_테스트() {
        //given
        int categoryNo = 1;
        //when
        LectureCategory category = requestLectureCategoryRepository.findByLectureCategoryNo(categoryNo);
        //then
        assertNotNull(category);
    }

}
