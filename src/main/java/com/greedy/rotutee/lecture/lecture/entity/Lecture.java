package com.greedy.rotutee.lecture.lecture.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Lecture_Lecture")
@Table(name = "TBL_LECTURE")
public class Lecture {

    @Id
    @Column(name = "LECTURE_NO")
    private int lectureNo;

    @Column(name = "LECTURE_NAME")
    private String lectureName;

    @Column(name = "LECTURE_PRICE")
    private int lecturePrice;

    @Column(name = "LECTURE_LEVEL")
    private String lectureLevel;

    @Column(name = "LECTURE_SUMMARY")
    private String lectureSummary;

    @Column(name = "LECTURE_DETAILS")
    private String lecturedetails;

    @Column(name = "REVISION_HISTORY")
    private String revisionHistory;

    @Column(name = "LECTURE_APPROBAL_STATUS")
    private String lectureApprovalStatus;

    @Column(name = "LECTURE_OPENING_DATE")
    private Date lectureOpeningDate;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne
    private Member tutor;

    @Column(name = "APPLICATION_DATE")
    private Date applicationDate;

    @Column(name = "APPLICATION_DIVISION")
    private String applicationDivision;

    @JoinColumn(name = "LECTURE_CATEGORY_NO")
    @ManyToOne
    private LectureCategory lectureCategory;
}
