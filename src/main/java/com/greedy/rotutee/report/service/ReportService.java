package com.greedy.rotutee.report.service;

import com.greedy.rotutee.report.dto.ReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * packageName : com.greedy.rotutee.report.service
 * fileName : ReportService
 * author : SeoYoung
 * date : 2022-05-05
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-05 SeoYoung 최초 생성
 */
public interface ReportService {
    Page<ReportDTO> findReportList(Pageable pageable);

    ReportDTO findReportDetail(int reportNo);
}
