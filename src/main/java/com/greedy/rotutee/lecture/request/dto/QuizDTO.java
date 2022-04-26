package com.greedy.rotutee.lecture.request.dto;

import com.greedy.rotutee.lecture.request.entity.Class;

import java.util.List;

/**
 * packageName      : com.greedy.rotutee.lecture.request.dto
 * fileName         : QuizDTO
 * author           : SEOK
 * date             : 2022-04-25
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-04-25      SEOK         최초 생성
 */
public class QuizDTO {

    private int quizNo;
    private String quizProblem;
    private String quizAnswer;
    private String quizType;
    private String quizAnswerExplain;
    private String quizOption1;
    private String quizOption2;
    private String quizOption3;
    private String quizOption4;
    private ClassDTO classEntity;


    public QuizDTO() {
    }

    public QuizDTO(int quizNo, String quizProblem, String quizAnswer, String quizType, String quizAnswerExplain, String quizOption1, String quizOption2, String quizOption3, String quizOption4, ClassDTO classEntity) {
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

    public ClassDTO getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassDTO classEntity) {
        this.classEntity = classEntity;
    }

    @Override
    public String toString() {
        return "QuizDTO{" +
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
