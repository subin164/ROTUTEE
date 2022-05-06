package com.greedy.rotutee.report.repository;

import com.greedy.rotutee.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.greedy.rotutee.report.repository
 * fileName : ReportRepository
 * author : SeoYoung
 * date : 2022-05-05
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-05 SeoYoung 최초 생성
 */
public interface ReportRepository extends JpaRepository<Report, Integer> {


}
