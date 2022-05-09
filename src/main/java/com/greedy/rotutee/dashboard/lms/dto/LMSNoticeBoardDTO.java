package com.greedy.rotutee.dashboard.lms.dto;

import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardMemberDTO;
import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.dto
 * fileName : LMSNoticeBoardDTO
 * author : SeoYoung
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-22 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LMSNoticeBoardDTO {
    private int boardNo;
    private String title;
    private String content;
    private Date createdDate;
    private Date modifiedDate;
    private Date deletedDate;
    private String deleteStatus;
    private int count;
    private int categoryNo;
    private DashboardMemberDTO member;
    private int reportCount;
    private String secretStatus;

}
