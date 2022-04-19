package com.greedy.rotutee.dashboard.mypage.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.board.entity
 * fileName : DashboardBoard
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */
@Entity(name = "dashboardBoard")
@Table(name = "TBL_BOARD")
@SequenceGenerator(
        name = "DASHBOARD_BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_NO",
        initialValue = 1,
        allocationSize = 1
)
public class DashboardBoard {

    @Id
    @Column(name = "BOARD_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "DASHBOARD_BOARD_SEQ_GENERATOR"
    )
    private int boardNo;

    @Column(name = "BOARD_TITLE")
    private String title;

    @Column(name = "BOARD_CONTENT")
    private String content;

    @Column(name = "BOARD_CREATION_DATE")
    private Date createdDate;

    @Column(name = "BOARD_MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "BOARD_DELETION_DATE")
    private Date deletedDate;

    @Column(name = "BOARD_DELETE_YN")
    private String deleteStatus;

    @Column(name = "BOARD_VIEW_COUNT")
    private int count;

    @Column(name = "BOARD_CATEGORY_NO")
    private int categoryNo;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Column(name = "BOARD_REPORT_COUNT")
    private int reportCount;

    @Column(name = "BULLETIN_BOARD_SECRET_YN")
    private String secretStatus;

    public DashboardBoard() {
    }

    public DashboardBoard(int boardNo, String title, String content, Date createdDate, Date modifiedDate, Date deletedDate, String deleteStatus, int count, int categoryNo, int memberNo, int reportCount, String secretStatus) {
        this.boardNo = boardNo;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.deletedDate = deletedDate;
        this.deleteStatus = deleteStatus;
        this.count = count;
        this.categoryNo = categoryNo;
        this.memberNo = memberNo;
        this.reportCount = reportCount;
        this.secretStatus = secretStatus;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public int getCount() {
        return count;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public int getReportCount() {
        return reportCount;
    }

    public String getSecretStatus() {
        return secretStatus;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public void setSecretStatus(String secretStatus) {
        this.secretStatus = secretStatus;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardNo=" + boardNo +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", deletedDate=" + deletedDate +
                ", deleteStatus='" + deleteStatus + '\'' +
                ", count=" + count +
                ", categoryNo=" + categoryNo +
                ", memberNo=" + memberNo +
                ", reportCount=" + reportCount +
                ", secretStatus='" + secretStatus + '\'' +
                '}';
    }
}
