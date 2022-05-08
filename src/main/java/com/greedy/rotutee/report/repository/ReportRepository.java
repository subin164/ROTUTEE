package com.greedy.rotutee.report.repository;

import com.greedy.rotutee.report.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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


    List<Report> findByBoardBoardNoOrderByReportNoAsc(int boardNo);

    List<Report> findByBoardBoardNo(int boardNo);

    List<Report> findByProcessStatusContaining(String searchValue);

    Page<Report> findAllByBoardBoardTitleContaining(String searchValue, Pageable pageable);

    Page<Report> findAllByDivisionContaining(String searchValue, Pageable pageable);

    Page<Report> findAllByProcessStatusContaining(String searchValue, Pageable pageable);

    List<Report> findAllByBoardBoardTitleContaining(String searchValue1);

    List<Report> findAllByDivisionContaining(String searchValue);

    List<Report> findAllByProcessStatusContaining(String searchValue);
}
