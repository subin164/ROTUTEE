package com.greedy.rotutee.report.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.report.entity
 * fileName : ReportCategory
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
@Setter
@Getter
@Entity(name = "Report_category")
@Table(name = "TBL_REASON_REPORT")
public class ReportCategory {


    @Id
    @Column(name = "REPORT_REASON_NO")
    private int reportCategoryNo;

    @Column(name = "REPORT_REASON_NAME")
    private String reportCategoryName;

    @Override
    public String toString() {
        return "ReportCategory{" +
                "reportCategoryNo=" + reportCategoryNo +
                ", reportCategoryName='" + reportCategoryName + '\'' +
                '}';
    }
}
