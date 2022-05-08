package com.greedy.rotutee.report.service;

import com.greedy.rotutee.report.dto.ReportDTO;
import com.greedy.rotutee.report.entity.Report;
import com.greedy.rotutee.report.repository.ReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * packageName : com.greedy.rotutee.report.service
 * fileName : ReportServiceImpl
 * author : SeoYoung
 * date : 2022-05-05
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-05 SeoYoung 최초 생성
 */
@Service
public class ReportServiceImpl implements ReportService{

    private ReportRepository reportRepository;
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, ModelMapper modelMapper) {
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ReportDTO> findReportList(Pageable pageable, Map<String, String> searchMap) {

        System.out.println("pageable = " + pageable);

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() -1, pageable.getPageSize(),
                Sort.by("reportNo").descending());

        String searchCondition = searchMap.get("searchCondition");
        String searchValue = searchMap.get("searchValue");

        Page<Report> reportEntities = null;
        if(searchCondition == null || searchCondition.equals("")) {
            reportEntities = reportRepository.findAll(pageable);

        } else if(searchCondition.equals("title")){
            reportEntities = reportRepository.findAllByBoardBoardTitleContaining(searchValue, pageable);

        } else if(searchCondition.equals("category")) {
            reportEntities = reportRepository.findAllByDivisionContaining(searchValue, pageable);

        } else if(searchCondition.equals("status")) {
            reportEntities = reportRepository.findAllByProcessStatusContaining(searchValue, pageable);
        }

//        Page<Report> reportEntities = reportRepository.findAll(pageable);
//        Page<ReportDTO> reports = reportEntities.map(Report -> modelMapper.map(Report, ReportDTO.class));

        return reportEntities.map(Report -> modelMapper.map(Report, ReportDTO.class));
    }

    @Override
    public List<ReportDTO> findReportDetail(int boardNo) {

        String jpql = "SELECT report " +
                "FROM Report as report " +
                "WHERE report.board.boardNo = :boardNo " +
                "ORDER BY report.reportNo ASC";
        Query reportQuery = entityManager.createQuery(jpql, Report.class)
                .setParameter("boardNo", boardNo);
        List<Report> reportEntities = reportQuery.getResultList();
        List<ReportDTO> reports = reportEntities.stream().map(Report -> modelMapper.map(Report, ReportDTO.class)).collect(Collectors.toList());

        return reports;
    }

    @Override
    public List<ReportDTO> findReportAnswerDetail(int answerNo) {

        String jpql = "SELECT report " +
                "FROM Report as report " +
                "WHERE report.boardAnswer.answerNo = :answerNo " +
                "ORDER BY report.reportNo ASC";
        Query reportQuery = entityManager.createQuery(jpql, Report.class)
                .setParameter("answerNo", answerNo);
        List<Report> reportEntities = reportQuery.getResultList();
        List<ReportDTO> reports = reportEntities.stream().map(Report -> modelMapper.map(Report, ReportDTO.class)).collect(Collectors.toList());

        return reports;
    }

    @Override
    @Transactional
    public boolean approveBoardReport(int boardNo, int memberNo) {

        /* BoardNo에 해당하는 신고 조회 (3회신고 됐을 시 3개 조회)*/
        String jpql = "SELECT report " +
                "FROM Report as report " +
                "WHERE report.board.boardNo = :boardNo " +
                "ORDER BY report.reportNo ASC";
        Query reportQuery = entityManager.createQuery(jpql, Report.class)
                .setParameter("boardNo", boardNo);
        List<Report> modifiedReportEntities = reportQuery.getResultList();

        for (int i = 0; i < modifiedReportEntities.size(); i++) {
            if(modifiedReportEntities.get(i).getProcessStatus().equals("승인")){
                return false;
            }
            modifiedReportEntities.get(i).setProcessStatus("승인");
            /* 승인 후 게시글 비활성화 */
            modifiedReportEntities.get(i).getBoard().setBoardDeleteYN('Y');
            /* 처리한 관리자 번호 업데이트 */
            modifiedReportEntities.get(i).setAdminNo(memberNo);
            /* 처리한 날짜 업데이트 */
            modifiedReportEntities.get(i).setProcessingDate(new Date(System.currentTimeMillis()));
        }

        return true;
    }

    @Override
    @Transactional
    public boolean approveAnswerReport(int answerNo, int memberNo) {

        String jpql = "SELECT report " +
                "FROM Report as report " +
                "WHERE report.boardAnswer.answerNo = :answerNo " +
                "ORDER BY report.reportNo ASC";
        Query reportQuery = entityManager.createQuery(jpql, Report.class)
                .setParameter("answerNo", answerNo);
        List<Report> modifiedReportEntities = reportQuery.getResultList();

        for (int i = 0; i < modifiedReportEntities.size(); i++) {
            if(modifiedReportEntities.get(i).getProcessStatus().equals("승인")){
                return false;
            }
            modifiedReportEntities.get(i).setProcessStatus("승인");
            /* 승인 후 댓글 비활성화 */
            modifiedReportEntities.get(i).getBoardAnswer().setAnswerYN('Y');
            /* 처리한 관리자 번호 업데이트 */
            modifiedReportEntities.get(i).setAdminNo(memberNo);
            /* 처리한 날짜 업데이트 */
            modifiedReportEntities.get(i).setProcessingDate(new Date(System.currentTimeMillis()));
        }

        return true;
    }

    @Override
    @Transactional
    public boolean withdrawBoardReport(int boardNo, int memberNo) {

        String jpql = "SELECT report " +
                "FROM Report as report " +
                "WHERE report.board.boardNo = :boardNo " +
                "ORDER BY report.reportNo ASC";
        Query reportQuery = entityManager.createQuery(jpql, Report.class)
                .setParameter("boardNo", boardNo);
        List<Report> modifiedReportEntities = reportQuery.getResultList();

        for (int i = 0; i < modifiedReportEntities.size(); i++) {
            modifiedReportEntities.get(i).setProcessStatus("대기");
            /* 철회 후 게시글 활성화 */
            modifiedReportEntities.get(i).getBoard().setBoardDeleteYN('N');
            /* 처리한 관리자 번호 업데이트 */
            modifiedReportEntities.get(i).setAdminNo(memberNo);
            /* 처리한 날짜 업데이트 */
            modifiedReportEntities.get(i).setProcessingDate(new Date(System.currentTimeMillis()));
        }

        return true;
    }

    @Override
    @Transactional
    public boolean withdrawAnswerReport(int answerNo, int memberNo) {

        String jpql = "SELECT report " +
                "FROM Report as report " +
                "WHERE report.boardAnswer.answerNo = :answerNo " +
                "ORDER BY report.reportNo ASC";
        Query reportQuery = entityManager.createQuery(jpql, Report.class)
                .setParameter("answerNo", answerNo);
        List<Report> modifiedReportEntities = reportQuery.getResultList();

        for (int i = 0; i < modifiedReportEntities.size(); i++) {
            modifiedReportEntities.get(i).setProcessStatus("대기");
            /* 철회 후 댓글 활성화 */
            modifiedReportEntities.get(i).getBoardAnswer().setAnswerYN('N');
            /* 처리한 관리자 번호 업데이트 */
            modifiedReportEntities.get(i).setAdminNo(memberNo);
            /* 처리한 날짜 업데이트 */
            modifiedReportEntities.get(i).setProcessingDate(new Date(System.currentTimeMillis()));
        }

        return true;
    }

    @Override
    @Transactional
    public boolean rejectBoardReport(int boardNo, int memberNo) {

        String jpql = "SELECT report " +
                "FROM Report as report " +
                "WHERE report.board.boardNo = :boardNo " +
                "ORDER BY report.reportNo ASC";
        Query reportQuery = entityManager.createQuery(jpql, Report.class)
                .setParameter("boardNo", boardNo);
        List<Report> modifiedReportEntities = reportQuery.getResultList();

        for (int i = 0; i < modifiedReportEntities.size(); i++) {
            modifiedReportEntities.get(i).setProcessStatus("거절");
            /* 처리한 관리자 번호 업데이트 */
            modifiedReportEntities.get(i).setAdminNo(memberNo);
            /* 처리한 날짜 업데이트 */
            modifiedReportEntities.get(i).setProcessingDate(new Date(System.currentTimeMillis()));
        }
        return true;
    }

    @Override
    @Transactional
    public boolean rejectAnswerReport(int answerNo, int memberNo) {

        String jpql = "SELECT report " +
                "FROM Report as report " +
                "WHERE report.boardAnswer.answerNo = :answerNo " +
                "ORDER BY report.reportNo ASC";
        Query reportQuery = entityManager.createQuery(jpql, Report.class)
                .setParameter("answerNo", answerNo);
        List<Report> modifiedReportEntities = reportQuery.getResultList();

        for (int i = 0; i < modifiedReportEntities.size(); i++) {
            modifiedReportEntities.get(i).setProcessStatus("거절");
            /* 처리한 관리자 번호 업데이트 */
            modifiedReportEntities.get(i).setAdminNo(memberNo);
            /* 처리한 날짜 업데이트 */
            modifiedReportEntities.get(i).setProcessingDate(new Date(System.currentTimeMillis()));
        }
        return true;
    }


}
