package com.greedy.rotutee.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Main_LectureRejectionReason")
@Table(name = "TBL_LECTURE_REJECTION_REASON")
public class LectureRejectionReason {

    @Id
    @Column(name = "LECTURE_REJECTION_REASON_NO")
    private int lectureRejectionReasonNo;

    @Column(name = "LECTURE_REJECTION_CONTENT_REASON")
    private String lectureRejectionContentReason;

    public LectureRejectionReason() {
    }

    public LectureRejectionReason(int lectureRejectionReasonNo, String lectureRejectionContentReason) {
        this.lectureRejectionReasonNo = lectureRejectionReasonNo;
        this.lectureRejectionContentReason = lectureRejectionContentReason;
    }

    public int getLectureRejectionReasonNo() {
        return lectureRejectionReasonNo;
    }

    public void setLectureRejectionReasonNo(int lectureRejectionReasonNo) {
        this.lectureRejectionReasonNo = lectureRejectionReasonNo;
    }

    public String getLectureRejectionContentReason() {
        return lectureRejectionContentReason;
    }

    public void setLectureRejectionContentReason(String lectureRejectionContentReason) {
        this.lectureRejectionContentReason = lectureRejectionContentReason;
    }

    @Override
    public String toString() {
        return "LectureRejectionReason{" +
                "lectureRejectionReasonNo=" + lectureRejectionReasonNo +
                ", lectureRejectionContentReason='" + lectureRejectionContentReason + '\'' +
                '}';
    }
}
