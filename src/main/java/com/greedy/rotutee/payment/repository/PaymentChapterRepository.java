package com.greedy.rotutee.payment.repository;

import com.greedy.rotutee.payment.entity.PaymentChapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.payment.repository
 * fileName : PaymentChapterRepository
 * author : soobeen
 * date : 2022-05-13
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-13          soobeen     최초 생성
 */

public interface PaymentChapterRepository extends JpaRepository<PaymentChapter, Integer> {
    List<PaymentChapter> findByLectureNo(int lectureNo);
}
