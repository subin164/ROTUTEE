package com.greedy.rotutee.dashboard.mypage.dto;

import lombok.*;

import java.sql.Date;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.dto
 * fileName : DashboardMemberDTO
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
public class DashboardMemberDTO {

    private int memberNo;
    private String memberName;
    private String email;
    private String pwd;
    private String nickname;
    private String phone;
    private String introduction;
    private Date registedDate;
    private Date withdrawedDate;
    private String withdrawalStatusYn;
    private String rouletteChance;
}
