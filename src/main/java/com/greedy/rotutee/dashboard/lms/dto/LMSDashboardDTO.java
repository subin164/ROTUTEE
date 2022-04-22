package com.greedy.rotutee.dashboard.lms.dto;

import lombok.*;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.dto
 * fileName : LMSDashboardDTO
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
public class LMSDashboardDTO {

    List<LMSNoticeBoardDTO> noticeBoardList;
    List<LMSNormalBoardDTO> normalBoardList;


}
