package com.greedy.rotutee.dashboard.mypage.dto.tutee;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.dto
 * fileName : DashboardLectureWatchDTO
 * author : SeoYoung
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DashboardLectureWatchDTO {

    private int watchNo;
    private Date watchedDate;
    private DashboardLectureDTO lecture;
    private int memberNo;

}
