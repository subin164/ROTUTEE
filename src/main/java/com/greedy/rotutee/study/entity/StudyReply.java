package com.greedy.rotutee.study.entity;

import com.greedy.rotutee.member.member.entity.Member;

import javax.persistence.*;
import java.sql.Date;

/**
 * The type StudyReply.
 */
@Entity
@Table(name = "TBL_STUDY_BOARD_ANSWER")
@SequenceGenerator(
        name="STUDY_REPLY_GENERATOR",
        sequenceName = "STUDY_ANSWER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class StudyReply {

    /**
     * The Reply no.
     */
    @Id
    @Column(name = "STUDY_ANSWER_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDY_REPLY_GENERATOR"
    )
    private int replyNo;

    /**
     * The Reply content.
     */
    @Column(name="STUDY_ANSWER_CONTENT")
    private String replyContent;

    /**
     * The Reply status.
     */
    @Column(name="STUDY_ANSWER_YN")
    private String replyStatus;

    /**
     * The Reply report count.
     */
    @Column(name="STUDY_ANSWERS_REPORT_COUNT")
    private int replyReportCount;

    /**
     * The Reply write date.
     */
    @Column(name="STUDY_ANSWER_CREATE_DATE")
    private java.sql.Date replyWriteDate;

    /**
     * The Reply modify date.
     */
    @Column(name="STUDY_ANSWER_MODIFY_DATE")
    private java.sql.Date replyModifyDate;

    /**
     * The Reply remove date.
     */
    @Column(name="STUDY_ANSWER_DELETE_DATE")
    private java.sql.Date replyRemoveDate;

    /**
     * The Study no.
     */
    @Column(name ="STUDY_NO")
    private int studyNo;

    /**
     * The Writer.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_NO")
    private Member writer;

    /**
     * Instantiates a new Study reply.
     */
    public StudyReply() {
    }

    /**
     * Instantiates a new Study reply.
     *
     * @param replyNo          the reply no
     * @param replyContent     the reply content
     * @param replyStatus      the reply status
     * @param replyReportCount the reply report count
     * @param replyWriteDate   the reply write date
     * @param replyModifyDate  the reply modify date
     * @param replyRemoveDate  the reply remove date
     * @param studyNo          the study no
     * @param writer           the writer
     */
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

    /**
     * Gets reply no.
     * author : SeoYoung Kim
     * description :
     *
     * @return the reply no
     */
    public int getReplyNo() {
        return replyNo;
    }

    /**
     * Sets reply no.
     *
     * @param replyNo the reply no
     */
    public void setReplyNo(int replyNo) {
        this.replyNo = replyNo;
    }

    /**
     * Gets reply content.
     * author : SeoYoung Kim
     * description :
     *
     * @return the reply content
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * Sets reply content.
     *
     * @param replyContent the reply content
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /**
     * Gets reply status.
     * author : SeoYoung Kim
     * description :
     *
     * @return the reply status
     */
    public String getReplyStatus() {
        return replyStatus;
    }

    /**
     * Sets reply status.
     *
     * @param replyStatus the reply status
     */
    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }

    /**
     * Gets reply report count.
     * author : SeoYoung Kim
     * description :
     *
     * @return the reply report count
     */
    public int getReplyReportCount() {
        return replyReportCount;
    }

    /**
     * Sets reply report count.
     *
     * @param replyReportCount the reply report count
     */
    public void setReplyReportCount(int replyReportCount) {
        this.replyReportCount = replyReportCount;
    }

    /**
     * Gets reply write date.
     * author : SeoYoung Kim
     * description :
     *
     * @return the reply write date
     */
    public Date getReplyWriteDate() {
        return replyWriteDate;
    }

    /**
     * Sets reply write date.
     *
     * @param replyWriteDate the reply write date
     */
    public void setReplyWriteDate(Date replyWriteDate) {
        this.replyWriteDate = replyWriteDate;
    }

    /**
     * Gets reply modify date.
     * author : SeoYoung Kim
     * description :
     *
     * @return the reply modify date
     */
    public Date getReplyModifyDate() {
        return replyModifyDate;
    }

    /**
     * Sets reply modify date.
     *
     * @param replyModifyDate the reply modify date
     */
    public void setReplyModifyDate(Date replyModifyDate) {
        this.replyModifyDate = replyModifyDate;
    }

    /**
     * Gets reply remove date.
     * author : SeoYoung Kim
     * description :
     *
     * @return the reply remove date
     */
    public Date getReplyRemoveDate() {
        return replyRemoveDate;
    }

    /**
     * Sets reply remove date.
     *
     * @param replyRemoveDate the reply remove date
     */
    public void setReplyRemoveDate(Date replyRemoveDate) {
        this.replyRemoveDate = replyRemoveDate;
    }

    /**
     * Gets study no.
     * author : SeoYoung Kim
     * description :
     *
     * @return the study no
     */
    public int getStudyNo() {
        return studyNo;
    }

    /**
     * Sets study no.
     *
     * @param studyNo the study no
     */
    public void setStudyNo(int studyNo) {
        this.studyNo = studyNo;
    }

    /**
     * Gets writer.
     * author : SeoYoung Kim
     * description :
     *
     * @return the writer
     */
    public Member getWriter() {
        return writer;
    }

    /**
     * Sets writer.
     *
     * @param writer the writer
     */
    public void setWriter(Member writer) {
        this.writer = writer;
    }

    /**
     * methodName : toString
     * author : SeoYoung Kim
     * description :
     *
     * @return string
     */
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
