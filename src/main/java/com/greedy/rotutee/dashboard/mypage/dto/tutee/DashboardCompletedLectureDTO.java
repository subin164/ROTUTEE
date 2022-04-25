package com.greedy.rotutee.dashboard.mypage.dto.tutee;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.dto
 * fileName : DashboardCompletedLectureDTO
 * author : SeoYoung
 * date : 2022-04-21
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-21 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DashboardCompletedLectureDTO {

    private int timeNo;
    private String watchedStatus;
    private Date completedDate;
    private DashboardMemberLectureDTO memberLecture;
}
