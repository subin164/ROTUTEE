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
@Entity(name = "Mypage_MemberLecture")
@Table(name = "TBL_MEMBER_LECTURE")
@SequenceGenerator(
        name = "DASHBOARD_MEMBER_LECTURE_SEQ_GENERATOR",
        sequenceName = "MEMBER_LECTURE_NO",
        initialValue = 1,
        allocationSize = 1
)
public class MyPageMemberLecture {

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

    @ManyToOne
    @JoinColumn(name = "LECTURE_NO")
    private DashboardLecture lecture;

    public MyPageMemberLecture() {}

    public MyPageMemberLecture(int memberLectureNo, DashboardMember member, DashboardLecture lecture) {
        this.memberLectureNo = memberLectureNo;
        this.member = member;
        this.lecture = lecture;
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

    public DashboardLecture getLecture() {
        return lecture;
    }

    public void setLecture(DashboardLecture lecture) {
        this.lecture = lecture;
    }

    @Override
    public String toString() {
        return "DashboardMemberLecture{" +
                "memberLectureNo=" + memberLectureNo +
                ", member=" + member +
                ", lecture=" + lecture +
                '}';
    }
}
