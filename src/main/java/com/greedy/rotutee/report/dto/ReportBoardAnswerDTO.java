package com.greedy.rotutee.report.dto;

import com.greedy.rotutee.report.entity.ReportBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * packageName : com.greedy.rotutee.report.dto
 * fileName : ReportBoardAnswerDTO
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
public class ReportBoardAnswerDTO {
    private int answerNo;
    private String answerContent;
    private char answerYN;
    private int answerReportCount;
    private Timestamp answerCreatedDate;
    private Date answerModifyDate;
    private Date answerDeleteDate;
    private int boardNo;
    private int memberNo;

    @Override
    public String toString() {
        return "ReportBoardAnswerDTO{" +
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
