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

import java.util.ArrayList;
import java.util.List;
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
    public void 게시판번호에_따른_신고조회_확인() {
        //given
        int boardNo = 903;
        //when
        List<Report> reportEntities = reportRepository.findByBoardBoardNoOrderByReportNoAsc(boardNo);

        //then
        for (Report reportEntity : reportEntities) {
            System.out.println("reportEntity = " + reportEntity);
        }
    }


}
