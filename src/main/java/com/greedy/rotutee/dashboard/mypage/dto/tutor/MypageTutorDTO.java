package com.greedy.rotutee.dashboard.mypage.dto.tutor;

import com.greedy.rotutee.dashboard.mypage.dto.tutee.*;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardBoard;
import lombok.*;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.dto.tutor
 * fileName : MypageTutorDTO
 * author : SeoYoung
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-22 SeoYoung 최초 생성
 */
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MypageTutorDTO {

 private DashboardMemberDTO member;
 private List<DashboardBoardDTO> boardList;
 private List<DashboardLectureDTO> lectureList;
 private List<DashboardNoticeDTO> noticeList;

}
