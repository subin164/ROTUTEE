package com.greedy.rotutee.report.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.report.entity
 * fileName : Report
 * author : SeoYoung
 * date : 2022-05-05
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-05 SeoYoung 최초 생성
 */

@Entity(name = "Report")
@Table(name = "TBL_REPORT")
@SequenceGenerator(
        name = "REPORT_SEQ_GENERATOR",
        sequenceName = "REPORT_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Report {

    @Id
    @Column(name = "REPORT_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "REPORT_SEQ_GENERATOR"
    )
    private int reportNo;

    @Column(name = "REPORT_CONTENT")
    private String content;

    @Column(name = "REPORT_DATE")
    private String reportedDate;

    @Column(name = "REPORT_BOARD_DIVISION")
    private String division;                    //게시판, 답변

    @Column(name = "REPORT_STATUS")
    private String processStatus;               //신고승인상태

    @ManyToOne
    @JoinColumn(name = "BOARD_NO")
    private ReportBoard board;

    @ManyToOne
    @JoinColumn(name = "ANSWER_NO")
    private ReportBoardAnswer boardAnswer;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private ReportMember member;

    @ManyToOne
    @JoinColumn(name = "REPORT_REASON_NO")
    private ReportCategory reportCategory;                 //욕설,광고성,도배,기타

    @ManyToOne
    @JoinColumn(name = "REPORT_ACCUSED_MEMBER_NO")
    private ReportMember accusedMember;

    @Column(name = "REPORT_MANAGE_MEMBER_NO")
    private Integer adminNo;

    @Column(name = "REPORT_PROCESSING_DATE")
    private Date processingDate;

    public Report() {}

    public Report(int reportNo, String content, String division, String processStatus, ReportBoard board, ReportBoardAnswer boardAnswer, ReportMember member, ReportCategory reportCategory, ReportMember accusedMember, Integer adminNo, Date processingDate) {
        this.reportNo = reportNo;
        this.content = content;
        this.division = division;
        this.processStatus = processStatus;
        this.board = board;
        this.boardAnswer = boardAnswer;
        this.member = member;
        this.reportCategory = reportCategory;
        this.accusedMember = accusedMember;
        this.adminNo = adminNo;
        this.processingDate = processingDate;
    }

    @PrePersist
    public void prePersist() {
        this.reportedDate = this.reportedDate == null? String.valueOf(new Date(System.currentTimeMillis())) : this.reportedDate;
    }

    public int getReportNo() {
        return reportNo;
    }

    public void setReportNo(int reportNo) {
        this.reportNo = reportNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(String reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public ReportBoard getBoard() {
        return board;
    }

    public void setBoard(ReportBoard board) {
        this.board = board;
    }

    public ReportBoardAnswer getBoardAnswer() {
        return boardAnswer;
    }

    public void setBoardAnswer(ReportBoardAnswer boardAnswer) {
        this.boardAnswer = boardAnswer;
    }

    public ReportMember getMember() {
        return member;
    }

    public void setMember(ReportMember member) {
        this.member = member;
    }

    public ReportCategory getReportCategory() {
        return reportCategory;
    }

    public void setReportCategory(ReportCategory reportCategory) {
        this.reportCategory = reportCategory;
    }

    public ReportMember getAccusedMember() {
        return accusedMember;
    }

    public void setAccusedMember(ReportMember accusedMember) {
        this.accusedMember = accusedMember;
    }

    public Integer getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(Integer adminNo) {
        this.adminNo = adminNo;
    }

    public Date getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportNo=" + reportNo +
                ", content='" + content + '\'' +
                ", reportedDate='" + reportedDate + '\'' +
                ", division='" + division + '\'' +
                ", processStatus='" + processStatus + '\'' +
                ", board=" + board +
                ", boardAnswer=" + boardAnswer +
                ", member=" + member +
                ", reportCategory=" + reportCategory +
                ", accusedMember=" + accusedMember +
                ", adminNo=" + adminNo +
                ", processingDate=" + processingDate +
                '}';
    }
}
