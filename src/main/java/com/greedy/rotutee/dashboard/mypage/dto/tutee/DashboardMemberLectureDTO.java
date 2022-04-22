package com.greedy.rotutee.dashboard.mypage.dto.tutee;

import lombok.*;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.dto
 * fileName : DashboardMemberLectureDTO
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
public class DashboardMemberLectureDTO {
    private int memberLectureNo;
    private DashboardMemberDTO member;
    private int lectureNo;
}
