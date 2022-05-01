package com.greedy.rotutee.dashboard.lms.dto;

import com.greedy.rotutee.dashboard.lms.entity.LMSChapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.dto
 * fileName : LecturePlayDTO
 * author : SeoYoung
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LecturePlayDTO {

    private String lectureSummary;
    private List<LMSChapterDTO> chapters;

}
