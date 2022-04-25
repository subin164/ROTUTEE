package com.greedy.rotutee.lecture.request.dto;

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
    private int classNo;

    public QuizDTO() {
    }

    public QuizDTO(int quizNo, String quizProblem, String quizAnswer, String quizType, int classNo) {
        this.quizNo = quizNo;
        this.quizProblem = quizProblem;
        this.quizAnswer = quizAnswer;
        this.quizType = quizType;
        this.classNo = classNo;
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

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    @Override
    public String toString() {
        return "QuizDTO{" +
                "quizNo=" + quizNo +
                ", quizProblem='" + quizProblem + '\'' +
                ", quizAnswer='" + quizAnswer + '\'' +
                ", quizType='" + quizType + '\'' +
                ", classNo=" + classNo +
                '}';
    }
}
