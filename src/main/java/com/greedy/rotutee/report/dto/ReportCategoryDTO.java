package com.greedy.rotutee.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName : com.greedy.rotutee.report.dto
 * fileName : ReportCategoryDTO
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
public class ReportCategoryDTO {

    private int reportCategoryNo;
    private String reportCategoryName;

    @Override
    public String toString() {
        return "ReportCategoryDTO{" +
                "reportCategoryNo=" + reportCategoryNo +
                ", reportCategoryName='" + reportCategoryName + '\'' +
                '}';
    }
}
