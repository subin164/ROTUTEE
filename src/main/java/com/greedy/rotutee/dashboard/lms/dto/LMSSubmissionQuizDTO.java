package com.greedy.rotutee.dashboard.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.dto
 * fileName : LMSSubmissionQuizDTO
 * author : SeoYoung
 * date : 2022-04-30
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-30 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LMSSubmissionQuizDTO {

    private int submissionQuizNo;
    private String answerStatus;
    private Date submitDate;
    private int memberLectureNo;
    private int quizNo;

    @Override
    public String toString() {
        return "LMSSubmissionQuizDTO{" +
                "submissionQuizNo=" + submissionQuizNo +
                ", answerStatus='" + answerStatus + '\'' +
                ", submitDate=" + submitDate +
                ", memberLectureNo=" + memberLectureNo +
                ", quizNo=" + quizNo +
                '}';
    }
}
