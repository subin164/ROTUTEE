package com.greedy.rotutee.lecture.request.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * packageName      : com.greedy.rotutee.lecture.request.entity
 * fileName         : NoticeCategory
 * author           : SEOK
 * date             : 2022-05-11
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-11      SEOK         최초 생성
 */
@Entity(name = "Request_NoticeCategory")
@Table(name = "TBL_NOTICE_CATEGORY")
public class NoticeCategory {

    @Id
    @Column(name = "NOTICE_CATEGORY_NO")
    private int noticeCategoryNo;

    @Column(name = "NOTICE_CATEGORY_NAME")
    private String noticeCategoryName;

    public NoticeCategory() {
    }

    public NoticeCategory(int noticeCategoryNo, String noticeCategoryName) {
        this.noticeCategoryNo = noticeCategoryNo;
        this.noticeCategoryName = noticeCategoryName;
    }

    public int getNoticeCategoryNo() {
        return noticeCategoryNo;
    }

    public void setNoticeCategoryNo(int noticeCategoryNo) {
        this.noticeCategoryNo = noticeCategoryNo;
    }

    public String getNoticeCategoryName() {
        return noticeCategoryName;
    }

    public void setNoticeCategoryName(String noticeCategoryName) {
        this.noticeCategoryName = noticeCategoryName;
    }

    @Override
    public String toString() {
        return "NoticeCategory{" +
                "noticeCategoryNo=" + noticeCategoryNo +
                ", noticeCategoryName='" + noticeCategoryName + '\'' +
                '}';
    }
}
