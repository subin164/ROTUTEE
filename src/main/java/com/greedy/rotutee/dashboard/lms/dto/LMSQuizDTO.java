package com.greedy.rotutee.dashboard.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.dto
 * fileName : LMSQuizDTO
 * author : SeoYoung
 * date : 2022-04-28
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-28 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LMSQuizDTO {

    private int quizNo;
    private String content;
    private int answer;
    private String type;
    private int classNo;
    private String solution;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String submissionStatus;        //제출을 했는지 안했는지 여부
    private String correctStatus;

    @Override
    public String toString() {
        return "LMSQuizDTO{" +
                "quizNo=" + quizNo +
                ", content='" + content + '\'' +
                ", answer=" + answer +
                ", type='" + type + '\'' +
                ", classNo=" + classNo +
                ", solution='" + solution + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", submissionStatus='" + submissionStatus + '\'' +
                ", correctStatus='" + correctStatus + '\'' +
                '}';
    }
}
