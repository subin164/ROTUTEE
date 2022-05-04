package com.greedy.rotutee.dashboard.lms.repository;

import com.greedy.rotutee.dashboard.lms.entity.LMSSubmissonQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.repository
 * fileName : LMSSubmissionQuizRepository
 * author : SeoYoung
 * date : 2022-04-30
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-30 SeoYoung 최초 생성
 */
public interface LMSSubmissionQuizRepository extends JpaRepository<LMSSubmissonQuiz, Integer> {
    LMSSubmissonQuiz findByQuizNo(int quizNo);

    List<LMSSubmissonQuiz> findByMemberLectureNo(int memberLectureNo);
}
