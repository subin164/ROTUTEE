package com.greedy.rotutee.lecture.lecture.entity;

import com.greedy.rotutee.lecture.lecture.dto.MemberDTO;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Lecture_LectureReview")
@Table(name = "TBL_LECTURE_REVIEW")
public class LectureReview {

    @Id
    @Column(name = "LECTURE_REVIEW_NO")
    private int lectureReviewNo;

    @Column(name = "LECTURE_GRADE")
    private int lectureGrade;

    @Column(name = "LECTURE_REVIEW_CONTENT")
    private String lectureReviewContent;

    @Column(name = "LECTURE_REVIEW_DATE")
    private Date lectureReviewDate;

    @Column(name = "LECTURE_REVIEW_REMOVE_YN")
    private String lectureReviewRemoveYN;

    @JoinColumn(name = "MEMBER_LECTURE_NO")
    @ManyToOne
    private Member writer;

}
