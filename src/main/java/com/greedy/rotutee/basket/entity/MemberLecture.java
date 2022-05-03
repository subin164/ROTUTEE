package com.greedy.rotutee.basket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Basket_MemberLecture")
@Table(name = "TBL_MEMBER_LECTURE")
public class MemberLecture {

    @Id
    @Column(name = "MEMBER_LECTURE_NO")
    private int memberLectureNo;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Column(name = "LECTURE_NO")
    private int lectureNo;

    public MemberLecture() {
    }

    public MemberLecture(int memberLectureNo, int memberNo, int lectureNo) {
        this.memberLectureNo = memberLectureNo;
        this.memberNo = memberNo;
        this.lectureNo = lectureNo;
    }

    public int getMemberLectureNo() {
        return memberLectureNo;
    }

    public void setMemberLectureNo(int memberLectureNo) {
        this.memberLectureNo = memberLectureNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(int lectureNo) {
        this.lectureNo = lectureNo;
    }

    @Override
    public String toString() {
        return "MemberLecture{" +
                "memberLectureNo=" + memberLectureNo +
                ", memberNo=" + memberNo +
                ", lectureNo=" + lectureNo +
                '}';
    }
}
