package com.greedy.rotutee.lecture.request.dto;

/**
 * packageName      : com.greedy.rotutee.lecture.request.dto
 * fileName         : ViewQuizDTO
 * author           : SEOK
 * date             : 2022-04-25
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-04-25      SEOK         최초 생성
 */
public class ViewQuizDTO {

    private int viewQuizNo;
    private int problemNumber;
    private String problemContent;
    private int QuizNo;

    public ViewQuizDTO() {
    }

    public ViewQuizDTO(int viewQuizNo, int problemNumber, String problemContent, int quizNo) {
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
        return "ViewQuizDTO{" +
                "viewQuizNo=" + viewQuizNo +
                ", problemNumber=" + problemNumber +
                ", problemContent='" + problemContent + '\'' +
                ", QuizNo=" + QuizNo +
                '}';
    }
}
