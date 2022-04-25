package com.greedy.rotutee.lecture.request.entity;

import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity(name = "Request_Quiz")
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

    @Column(name = "CLASS_NO")
    private int classNo;

    public Quiz() {
    }

    public Quiz(int quizNo, String quizProblem, String quizAnswer, String quizType, int classNo) {
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
}
