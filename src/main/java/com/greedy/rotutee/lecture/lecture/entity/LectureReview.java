package com.greedy.rotutee.lecture.lecture.entity;

import com.greedy.rotutee.lecture.lecture.dto.MemberDTO;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Lecture_LectureReview")
@Table(name = "TBL_LECTURE_REVIEW")
@SequenceGenerator(
        name = "LECTURE_REVIEW_SEQ_GENERATOR",
        sequenceName = "LECTURE_REVIEW_NO",
        initialValue = 1,
        allocationSize = 1
)
public class LectureReview {

    @Id
    @Column(name = "LECTURE_REVIEW_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LECTURE_REVIEW_SEQ_GENERATOR"
    )
    private int lectureReviewNo;

    @Column(name = "LECTURE_GRADE")
    private int lectureGrade;

    @Column(name = "LECTURE_REVIEW_CONTENT")
    private String lectureReviewContent;

    @Column(name = "LECTURE_REVIEW_DATE")
    private Date lectureReviewDate;

    @Column(name = "LECTURE_REVIEW_REMOVE_YN")
    private String lectureReviewRemoveYN;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @Column(name = "LECTURE_NO")
    private int lectureNo;

    public LectureReview() {
    }

    public LectureReview(int lectureReviewNo, int lectureGrade, String lectureReviewContent, Date lectureReviewDate, String lectureReviewRemoveYN, Member writer, int lectureNo) {
        this.lectureReviewNo = lectureReviewNo;
        this.lectureGrade = lectureGrade;
        this.lectureReviewContent = lectureReviewContent;
        this.lectureReviewDate = lectureReviewDate;
        this.lectureReviewRemoveYN = lectureReviewRemoveYN;
        this.writer = writer;
        this.lectureNo = lectureNo;
    }

    public int getLectureReviewNo() {
        return lectureReviewNo;
    }

    public void setLectureReviewNo(int lectureReviewNo) {
        this.lectureReviewNo = lectureReviewNo;
    }

    public int getLectureGrade() {
        return lectureGrade;
    }

    public void setLectureGrade(int lectureGrade) {
        this.lectureGrade = lectureGrade;
    }

    public String getLectureReviewContent() {
        return lectureReviewContent;
    }

    public void setLectureReviewContent(String lectureReviewContent) {
        this.lectureReviewContent = lectureReviewContent;
    }

    public Date getLectureReviewDate() {
        return lectureReviewDate;
    }

    public void setLectureReviewDate(Date lectureReviewDate) {
        this.lectureReviewDate = lectureReviewDate;
    }

    public String getLectureReviewRemoveYN() {
        return lectureReviewRemoveYN;
    }

    public void setLectureReviewRemoveYN(String lectureReviewRemoveYN) {
        this.lectureReviewRemoveYN = lectureReviewRemoveYN;
    }

    public Member getWriter() {
        return writer;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
    }

    public int getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(int lectureNo) {
        this.lectureNo = lectureNo;
    }

    @Override
    public String toString() {
        return "LectureReview{" +
                "lectureReviewNo=" + lectureReviewNo +
                ", lectureGrade=" + lectureGrade +
                ", lectureReviewContent='" + lectureReviewContent + '\'' +
                ", lectureReviewDate=" + lectureReviewDate +
                ", lectureReviewRemoveYN='" + lectureReviewRemoveYN + '\'' +
                ", writer=" + writer +
                ", lectureNo=" + lectureNo +
                '}';
    }
}
