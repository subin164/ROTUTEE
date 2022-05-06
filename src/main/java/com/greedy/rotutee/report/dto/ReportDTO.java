package com.greedy.rotutee.report.dto;

import com.greedy.rotutee.report.entity.ReportBoardAnswer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.report.dto
 * fileName : ReportDTO
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
public class ReportDTO {

    private int reportNo;
    private String content;
    private String reportedDate;
    private String division;
    private String processStatus;
    private ReportBoardDTO board;
    private ReportBoardAnswerDTO boardAnswer;
    private ReportMemberDTO member;
    private ReportCategoryDTO reportCategory;
    private ReportMemberDTO accusedMember;
    private Integer adminNo;
    private Date processingDate;

    @Override
    public String toString() {
        return "ReportDTO{" +
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
