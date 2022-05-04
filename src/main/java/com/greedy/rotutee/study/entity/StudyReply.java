package com.greedy.rotutee.study.entity;

import com.greedy.rotutee.member.member.entity.Member;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_STUDY_BOARD_ANSWER")
@SequenceGenerator(
        name="STUDY_REPLY_GENERATOR",
        sequenceName = "STUDY_ANSWER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class StudyReply {

    @Id
    @Column(name = "STUDY_ANSWER_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDY_REPLY_GENERATOR"
    )
    private int replyNo;

    @Column(name="STUDY_ANSWER_CONTENT")
    private String replyContent;

    @Column(name="STUDY_ANSWER_YN")
    private String replyStatus;

    @Column(name="STUDY_ANSWERS_REPORT_COUNT")
    private int replyReportCount;

    @Column(name="STUDY_ANSWER_CREATE_DATE")
    private java.sql.Date replyWriteDate;

    @Column(name="STUDY_ANSWER_MODIFY_DATE")
    private java.sql.Date replyModifyDate;

    @Column(name="STUDY_ANSWER_DELETE_DATE")
    private java.sql.Date replyRemoveDate;

    @Column(name ="STUDY_NO")
    private int studyNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_NO")
    private Member writer;

    public StudyReply() {
    }

    public StudyReply(int replyNo, String replyContent, String replyStatus, int replyReportCount, Date replyWriteDate, Date replyModifyDate, Date replyRemoveDate, int studyNo, Member writer) {
        this.replyNo = replyNo;
        this.replyContent = replyContent;
        this.replyStatus = replyStatus;
        this.replyReportCount = replyReportCount;
        this.replyWriteDate = replyWriteDate;
        this.replyModifyDate = replyModifyDate;
        this.replyRemoveDate = replyRemoveDate;
        this.studyNo = studyNo;
        this.writer = writer;
    }

    public int getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(int replyNo) {
        this.replyNo = replyNo;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }

    public int getReplyReportCount() {
        return replyReportCount;
    }

    public void setReplyReportCount(int replyReportCount) {
        this.replyReportCount = replyReportCount;
    }

    public Date getReplyWriteDate() {
        return replyWriteDate;
    }

    public void setReplyWriteDate(Date replyWriteDate) {
        this.replyWriteDate = replyWriteDate;
    }

    public Date getReplyModifyDate() {
        return replyModifyDate;
    }

    public void setReplyModifyDate(Date replyModifyDate) {
        this.replyModifyDate = replyModifyDate;
    }

    public Date getReplyRemoveDate() {
        return replyRemoveDate;
    }

    public void setReplyRemoveDate(Date replyRemoveDate) {
        this.replyRemoveDate = replyRemoveDate;
    }

    public int getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(int studyNo) {
        this.studyNo = studyNo;
    }

    public Member getWriter() {
        return writer;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "StudyReply{" +
                "replyNo=" + replyNo +
                ", replyContent='" + replyContent + '\'' +
                ", replyStatus='" + replyStatus + '\'' +
                ", replyReportCount=" + replyReportCount +
                ", replyWriteDate=" + replyWriteDate +
                ", replyModifyDate=" + replyModifyDate +
                ", replyRemoveDate=" + replyRemoveDate +
                ", studyNo=" + studyNo +
                ", writer=" + writer +
                '}';
    }
}
