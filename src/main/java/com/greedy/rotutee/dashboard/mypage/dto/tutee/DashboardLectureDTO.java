package com.greedy.rotutee.dashboard.mypage.dto;

import lombok.*;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.dto
 * fileName : DashboardLectureDTO
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
public class DashboardLectureDTO {
 private int lectureNo;
 private String lectureTitle;

}
