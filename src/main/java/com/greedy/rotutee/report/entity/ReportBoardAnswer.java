package com.greedy.rotutee.report.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * packageName : com.greedy.rotutee.report.entity
 * fileName : ReportBoardAnswer
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
@Entity(name = "Report_boardAnswer")
@Table(name = "TBL_ANSWER")
@SequenceGenerator(
        name = "REPORT_BOARD_ANSWER_GENERATOR",
        sequenceName = "ANSWER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class ReportBoardAnswer {

    @Id
    @Column(name = "ANSWER_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "REPORT_BOARD_ANSWER_GENERATOR"
    )
    private int answerNo;

    @Column(name = "ANSWER_CONTENT")
    private String answerContent;

    @Column(name = "ANSWER_YN")
    private char answerYN;

    @Column(name = "ANSWER_REPORT_COUNT")
    private int answerReportCount;

    @Column(name = "ANSWER_CREATED_DATE")
    private Timestamp answerCreatedDate;

    @Column( name = "ANSWER_MODIFY_DATE")
    private Date answerModifyDate;

    @Column(name = "ANSWER_DELETE_DATE")
    private Date answerDeleteDate;

    @Column(name ="BOARD_NO")
    private int boardNo;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Override
    public String toString() {
        return "ReportBoardAnswer{" +
                "answerNo=" + answerNo +
                ", answerContent='" + answerContent + '\'' +
                ", answerYN=" + answerYN +
                ", answerReportCount=" + answerReportCount +
                ", answerCreatedDate=" + answerCreatedDate +
                ", answerModifyDate=" + answerModifyDate +
                ", answerDeleteDate=" + answerDeleteDate +
                ", boardNo=" + boardNo +
                ", memberNo=" + memberNo +
                '}';
    }
}
