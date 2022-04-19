package com.greedy.rotutee.dashboard.mypage.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.mypage.entity
 * fileName : DashboardNotice
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */
@Entity(name = "dashboardNotice")
@Table(name = "TBL_NOTICE")
@SequenceGenerator(
        name = "DASHBOARD_NOTICE_SEQ_GENERATOR",
        sequenceName = "NOTICE_NO",
        initialValue = 1,
        allocationSize = 1
)
public class DashboardNotice {

    @Id
    @Column(name = "NOTICE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "DASHBOARD_NOTICE_SEQ_GENERATOR"
    )
    private int noticeNo;

    @Column(name = "NOTICE_CONTENTS")
    private String content;

    @Column(name = "NOTICE_CATEGORY_NO")
    private int categoryNo;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Column(name = "NOTICE_TIME")
    private Date noticedTime;

    public DashboardNotice() {}

    public DashboardNotice(int noticeNo, String content, int categoryNo, int memberNo, Date noticedTime) {
        this.noticeNo = noticeNo;
        this.content = content;
        this.categoryNo = categoryNo;
        this.memberNo = memberNo;
        this.noticedTime = noticedTime;
    }

    public int getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(int noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public Date getNoticedTime() {
        return noticedTime;
    }

    public void setNoticedTime(Date noticedTime) {
        this.noticedTime = noticedTime;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "noticeNo=" + noticeNo +
                ", content='" + content + '\'' +
                ", categoryNo=" + categoryNo +
                ", memberNo=" + memberNo +
                ", noticedTime=" + noticedTime +
                '}';
    }
}
