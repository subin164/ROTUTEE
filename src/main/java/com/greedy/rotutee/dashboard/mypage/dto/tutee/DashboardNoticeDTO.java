package com.greedy.rotutee.dashboard.mypage.dto.tutee;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.dto
 * fileName : MyPageDashboardAlarmDTO
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DashboardNoticeDTO {

    private int noticeNo;
    private String content;
    private int categoryNo;
    private int memberNo;
    private Date noticedTime;
}
