package com.greedy.rotutee.dashboard.mypage.entity;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.entity
 * fileName : DashboardMemberLecture
 * author : SeoYoung
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 SeoYoung 최초 생성
 */
@Entity(name = "Dashboard_MemberLecture")
@Table(name = "TBL_MEMBER_LECTURE")
@SequenceGenerator(
        name = "DASHBOARD_MEMBER_LECTURE_SEQ_GENERATOR",
        sequenceName = "MEMBER_LECTURE_NO",
        initialValue = 1,
        allocationSize = 1
)
public class DashboardMemberLecture {

    @Id
    @Column(name = "MEMBER_LECTURE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "DASHBOARD_MEMBER_LECTURE_SEQ_GENERATOR"
    )
    private int memberLectureNo;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private DashboardMember member;

    @Column(name = "LECTURE_NO")
    private int lectureNo;

    public DashboardMemberLecture() {}

    public DashboardMemberLecture(int memberLectureNo, DashboardMember member, int lectureNo) {
        this.memberLectureNo = memberLectureNo;
        this.member = member;
        this.lectureNo = lectureNo;
    }

    public int getMemberLectureNo() {
        return memberLectureNo;
    }

    public void setMemberLectureNo(int memberLectureNo) {
        this.memberLectureNo = memberLectureNo;
    }

    public DashboardMember getMember() {
        return member;
    }

    public void setMember(DashboardMember member) {
        this.member = member;
    }

    public int getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(int lectureNo) {
        this.lectureNo = lectureNo;
    }

    @Override
    public String toString() {
        return "DashboardMemberLecture{" +
                "memberLectureNo=" + memberLectureNo +
                ", member=" + member +
                ", lectureNo=" + lectureNo +
                '}';
    }
}
