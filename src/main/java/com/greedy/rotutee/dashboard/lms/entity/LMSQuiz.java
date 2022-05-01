package com.greedy.rotutee.dashboard.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.entity
 * fileName : LMSQuiz
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
@Entity(name = "Lms_Quiz")
@Table(name = "TBL_QUIZ")
@SequenceGenerator(
        name = "LMS_QUIZ_SEQ_GENERATOR",
        sequenceName = "QUIZ_NO",
        initialValue = 1,
        allocationSize = 1
)
public class LMSQuiz {

    @Id
    @Column(name = "QUIZ_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LMS_QUIZ_SEQ_GENERATOR"
    )
    private int quizNo;

    @Column(name = "QUIZ_PROBLEM")
    private String content;

    @Column(name = "QUIZ_ANSWER")
    private int answer;

    @Column(name = "QUIZ_TYPE")
    private String type;

    @Column(name = "CLASS_NO")
    private int classNo;

    @Column(name = "QUIZ_ANSWER_EXPLAIN")
    private String solution;

    @Column(name = "QUIZ_OPTION_1")
    private String option1;

    @Column(name = "QUIZ_OPTION_2")
    private String option2;

    @Column(name = "QUIZ_OPTION_3")
    private String option3;

    @Column(name = "QUIZ_OPTION_4")
    private String option4;

    @Override
    public String toString() {
        return "LMSQuiz{" +
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
                '}';
    }
}
