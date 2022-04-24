package com.greedy.rotutee.lecture.request.dto;

import com.greedy.rotutee.lecture.request.entity.Lecture;

import java.sql.Date;

public class LectureRequestProcessingHistoryDTO {

    private int lectureRequestHistoryNo;
    private Date processingDate;
    private String processingStatus;
    private LectureRejectionReasonDTO reason;
    private LectureDTO lecture;

    public LectureRequestProcessingHistoryDTO() {
    }

    public LectureRequestProcessingHistoryDTO(int lectureRequestHistoryNo, Date processingDate, String processingStatus, LectureRejectionReasonDTO reason, LectureDTO lecture) {
        this.lectureRequestHistoryNo = lectureRequestHistoryNo;
        this.processingDate = processingDate;
        this.processingStatus = processingStatus;
        this.reason = reason;
        this.lecture = lecture;
    }

    public int getLectureRequestHistoryNo() {
        return lectureRequestHistoryNo;
    }

    public void setLectureRequestHistoryNo(int lectureRequestHistoryNo) {
        this.lectureRequestHistoryNo = lectureRequestHistoryNo;
    }

    public Date getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    public String getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(String processingStatus) {
        this.processingStatus = processingStatus;
    }

    public LectureRejectionReasonDTO getReason() {
        return reason;
    }

    public void setReason(LectureRejectionReasonDTO reason) {
        this.reason = reason;
    }

    public LectureDTO getLecture() {
        return lecture;
    }

    public void setLecture(LectureDTO lecture) {
        this.lecture = lecture;
    }

    @Override
    public String toString() {
        return "LectureRequestProcessingHistoryDTO{" +
                "lectureRequestHistoryNo=" + lectureRequestHistoryNo +
                ", processingDate=" + processingDate +
                ", processingStatus='" + processingStatus + '\'' +
                ", reason=" + reason +
                ", lecture=" + lecture +
                '}';
    }
}
