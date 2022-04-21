package com.greedy.rotutee.lecture.lecture.dto;

public class MemberLectureDTO {

    private int memberLectureNo;
    private int memberNo;
    private int lectureNo;

    public MemberLectureDTO() {
    }

    public MemberLectureDTO(int memberLectureNo, int memberNo, int lectureNo) {
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
        return "MemberLectureDTO{" +
                "memberLectureNo=" + memberLectureNo +
                ", memberNo=" + memberNo +
                ", lectureNo=" + lectureNo +
                '}';
    }
}
