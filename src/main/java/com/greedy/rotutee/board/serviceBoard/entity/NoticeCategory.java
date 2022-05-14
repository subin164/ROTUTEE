package com.greedy.rotutee.board.serviceBoard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.entity
 * fileName : NoticeCategory
 * author : 7sang
 * date : 2022-05-14
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-14 7sang 최초 생성
 */

@Entity(name = "serviceBoard_NoticeCategory")
@Table(name = "TBL_NOTICE_CATEGORY")
public class NoticeCategory {

    @Id
    @Column(name = "NOTICE_CATEGORY_NO")
    private int no;

    @Column(name = "NOTICE_CATEGORY_NAME")
    private String name;

    public NoticeCategory() {}

    public NoticeCategory(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NoticeCategory{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
