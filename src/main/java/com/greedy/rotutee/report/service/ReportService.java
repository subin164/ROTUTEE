package com.greedy.rotutee.report.service;

import com.greedy.rotutee.report.dto.ReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

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
    Page<ReportDTO> findReportList(Pageable pageable, Map<String,String> searchMap);

    List<ReportDTO> findReportDetail(int reportNo);

    boolean approveBoardReport(int boardNo, int memberNo);

    List<ReportDTO> findReportAnswerDetail(int answerNo);

    boolean approveAnswerReport(int answerNo, int memberNo);

    boolean withdrawBoardReport(int boardNo, int memberNo);

    boolean withdrawAnswerReport(int answerNo, int memberNo);

    boolean rejectBoardReport(int boardNo, int memberNo);

    boolean rejectAnswerReport(int answerNo, int memberNo);
}
