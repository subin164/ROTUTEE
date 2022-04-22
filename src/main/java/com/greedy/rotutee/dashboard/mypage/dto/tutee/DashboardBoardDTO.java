package com.greedy.rotutee.dashboard.mypage.dto.tutee;

import lombok.*;
/**
 * packageName : com.greedy.rotutee.dashboard.mypage.dto
 * fileName : DashboardBoardDTO
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
public class DashboardBoardDTO {

    private int boardNo;
    private String title;
    private int categoryNo;
}
