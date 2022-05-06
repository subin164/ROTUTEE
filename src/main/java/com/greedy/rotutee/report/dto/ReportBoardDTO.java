package com.greedy.rotutee.report.dto;


import com.greedy.rotutee.report.entity.ReportMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.report.dto
 * fileName : ReportBoardDTO
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
public class ReportBoardDTO {

    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private Date boardCreationDate;
    private Date boardModifiedDate;
    private Date boardDeletionDate;
    private char boardDeleteYN;
    private int boardViewCount;
    private int boardCategoryNo;
    private ReportMemberDTO member;
    private int boardReportCount;
    private char bulletinBoardSecretYN;

    @Override
    public String toString() {
        return "ReportBoardDTO{" +
                "boardNo=" + boardNo +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                ", boardCreationDate=" + boardCreationDate +
                ", boardModifiedDate=" + boardModifiedDate +
                ", boardDeletionDate=" + boardDeletionDate +
                ", boardDeleteYN='" + boardDeleteYN + '\'' +
                ", boardViewCount=" + boardViewCount +
                ", boardCategoryNo=" + boardCategoryNo +
                ", member=" + member +
                ", boardReportCount=" + boardReportCount +
                ", bulletinBoardSecretYN='" + bulletinBoardSecretYN + '\'' +
                '}';
    }
}
