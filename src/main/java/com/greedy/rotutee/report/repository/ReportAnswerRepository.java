package com.greedy.rotutee.report.repository;

import com.greedy.rotutee.report.entity.ReportBoardAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.greedy.rotutee.report.repository
 * fileName : ReportAnswerRepository
 * author : SeoYoung
 * date : 2022-05-12
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-12 SeoYoung 최초 생성
 */
public interface ReportAnswerRepository extends JpaRepository<ReportBoardAnswer, Integer> {
}
