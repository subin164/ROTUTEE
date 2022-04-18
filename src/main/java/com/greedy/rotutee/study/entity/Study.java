package com.greedy.rotutee.study.entity;

import com.greedy.rotutee.member.entity.Member;

import javax.persistence.*;

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

    @Column(name = "STUDY_TITLE")
    public int title;

    @Column(name = "STUDY_WRITE_DATE")
    public int writeDate;

    @Column(name = "STUDY_LIMITED_MEMBER_NUM")
    public int limited;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    public Member memberNo;

    @Column(name = "STUDY_RECRUITMENT_START_DATE")
    public int startDate;

    @Column(name = "STUDY_RECRUITMENT_END_DATE")
    public int endDate;

    @Column(name = "STUDY_RECRUITMENT_STATUS")
    public String status;

    @Column(name = "STUDY_TAG")
    public String tag;

    public Study() {
    }

    public Study(int studyNo, int title, int writeDate, int limited, Member memberNo, int startDate, int endDate, String status, String tag) {
        StudyNo = studyNo;
        this.title = title;
        this.writeDate = writeDate;
        this.limited = limited;
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

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(int writeDate) {
        this.writeDate = writeDate;
    }

    public int getLimited() {
        return limited;
    }

    public void setLimited(int limited) {
        this.limited = limited;
    }

    public Member getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Member memberNo) {
        this.memberNo = memberNo;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
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
        return "StudyEntity{" +
                "StudyNo=" + StudyNo +
                ", title=" + title +
                ", writeDate=" + writeDate +
                ", limited=" + limited +
                ", memberNo=" + memberNo +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
