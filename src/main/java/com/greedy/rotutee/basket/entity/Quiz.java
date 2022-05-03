package com.greedy.rotutee.basket.entity;

import javax.persistence.*;

/**
 * packageName      : com.greedy.rotutee.lecture.request.entity
 * fileName         : Quiz
 * author           : SEOK
 * date             : 2022-04-25
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-04-25      SEOK         최초 생성
 */
@Entity(name = "Basket_Quiz")
@Table(name = "TBL_QUIZ")
public class Quiz {

    @Id
    @Column(name = "QUIZ_NO")
    private int quizNo;

    @Column(name = "QUIZ_PROBLEM")
    private String quizProblem;

    @Column(name = "QUIZ_ANSWER")
    private String quizAnswer;

    @Column(name = "QUIZ_TYPE")
    private String quizType;

    @Column(name = "QUIZ_ANSWER_EXPLAIN")
    private String quizAnswerExplain;

    @Column(name = "QUIZ_OPTION_1")
    private String quizOption1;

    @Column(name = "QUIZ_OPTION_2")
    private String quizOption2;

    @Column(name = "QUIZ_OPTION_3")
    private String quizOption3;

    @Column(name = "QUIZ_OPTION_4")
    private String quizOption4;

    @JoinColumn(name = "CLASS_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Class classEntity;

    public Quiz() {
    }

    public Quiz(int quizNo, String quizProblem, String quizAnswer, String quizType, String quizAnswerExplain, String quizOption1, String quizOption2, String quizOption3, String quizOption4, Class classEntity) {
        this.quizNo = quizNo;
        this.quizProblem = quizProblem;
        this.quizAnswer = quizAnswer;
        this.quizType = quizType;
        this.quizAnswerExplain = quizAnswerExplain;
        this.quizOption1 = quizOption1;
        this.quizOption2 = quizOption2;
        this.quizOption3 = quizOption3;
        this.quizOption4 = quizOption4;
        this.classEntity = classEntity;
    }

    public int getQuizNo() {
        return quizNo;
    }

    public void setQuizNo(int quizNo) {
        this.quizNo = quizNo;
    }

    public String getQuizProblem() {
        return quizProblem;
    }

    public void setQuizProblem(String quizProblem) {
        this.quizProblem = quizProblem;
    }

    public String getQuizAnswer() {
        return quizAnswer;
    }

    public void setQuizAnswer(String quizAnswer) {
        this.quizAnswer = quizAnswer;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public String getQuizAnswerExplain() {
        return quizAnswerExplain;
    }

    public void setQuizAnswerExplain(String quizAnswerExplain) {
        this.quizAnswerExplain = quizAnswerExplain;
    }

    public String getQuizOption1() {
        return quizOption1;
    }

    public void setQuizOption1(String quizOption1) {
        this.quizOption1 = quizOption1;
    }

    public String getQuizOption2() {
        return quizOption2;
    }

    public void setQuizOption2(String quizOption2) {
        this.quizOption2 = quizOption2;
    }

    public String getQuizOption3() {
        return quizOption3;
    }

    public void setQuizOption3(String quizOption3) {
        this.quizOption3 = quizOption3;
    }

    public String getQuizOption4() {
        return quizOption4;
    }

    public void setQuizOption4(String quizOption4) {
        this.quizOption4 = quizOption4;
    }

    public Class getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(Class classEntity) {
        this.classEntity = classEntity;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizNo=" + quizNo +
                ", quizProblem='" + quizProblem + '\'' +
                ", quizAnswer='" + quizAnswer + '\'' +
                ", quizType='" + quizType + '\'' +
                ", quizAnswerExplain='" + quizAnswerExplain + '\'' +
                ", quizOption1='" + quizOption1 + '\'' +
                ", quizOption2='" + quizOption2 + '\'' +
                ", quizOption3='" + quizOption3 + '\'' +
                ", quizOption4='" + quizOption4 + '\'' +
                ", classEntity=" + classEntity +
                '}';
    }
}
