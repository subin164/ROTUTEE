package com.greedy.rotutee.lecture.mylecture.entity;


import com.greedy.rotutee.dashboard.mypage.entity.DashboardMember;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.lecture.mylecture.entity
 * fileName : MyLecture
 * author : SeoYoung
 * date : 2022-05-10
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-10 SeoYoung 최초 생성
 */
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "My_Lecture")
@Table(name = "TBL_LECTURE")
@SequenceGenerator(
        name = "MY_LECTURE_SEQ_GENERATOR",
        sequenceName = "LECTURE_NO",
        initialValue = 1,
        allocationSize = 1
)
public class MyLecture {

    @Id
    @Column(name = "LECTURE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MY_LECTURE_SEQ_GENERATOR"
    )
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

    @Column(name = "LECTURE_APPROVAL_STATUS")
    private String lectureApprovalStatus;

    @Column(name = "LECTURE_OPENING_DATE")
    private Date lectureOpeningDate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private DashboardMember member;

    @Column(name = "APPLICATION_DATE")
    private Date applicationDate;

    @Column(name = "APPLICATION_DIVISION")
    private String applicationDivision;

    @Column(name = "LECTURE_CATEGORY_NO")
    private int lectureCategoryNo;

    @Override
    public String toString() {
        return "MyLecture{" +
                "lectureNo=" + lectureNo +
                ", lectureName='" + lectureName + '\'' +
                ", lecturePrice=" + lecturePrice +
                ", lectureLevel='" + lectureLevel + '\'' +
                ", lectureSummary='" + lectureSummary + '\'' +
                ", lecturedetails='" + lecturedetails + '\'' +
                ", revisionHistory='" + revisionHistory + '\'' +
                ", lectureApprovalStatus='" + lectureApprovalStatus + '\'' +
                ", lectureOpeningDate=" + lectureOpeningDate +
                ", member=" + member +
                ", applicationDate=" + applicationDate +
                ", applicationDivision='" + applicationDivision + '\'' +
                ", lectureCategoryNo=" + lectureCategoryNo +
                '}';
    }
}
