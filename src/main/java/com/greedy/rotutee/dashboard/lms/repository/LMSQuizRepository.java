package com.greedy.rotutee.dashboard.lms.repository;

import com.greedy.rotutee.dashboard.lms.entity.LMSQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.repository
 * fileName : LMSQuizRepository
 * author : SeoYoung
 * date : 2022-04-28
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-28 SeoYoung 최초 생성
 */
public interface LMSQuizRepository extends JpaRepository<LMSQuiz,Integer> {
    LMSQuiz findByClassNo(int classNo);

    LMSQuiz findByQuizNo(int quizNo);
}
