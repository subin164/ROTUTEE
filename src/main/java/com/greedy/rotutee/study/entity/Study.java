package com.greedy.rotutee.study.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "study")
@Table(name = "TBL_STUDY_GROUP_BOARD")
@SequenceGenerator(
        name="STUDY_NO_GENERATOR",
        sequenceName = "STUDY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Study {

    @Id
    @Column(name = "STUDY_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDY_NO_GENERATOR"
    )
    private int studyNo;

    @Column(name = "STUDY_TITLE")
    private String title;

    @Column(name = "STUDY_CONTENT")
    private String content;

    @Column(name = "STUDY_WRITE_DATE")
    private java.sql.Date startDate;

    @Column(name = "STUDY_RECRUITMENT_END_DATE")
    private java.sql.Date endDate;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Column(name="WRITER_MEMBER_NO")
    private String writer;

    @Column(name = "STUDY_LIMITED_MEMBER_NUM")
    private int limited;

    @Column(name="STUDY_BOARD_VIEW_COUNT")
    private int count;

    @Column(name = "STUDY_INVITE_LINK")
    private String linked;

    @Column(name = "STUDY_RECRUITMENT_STATUS")
    private String status;

    @Column(name = "STUDY_TAG_NO")
    private int tagNo;

    public Study() {
    }

    public Study(int studyNo, String title, String content, Date startDate, Date endDate, int memberNo, String writer, int limited, int count, String linked, String status, int tagNo) {
        this.studyNo = studyNo;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberNo = memberNo;
        this.writer = writer;
        this.limited = limited;
        this.count = count;
        this.linked = linked;
        this.status = status;
        this.tagNo = tagNo;
    }

    public int getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(int studyNo) {
        this.studyNo = studyNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getLimited() {
        return limited;
    }

    public void setLimited(int limited) {
        this.limited = limited;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLinked() {
        return linked;
    }

    public void setLinked(String linked) {
        this.linked = linked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTagNo() {
        return tagNo;
    }

    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }

    @Override
    public String toString() {
        return "Study{" +
                "studyNo=" + studyNo +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", memberNo=" + memberNo +
                ", writer='" + writer + '\'' +
                ", limited=" + limited +
                ", count=" + count +
                ", linked='" + linked + '\'' +
                ", status='" + status + '\'' +
                ", tagNo=" + tagNo +
                '}';
    }
}