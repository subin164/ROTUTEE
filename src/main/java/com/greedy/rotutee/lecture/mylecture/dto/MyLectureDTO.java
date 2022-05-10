package com.greedy.rotutee.lecture.mylecture.dto;

import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardMemberDTO;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.lecture.mylecture.dto
 * fileName : MyLectureDTO
 * author : SeoYoung
 * date : 2022-05-10
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-10 SeoYoung 최초 생성
 */
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MyLectureDTO {

    private int lectureNo;
    private String lectureName;
    private int lecturePrice;
    private String lectureLevel;
    private String lectureSummary;
    private String lecturedetails;
    private String revisionHistory;
    private String lectureApprovalStatus;
    private Date lectureOpeningDate;
    private DashboardMemberDTO member;
    private Date applicationDate;
    private String applicationDivision;
    private int lectureCategoryNo;
}
