package com.greedy.rotutee.dashboard.lms.dto;

import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardLectureWatchDTO;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardMemberDTO;
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

    private List<ToDoDTO> todos;
    private List<LMSNoticeBoardDTO> noticeBoards;
    private List<LMSNormalBoardDTO> normalBoards;
    private List<LMSLatelyViewDTO> watching;
    private DashboardMemberDTO member;


}
