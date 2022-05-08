package com.greedy.rotutee.report.service;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.report.dto.ReportDTO;
import com.greedy.rotutee.report.entity.Report;
import com.greedy.rotutee.report.entity.ReportCategory;
import com.greedy.rotutee.report.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;

/**
 * packageName : com.greedy.rotutee.report.repository
 * fileName : ReportRepositoryTest
 * author : SeoYoung
 * date : 2022-05-05
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-05 SeoYoung 최초 생성
 */
@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class ReportServiceTest {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void init() {
        assertNotNull(reportRepository);
    }

    @Test
    public void 신고_전체_조회_확인() {

        List<Report> reportEntities = reportRepository.findAll();
        List<ReportDTO> reports = reportEntities.stream().map(Report -> modelMapper.map(Report, ReportDTO.class)).collect(Collectors.toList());

        //then
        for (ReportDTO report : reports) {
            System.out.println("report = " + report);
        }
    }

    @Test
    public void 전체_조건_검색_신고_조회() {
        //given
        String searchCondition1 = "title";
        String searchValue1 = "제목3";

        //when
        /* 검색조건이 전체 일 때 */
        List<Report> searchReportEntities = new ArrayList<>();
        if (searchCondition1.equals("title")) {
            searchReportEntities = reportRepository.findAllByBoardBoardTitleContaining(searchValue1);
        }

        //then
        for (Report searchReportEntity : searchReportEntities) {
            System.out.println("searchReportEntity = " + searchReportEntity);
        }
    }

    @Test
    public void 신고_검색_조회_확인() {

        String searchCondition = "";
//        String searchCondition = "title";
//        String searchCondition = "category";
//        String searchCondition = "status";
        String searchValue = "";
//        String searchValue = "제목3";
//        String searchValue = "게시판";
//        String searchValue = "승인";

        //when

        List<Report> searchReportEntities = new ArrayList<>();
        if (searchCondition.equals("")) {
            searchReportEntities = reportRepository.findAll();
        } else if (searchCondition.equals("title")) {
            searchReportEntities = reportRepository.findAllByBoardBoardTitleContaining(searchValue);
        } else if (searchCondition.equals("category")) {
            searchReportEntities = reportRepository.findAllByDivisionContaining(searchValue);
        } else if (searchCondition.equals("status")) {
            searchReportEntities = reportRepository.findAllByProcessStatusContaining(searchValue);
        }

        //then
        for (Report searchReportEntity : searchReportEntities) {
            System.out.println("searchReportEntity = " + searchReportEntity);
        }

    }

    @Test
    public void 검색조건_없는_조회() {
        //given
        String searchCondition = "";
        String searchValue = "";

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);


    }

    @Test
    public void 게시판번호에_따른_신고조회_확인() {
        //given
        int boardNo = 902;
        String processStatus = "대기";
        //when

        String jpql = "SELECT report " +
                "FROM Report as report " +
                "WHERE report.board.boardNo = :boardNo " +
                "AND report.processStatus = :processStatus " +
                "ORDER BY report.reportNo ASC";
        Query reportQuery = entityManager.createQuery(jpql, Report.class)
                .setParameter("boardNo", boardNo)
                .setParameter("processStatus", processStatus);
        List<Report> reportEntities = reportQuery.getResultList();

//        List<Report> reportEntities = reportRepository.findByBoardBoardNoOrderByReportNoAsc(boardNo);
        List<ReportDTO> reports = reportEntities.stream().map(Report -> modelMapper.map(Report, ReportDTO.class)).collect(Collectors.toList());

        //then
        for (ReportDTO report : reports) {
            System.out.println("report = " + report);
        }
    }

    @Test
    public void 게시물_신고_승인() {
        //given
        int boardNo = 903;
        int memberNo = 11;
        String processStatus = "대기";

        //when
        /* BoardNo에 해당하는 신고 조회 (3회신고 됐을 시 3개 조회)*/
        String jpql = "SELECT report " +
                "FROM Report as report " +
                "WHERE report.board.boardNo = :boardNo " +
                "AND report.processStatus = :processStatus " +
                "ORDER BY report.reportNo ASC";
        Query reportQuery = entityManager.createQuery(jpql, Report.class)
                .setParameter("boardNo", boardNo)
                .setParameter("processStatus", processStatus);
        List<Report> modifiedReportEntities = reportQuery.getResultList();

        for (int i = 0; i < modifiedReportEntities.size(); i++) {
            modifiedReportEntities.get(i).setProcessStatus("승인");
            /* 승인 후 게시글 비활성화 */
            modifiedReportEntities.get(i).getBoard().setBoardDeleteYN('Y');
            /* 처리한 관리자 번호 업데이트 */
            modifiedReportEntities.get(i).setAdminNo(memberNo);
            /* 처리한 날짜 업데이트 */
            modifiedReportEntities.get(i).setProcessingDate(new Date(System.currentTimeMillis()));
        }


        //then
        for (Report modifiedReportEntity : modifiedReportEntities) {
            System.out.println("modifiedReportEntity = " + modifiedReportEntity);
        }

    }

    @Test
    public void 댓글_신고_상세조회() {
        //given
        int answerNo = 903;
        String processStatus = "대기";

        //when
        String jpql = "SELECT report " +
                "FROM Report as report " +
                "WHERE report.boardAnswer.answerNo = :answerNo " +
                "AND report.processStatus = :processStatus " +
                "ORDER BY report.reportNo ASC";
        Query reportQuery = entityManager.createQuery(jpql, Report.class)
                .setParameter("answerNo", answerNo)
                .setParameter("processStatus", processStatus);
        List<Report> reportEntities = reportQuery.getResultList();
        List<ReportDTO> reports = reportEntities.stream().map(Report -> modelMapper.map(Report, ReportDTO.class)).collect(Collectors.toList());

        //then
        for (ReportDTO report : reports) {
            System.out.println("report = " + report);
        }
    }


}
