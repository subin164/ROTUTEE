package com.greedy.rotutee.report.service;

import com.greedy.rotutee.notice.dto.NoticeCategoryDTO;
import com.greedy.rotutee.notice.dto.NoticeDTO;
import com.greedy.rotutee.notice.entity.Notice;
import com.greedy.rotutee.notice.repository.NoticeRepository;
import com.greedy.rotutee.report.dto.ReportDTO;
import com.greedy.rotutee.report.entity.Report;
import com.greedy.rotutee.report.entity.ReportBoard;
import com.greedy.rotutee.report.entity.ReportBoardAnswer;
import com.greedy.rotutee.report.repository.ReportAnswerRepository;
import com.greedy.rotutee.report.repository.ReportBoardRepository;
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
import java.util.ArrayList;
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
    private NoticeRepository noticeRepository;
    private ReportBoardRepository reportBoardRepository;
    private ReportAnswerRepository reportAnswerRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, ModelMapper modelMapper, NoticeRepository noticeRepository, ReportBoardRepository reportBoardRepository, ReportAnswerRepository reportAnswerRepository) {
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
        this.noticeRepository = noticeRepository;
        this.reportBoardRepository = reportBoardRepository;
        this.reportAnswerRepository = reportAnswerRepository;
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

        /* 신고한 사람에게 신고 승인 알림 전송*/
        List<Integer> reporterMemberNums = new ArrayList<>();
        int accusedMemberNo = 0;
        for(int i = 0; i < modifiedReportEntities.size(); i++) {

            /*게시물 신고한 사람들 멤버번호 리스트 저장*/
            int reporterMemberNo = modifiedReportEntities.get(i).getMember().getNo();
            reporterMemberNums.add(reporterMemberNo);

            String reporterNoticeContent = "[" + modifiedReportEntities.get(i).getBoard().getBoardTitle() +  "] 의 신고가 승인되었습니다.";

            NoticeDTO notice = new NoticeDTO();
            NoticeCategoryDTO noticeCategory = new NoticeCategoryDTO();
            noticeCategory.setNoticeCategoryNo(4);                          //신고결과
            notice.setNoticeContent(reporterNoticeContent);
            notice.setNoticeCategory(noticeCategory);
            notice.setNoticedDate(new Date(System.currentTimeMillis()));
            notice.setMemberNo(reporterMemberNo);

            Notice noticeEntity = modelMapper.map(notice, Notice.class);
            noticeRepository.save(noticeEntity);

            /*신고 당한사람 알림 전송*/
            accusedMemberNo = modifiedReportEntities.get(i).getAccusedMember().getNo();
            String accusedNoticeContent = "[" + modifiedReportEntities.get(i).getBoard().getBoardTitle() + "] 게시물 신고가 승인되었습니다.";
            notice.setNoticeContent(accusedNoticeContent);
            notice.setNoticedDate(new Date(System.currentTimeMillis()));
            notice.setMemberNo(accusedMemberNo);

            Notice accusedNoticeEntity = modelMapper.map(notice, Notice.class);
            noticeRepository.save(accusedNoticeEntity);

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

        /* 신고한 사람에게 신고 승인 알림 전송*/
        List<Integer> reporterMemberNums = new ArrayList<>();
        int accusedMemberNo = 0;
        for(int i = 0; i < modifiedReportEntities.size(); i++) {

            /*댓글 신고한 사람들 멤버번호 리스트 저장*/
            int reporterMemberNo = modifiedReportEntities.get(i).getMember().getNo();
            reporterMemberNums.add(reporterMemberNo);

            String reporterNoticeContent = "[" + modifiedReportEntities.get(i).getBoardAnswer().getAnswerContent() + "] 의 신고가 승인되었습니다.";

            NoticeDTO notice = new NoticeDTO();
            NoticeCategoryDTO noticeCategory = new NoticeCategoryDTO();
            noticeCategory.setNoticeCategoryNo(4);                          //신고결과
            notice.setNoticeContent(reporterNoticeContent);
            notice.setNoticeCategory(noticeCategory);
            notice.setNoticedDate(new Date(System.currentTimeMillis()));
            notice.setMemberNo(reporterMemberNo);

            Notice noticeEntity = modelMapper.map(notice, Notice.class);
            noticeRepository.save(noticeEntity);

            /*신고 당한사람 알림 전송*/
            accusedMemberNo = modifiedReportEntities.get(i).getBoardAnswer().getMemberNo();
            String accusedNoticeContent = "[" + modifiedReportEntities.get(i).getBoardAnswer().getAnswerContent() + "] 답변 신고가 승인되었습니다.";
            notice.setNoticeContent(accusedNoticeContent);
            notice.setNoticedDate(new Date(System.currentTimeMillis()));
            notice.setMemberNo(accusedMemberNo);

            Notice accusedNoticeEntity = modelMapper.map(notice, Notice.class);
            noticeRepository.save(accusedNoticeEntity);

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

        /* 신고한 사람에게 신고 거절 알림 전송*/
        List<Integer> reporterMemberNums = new ArrayList<>();
        int accusedMemberNo = 0;
        for(int i = 0; i < modifiedReportEntities.size(); i++) {

            /*게시물 신고한 사람들 멤버번호 리스트 저장*/
            int reporterMemberNo = modifiedReportEntities.get(i).getMember().getNo();
            reporterMemberNums.add(reporterMemberNo);

            String reporterNoticeContent = "[" + modifiedReportEntities.get(i).getBoard().getBoardTitle() + "] 의 신고가 거절되었습니다.";

            NoticeDTO notice = new NoticeDTO();
            NoticeCategoryDTO noticeCategory = new NoticeCategoryDTO();
            noticeCategory.setNoticeCategoryNo(4);                          //신고결과
            notice.setNoticeContent(reporterNoticeContent);
            notice.setNoticeCategory(noticeCategory);
            notice.setNoticedDate(new Date(System.currentTimeMillis()));
            notice.setMemberNo(reporterMemberNo);

            Notice noticeEntity = modelMapper.map(notice, Notice.class);
            noticeRepository.save(noticeEntity);

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

        /* 신고한 사람에게 신고 거절 알림 전송*/
        List<Integer> reporterMemberNums = new ArrayList<>();
        int accusedMemberNo = 0;
        for(int i = 0; i < modifiedReportEntities.size(); i++) {

            /*댓글 신고한 사람들 멤버번호 리스트 저장*/
            int reporterMemberNo = modifiedReportEntities.get(i).getMember().getNo();
            reporterMemberNums.add(reporterMemberNo);

            String reporterNoticeContent = "[" + modifiedReportEntities.get(i).getBoardAnswer().getAnswerContent() + "] 의 신고가 거절되었습니다.";

            NoticeDTO notice = new NoticeDTO();
            NoticeCategoryDTO noticeCategory = new NoticeCategoryDTO();
            noticeCategory.setNoticeCategoryNo(4);                          //신고결과
            notice.setNoticeContent(reporterNoticeContent);
            notice.setNoticeCategory(noticeCategory);
            notice.setNoticedDate(new Date(System.currentTimeMillis()));
            notice.setMemberNo(reporterMemberNo);

            Notice noticeEntity = modelMapper.map(notice, Notice.class);
            noticeRepository.save(noticeEntity);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean registBoardReport(ReportDTO report) {

        /*게시물 총 신고 횟수컬럼 +1 증가*/
        int boardNo = report.getBoard().getBoardNo();
        List<Report> reportCountEntity = reportRepository.findByBoardBoardNo(boardNo);
        int BeforeReportCount = reportCountEntity.size();
        int afterReportCount = BeforeReportCount + 1;

        ReportBoard reportBoard = reportBoardRepository.findById(boardNo).get();
        reportBoard.setBoardReportCount(afterReportCount);
        reportBoardRepository.save(reportBoard);

        /*게시물 신고 insert*/
        Report reportEntity = modelMapper.map(report, Report.class);
        reportRepository.save(reportEntity);

        /*게시물 신고 알림 전송*/
        int writerNo = report.getAccusedMember().getNo();
        String noticeContent = "[" + report.getBoard().getBoardTitle() + "] 게시물이 신고되었습니다.";
        NoticeDTO notice = new NoticeDTO();
        NoticeCategoryDTO category = new NoticeCategoryDTO();
        category.setNoticeCategoryNo(5);
        notice.setNoticeContent(noticeContent);
        notice.setNoticeCategory(category);
        notice.setMemberNo(writerNo);
        notice.setNoticedDate(new Date(System.currentTimeMillis()));

        Notice noticeEntity = modelMapper.map(notice, Notice.class);
        noticeRepository.save(noticeEntity);

        return true;
    }

    @Override
    public boolean registAnswerReport(ReportDTO report) {

        /*댓글 총 신고 횟수컬럼 +1 증가*/
        int answerNo = report.getBoardAnswer().getAnswerNo();
        List<Report> reportCountEntity = reportRepository.findByBoardAnswerAnswerNo(answerNo);
        int BeforeReportCount = reportCountEntity.size();
        int afterReportCount = BeforeReportCount + 1;

        ReportBoardAnswer reportBoardAnswerEntity = reportAnswerRepository.findById(answerNo).get();
        reportBoardAnswerEntity.setAnswerReportCount(afterReportCount);
        reportAnswerRepository.save(reportBoardAnswerEntity);

        /*댓글신고 insert*/
        Report reportAnswerEntity = modelMapper.map(report, Report.class);
        reportRepository.save(reportAnswerEntity);

        /*댓글 신고 알림 전송*/
        int writerNo = report.getAccusedMember().getNo();
        String noticeContent = "[" + report.getBoardAnswer().getAnswerContent() + "] 댓글이 신고되었습니다.";
        NoticeDTO notice = new NoticeDTO();
        NoticeCategoryDTO category = new NoticeCategoryDTO();
        category.setNoticeCategoryNo(5);
        notice.setNoticeContent(noticeContent);
        notice.setNoticeCategory(category);
        notice.setMemberNo(writerNo);
        notice.setNoticedDate(new Date(System.currentTimeMillis()));

        Notice noticeEntity = modelMapper.map(notice, Notice.class);
        noticeRepository.save(noticeEntity);

        return true;
    }


}
