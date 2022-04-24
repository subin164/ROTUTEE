package com.greedy.rotutee.lecture.request.dto;

public class LectureRejectionReasonDTO {

    private int lectureRejectionReasonNo;
    private String lectureRejectionContentReason;

    public LectureRejectionReasonDTO() {
    }

    public LectureRejectionReasonDTO(int lectureRejectionReasonNo, String lectureRejectionContentReason) {
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
        return "LectureRejectionReasonDTO{" +
                "lectureRejectionReasonNo=" + lectureRejectionReasonNo +
                ", lectureRejectionContentReason='" + lectureRejectionContentReason + '\'' +
                '}';
    }
}
