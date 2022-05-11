package com.greedy.rotutee.lecture.request.entity;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.sql.Date;

/**
 * packageName      : com.greedy.rotutee.lecture.request.entity
 * fileName         : Notice
 * author           : SEOK
 * date             : 2022-05-11
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-11      SEOK         최초 생성
 */
@Entity(name = "Request_Notice")
@Table(name = "TBL_NOTICE")
@SequenceGenerator(
        name = "NOTICE_SEQ_GENEROTOR",
        sequenceName = "NOTICE_NO",
        allocationSize = 1,
        initialValue = 1
)
public class Notice {

    @Id
    @Column(name = "NOTICE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "NOTICE_SEQ_GENEROTOR"
    )
    private int noticeNo;

    @Column(name = "NOTICE_CONTENTS")
    private String noticeContent;

    @JoinColumn(name = "NOTICE_CATEGORY_NO")
    @ManyToOne
    private NoticeCategory noticeCategory;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne
    private Member member;

    @Column(name = "NOTICE_TIME")
    private Date noticeTime;

    public Notice() {
    }

    public Notice(int noticeNo, String noticeContent, NoticeCategory noticeCategory, Member member, Date noticeTime) {
        this.noticeNo = noticeNo;
        this.noticeContent = noticeContent;
        this.noticeCategory = noticeCategory;
        this.member = member;
        this.noticeTime = noticeTime;
    }

    public int getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(int noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public NoticeCategory getNoticeCategory() {
        return noticeCategory;
    }

    public void setNoticeCategory(NoticeCategory noticeCategory) {
        this.noticeCategory = noticeCategory;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeNo=" + noticeNo +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeCategory=" + noticeCategory +
                ", member=" + member +
                ", noticeTime=" + noticeTime +
                '}';
    }
}
