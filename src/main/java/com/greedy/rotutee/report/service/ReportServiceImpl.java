package com.greedy.rotutee.report.service;

import com.greedy.rotutee.report.dto.ReportDTO;
import com.greedy.rotutee.report.entity.Report;
import com.greedy.rotutee.report.repository.ReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, ModelMapper modelMapper) {
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ReportDTO> findReportList(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() -1, pageable.getPageSize(),
                Sort.by("reportNo").ascending());

        Page<Report> reportEntities = reportRepository.findAll(pageable);
        Page<ReportDTO> reports = reportEntities.map(Report -> modelMapper.map(Report, ReportDTO.class));


        return reports;
    }

    @Override
    public ReportDTO findReportDetail(int boardNo) {

        List<Report> reportEntities = reportRepository.findByBoardBoardNoOrderByReportNoAsc(boardNo);


        return null;
    }
}
