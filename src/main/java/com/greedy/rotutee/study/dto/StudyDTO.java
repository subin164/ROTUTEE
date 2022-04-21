package com.greedy.rotutee.study.dto;

import java.sql.Date;

public class StudyDTO {

    private int studyNo;                //작성글 번호
    private String title;               //작성글 제목
    private String content;             //작성글 내용
    private java.sql.Date startDate;    //작성일
    private java.sql.Date endDate;      //모집 마감일
    private int MemberNo;               //회원 번호
    private String writer;              //작성자 정보
    private int limited;                //모집인원
    private int count;                  //조회수
    private String linked;              //신청링크
    private String status;              //모집상태
    private int tagNo;                  //태그 목록 번호

    public StudyDTO() {
    }

    public StudyDTO(int studyNo, String title, String content, Date startDate, Date endDate, int memberNo, String writer, int limited, int count, String linked, String status, int tagNo) {
        this.studyNo = studyNo;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        MemberNo = memberNo;
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
        return MemberNo;
    }

    public void setMemberNo(int memberNo) {
        MemberNo = memberNo;
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
        return "StudyDTO{" +
                "studyNo=" + studyNo +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", MemberNo=" + MemberNo +
                ", writer='" + writer + '\'' +
                ", limited=" + limited +
                ", count=" + count +
                ", linked='" + linked + '\'' +
                ", status='" + status + '\'' +
                ", tagNo=" + tagNo +
                '}';
    }
}