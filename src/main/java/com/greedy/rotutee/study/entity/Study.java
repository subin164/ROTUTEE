package com.greedy.rotutee.study.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Study")
@Table(name = "TBL_STUDY_GROUP_BOARD")
@SequenceGenerator(
        name = "STUDY_SEQ_GENERATOR",
        sequenceName = "STUDY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Study {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDY_SEQ_GENERATOR"
    )
    @Column(name = "STUDY_NO")
    public int studyNo;

    @Column(name = "STUDY_CONTENT")
    public String content;

    @Column(name = "STUDY_TITLE")
    public String title;

    @Column(name = "STUDY_WRITE_DATE")
    public java.sql.Date writeDate;

    @Column(name = "STUDY_LIMITED_MEMBER_NUM")
    public int limited;

    @Column(name = "STUDY_INVITE_LINK")
    public String linked;

    @Column(name = "MEMBER_NO")
    public int memberNo;

    @Column(name = "STUDY_RECRUITMENT_END_DATE")
    public java.sql.Date endDate;

    @Column(name = "STUDY_RECRUITMENT_STATUS")
    public String status;

    @Column(name = "STUDY_TAG")
    private int tagNo;

    public Study() {
    }

    public Study(int studyNo, String content, String title, Date writeDate, int limited, String linked, int memberNo, Date endDate, String status, int tagNo) {
        this.studyNo = studyNo;
        this.content = content;
        this.title = title;
        this.writeDate = writeDate;
        this.limited = limited;
        this.linked = linked;
        this.memberNo = memberNo;
        this.endDate = endDate;
        this.status = status;
        this.tagNo = tagNo;
    }

    public int getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(int studyNo) {
        this.studyNo = studyNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public int getLimited() {
        return limited;
    }

    public void setLimited(int limited) {
        this.limited = limited;
    }

    public String getLinked() {
        return linked;
    }

    public void setLinked(String linked) {
        this.linked = linked;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", writeDate=" + writeDate +
                ", limited=" + limited +
                ", linked='" + linked + '\'' +
                ", memberNo=" + memberNo +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", tagNo=" + tagNo +
                '}';
    }
}