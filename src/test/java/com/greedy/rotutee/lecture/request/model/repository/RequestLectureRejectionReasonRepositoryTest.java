package com.greedy.rotutee.lecture.request.model.repository;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.lecture.request.entity.LectureRejectionReason;
import com.greedy.rotutee.lecture.request.repository.RequestLectureRejectionReasonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName      : com.greedy.rotutee.lecture.request.model.repository
 * fileName         : RequestLectureRejectionReasonRepositoryTest
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
public class RequestLectureRejectionReasonRepositoryTest {

    @Autowired
    private RequestLectureRejectionReasonRepository requestLectureRejectionReasonRepository;

    @Test
    public void 레포지토리_의존성_테스트() {

        assertNotNull(requestLectureRejectionReasonRepository);
        System.out.println("requestLectureRejectionReasonRepository = " + requestLectureRejectionReasonRepository);
    }

    @Test
    public void 거절_카테고리_번호로_강의_거절_객체_조회_테스트() {
        //given
        int rejectionCategoryNo = 1;
        //when
        LectureRejectionReason reasonEntity = requestLectureRejectionReasonRepository.findByLectureRejectionReasonNo(rejectionCategoryNo);
        //then
        assertNotNull(reasonEntity);
    }
}
