package com.greedy.rotutee.basket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * packageName      : com.greedy.rotutee.lecture.request.entity
 * fileName         : ViewQuiz
 * author           : SEOK
 * date             : 2022-04-25
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-04-25      SEOK         최초 생성
 */
@Entity(name = "Basket_ViewQuiz")
@Table(name = "TBL_VIEW_QUIZ")
public class ViewQuiz {

    @Id
    @Column(name = "VIEW_QUIZ_NO")
    private int viewQuizNo;

    @Column(name = "PROBLEM_NUMBER")
    private int problemNumber;

    @Column(name = "PROBLEM_CONTENT")
    private String problemContent;

    @Column(name = "QUIZ_NO")
    private int QuizNo;

    public ViewQuiz() {
    }

    public ViewQuiz(int viewQuizNo, int problemNumber, String problemContent, int quizNo) {
        this.viewQuizNo = viewQuizNo;
        this.problemNumber = problemNumber;
        this.problemContent = problemContent;
        QuizNo = quizNo;
    }

    public int getViewQuizNo() {
        return viewQuizNo;
    }

    public void setViewQuizNo(int viewQuizNo) {
        this.viewQuizNo = viewQuizNo;
    }

    public int getProblemNumber() {
        return problemNumber;
    }

    public void setProblemNumber(int problemNumber) {
        this.problemNumber = problemNumber;
    }

    public String getProblemContent() {
        return problemContent;
    }

    public void setProblemContent(String problemContent) {
        this.problemContent = problemContent;
    }

    public int getQuizNo() {
        return QuizNo;
    }

    public void setQuizNo(int quizNo) {
        QuizNo = quizNo;
    }

    @Override
    public String toString() {
        return "ViewQuiz{" +
                "viewQuizNo=" + viewQuizNo +
                ", problemNumber=" + problemNumber +
                ", problemContent='" + problemContent + '\'' +
                ", QuizNo=" + QuizNo +
                '}';
    }
}
