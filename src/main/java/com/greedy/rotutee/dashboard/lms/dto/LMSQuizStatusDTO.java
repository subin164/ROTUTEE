package com.greedy.rotutee.dashboard.lms.dto;

import lombok.*;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.dto
 * fileName : LMSQuizStatusDTO
 * author : SeoYoung
 * date : 2022-05-02
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-02 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LMSQuizStatusDTO {

    private int submissionCount;
    private int correctCount;
    private List<LMSChapterDTO> chapters;
    private int quizSize;
}
