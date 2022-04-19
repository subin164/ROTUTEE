package com.greedy.rotutee.dashboard.mypage.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.entity
 * fileName : DashboardLecture
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */

@Entity(name = "dashboardLecture")
@Table(name = "TBL_LECTURE")
@SequenceGenerator(
        name = "DASHBOARD_LECTURE_SEQ_GENERATOR",
        sequenceName = "BOARD_NO",
        initialValue = 1,
        allocationSize = 1
)
public class DashboardLecture {

    @Id
    @Column(name = "LECTURE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "DASHBOARD_LECTURE_SEQ_GENERATOR"
    )
    private int lectureNo;

    @Column(name = "LECTURE_NAME")
    private String lectureTitle;

    @Column(name = "LECTURE_PRICE")
    private int price;

    @Column(name = "LECTURE_LEVEL")
    private String level;

    @Column(name = "LECTURE_SUMMARY")
    private String summary;

    @Column(name = "LECTURE_DETAILS")
    private String details;

    @Column(name = "REVISION_HISTORY")
    private String revisionMessage;

    @Column(name = "LECTURE_APPROBAL_STATUS")
    private String approvalStatus;

    @Column(name = "LECTURE_OPENING_DATE")
    private Date openedDate;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Column(name = "APPLICATION_DATE")
    private Date appliedDate;

    @Column(name = "APPLICATION_DIVISION")
    private Date reappliedDate;

    @Column(name = "LECTURE_CATEGORY_NO")
    private int categoryNo;

    public DashboardLecture() {}

    public DashboardLecture(int lectureNo, String lectureTitle, int price, String level, String summary, String details, String revisionMessage, String approvalStatus, Date openedDate, int memberNo, Date appliedDate, Date reappliedDate, int categoryNo) {
        this.lectureNo = lectureNo;
        this.lectureTitle = lectureTitle;
        this.price = price;
        this.level = level;
        this.summary = summary;
        this.details = details;
        this.revisionMessage = revisionMessage;
        this.approvalStatus = approvalStatus;
        this.openedDate = openedDate;
        this.memberNo = memberNo;
        this.appliedDate = appliedDate;
        this.reappliedDate = reappliedDate;
        this.categoryNo = categoryNo;
    }

    public int getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(int lectureNo) {
        this.lectureNo = lectureNo;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public void setLectureTitle(String lectureTitle) {
        this.lectureTitle = lectureTitle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getRevisionMessage() {
        return revisionMessage;
    }

    public void setRevisionMessage(String revisionMessage) {
        this.revisionMessage = revisionMessage;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(Date openedDate) {
        this.openedDate = openedDate;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    public Date getReappliedDate() {
        return reappliedDate;
    }

    public void setReappliedDate(Date reappliedDate) {
        this.reappliedDate = reappliedDate;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    @Override
    public String toString() {
        return "DashboardLecture{" +
                "lectureNo=" + lectureNo +
                ", lectureTitle='" + lectureTitle + '\'' +
                ", price=" + price +
                ", level='" + level + '\'' +
                ", summary='" + summary + '\'' +
                ", details='" + details + '\'' +
                ", revisionMessage='" + revisionMessage + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", openedDate=" + openedDate +
                ", memberNo=" + memberNo +
                ", appliedDate=" + appliedDate +
                ", reappliedDate=" + reappliedDate +
                ", categoryNo=" + categoryNo +
                '}';
    }
}
