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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
