package com.greedy.rotutee.main.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Main_LectureRequestProcessingHistory")
@Table(name = "TBL_LECTURE_REQUEST_PROCESSING_HISTORY")
@SequenceGenerator(
        name = "REQUEST_SEQ_LECTURE_REQUEST_HISTORY_NO",
        sequenceName = "LECTURE_REQUEST_HISTORY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class LectureRequestProcessingHistory {

    @Id
    @Column(name = "LECTURE_REQUEST_HISTORY_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "REQUEST_SEQ_LECTURE_REQUEST_HISTORY_NO"
    )
    private int lectureRequestHistoryNo;

    @Column(name = "PROCESSING_DATE")
    private Date processingDate;

    @Column(name = "PROCESSING_STATUS")
    private String processingStatus;

    @JoinColumn(name = "LECTURE_REJECTION_REASON_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private LectureRejectionReason reason;

    @JoinColumn(name = "LECTURE_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lecture lecture;

    public LectureRequestProcessingHistory() {}

    public LectureRequestProcessingHistory(int lectureRequestHistoryNo, Date processingDate, String processingStatus, LectureRejectionReason reason, Lecture lecture) {
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

    public LectureRejectionReason getReason() {
        return reason;
    }

    public void setReason(LectureRejectionReason reason) {
        this.reason = reason;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    @Override
    public String toString() {
        return "LectureRequestProcessingHistory{" +
                "lectureRequestHistoryNo=" + lectureRequestHistoryNo +
                ", processingDate=" + processingDate +
                ", processingStatus='" + processingStatus + '\'' +
                ", reason=" + reason +
                ", lecture=" + lecture +
                '}';
    }
}
