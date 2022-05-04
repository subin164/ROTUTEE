package com.greedy.rotutee.dashboard.lms.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.entity
 * fileName : LMSSubmissonQuiz
 * author : SeoYoung
 * date : 2022-04-30
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-30 SeoYoung 최초 생성
 */

@Entity(name = "Lms_SubmissionQuiz")
@Table(name = "TBL_SUBMISSION_QUIZ")
@SequenceGenerator(
        name = "LMS_SUBMISSION_QUIZ_SEQ_GENERATOR",
        sequenceName = "SUBMISSION_QUIZ_NO",
        initialValue = 1,
        allocationSize = 1
)
public class LMSSubmissionQuiz {

    @Id
    @Column(name = "SUBMISSION_QUIZ_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LMS_SUBMISSION_QUIZ_SEQ_GENERATOR"
    )
    private int submissionQuizNo;

    @Column(name = "SUBMIT_QUIZ_ANSWER_YN")
    private String answerStatus;

    @Column(name = "QUIZ_SUBMISSION_DATE")
    private Date submitDate;

    @Column(name = "MEMBER_LECTURE_NO")
    private int memberLectureNo;

    @Column(name = "QUIZ_NO")
    private int quizNo;

    public LMSSubmissionQuiz() {}


    public LMSSubmissionQuiz(int submissionQuizNo, String answerStatus, Date submitDate, int memberLectureNo, int quizNo) {
        this.submissionQuizNo = submissionQuizNo;
        this.answerStatus = answerStatus;
        this.submitDate = submitDate;
        this.memberLectureNo = memberLectureNo;
        this.quizNo = quizNo;
    }
    @PrePersist
    public void prePersist() {
        this.submitDate = this.submitDate == null? new Date(System.currentTimeMillis()) : this.submitDate;
    }

    public int getSubmissionQuizNo() {
        return submissionQuizNo;
    }

    public void setSubmissionQuizNo(int submissionQuizNo) {
        this.submissionQuizNo = submissionQuizNo;
    }

    public String getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(String answerStatus) {
        this.answerStatus = answerStatus;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public int getMemberLectureNo() {
        return memberLectureNo;
    }

    public void setMemberLectureNo(int memberLectureNo) {
        this.memberLectureNo = memberLectureNo;
    }

    public int getQuizNo() {
        return quizNo;
    }

    public void setQuizNo(int quizNo) {
        this.quizNo = quizNo;
    }

    @Override
    public String toString() {
        return "LMSSubmissonQuiz{" +
                "submissionQuizNo=" + submissionQuizNo +
                ", answerStatus='" + answerStatus + '\'' +
                ", submitDate=" + submitDate +
                ", memberLectureNo=" + memberLectureNo +
                ", quizNo=" + quizNo +
                '}';
    }
}
