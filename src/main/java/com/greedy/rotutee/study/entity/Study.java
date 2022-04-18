package com.greedy.rotutee.study.entity;

import com.greedy.rotutee.member.entity.Member;

import javax.persistence.*;
import java.sql.Date;

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
    @Column(name = "STUDY_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDY_SEQ_GENERATOR"
    )
    public int StudyNo;

    @Column(name="STUDY_CONTENT")
    public String content;

    @Column(name = "STUDY_TITLE")
    public String title;

    @Column(name = "STUDY_WRITE_DATE")
    public java.sql.Date writeDate;

    @Column(name = "STUDY_LIMITED_MEMBER_NUM")
    public int limited;

    @Column(name="STUDY_INVITE_LINK")
    public String linked;

    @Column(name = "MEMBER_NO")
    public int memberNo;

    @Column(name = "STUDY_RECRUITMENT_START_DATE")
    public java.sql.Date startDate;

    @Column(name = "STUDY_RECRUITMENT_END_DATE")
    public java.sql.Date endDate;

    @Column(name = "STUDY_RECRUITMENT_STATUS")
    public String status;

    @Column(name = "STUDY_TAG")
    public String tag;

    public Study() {
    }

    public Study(int studyNo, String content, String title, Date writeDate, int limited, String linked, int memberNo, Date startDate, Date endDate, String status, String tag) {
        StudyNo = studyNo;
        this.content = content;
        this.title = title;
        this.writeDate = writeDate;
        this.limited = limited;
        this.linked = linked;
        this.memberNo = memberNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.tag = tag;
    }

    public int getStudyNo() {
        return StudyNo;
    }

    public void setStudyNo(int studyNo) {
        StudyNo = studyNo;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Study{" +
                "StudyNo=" + StudyNo +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", writeDate=" + writeDate +
                ", limited=" + limited +
                ", linked='" + linked + '\'' +
                ", memberNo=" + memberNo +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
