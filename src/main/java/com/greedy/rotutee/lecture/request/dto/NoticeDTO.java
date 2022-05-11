package com.greedy.rotutee.lecture.request.dto;

import java.sql.Date;

/**
 * packageName      : com.greedy.rotutee.lecture.request.dto
 * fileName         : NoticeDTO
 * author           : SEOK
 * date             : 2022-05-11
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-11      SEOK         최초 생성
 */
public class NoticeDTO {

    private int noticeNo;
    private String noticeContent;
    private NoticeCategoryDTO noticeCategory;
    private MemberDTO member;
    private Date noticeTime;

    public NoticeDTO() {
    }

    public NoticeDTO(int noticeNo, String noticeContent, NoticeCategoryDTO noticeCategory, MemberDTO member, Date noticeTime) {
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

    public NoticeCategoryDTO getNoticeCategory() {
        return noticeCategory;
    }

    public void setNoticeCategory(NoticeCategoryDTO noticeCategory) {
        this.noticeCategory = noticeCategory;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
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
        return "NoticeDTO{" +
                "noticeNo=" + noticeNo +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeCategory=" + noticeCategory +
                ", member=" + member +
                ", noticeTime=" + noticeTime +
                '}';
    }
}
