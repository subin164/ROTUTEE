package com.greedy.rotutee.member.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * packageName : com.greedy.rotutee.member.member.entity
 * fileName : Reasons
 * author : 7sang
 * date : 2022-04-28
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-28 7sang 최초 생성
 */

@Entity(name = "Reasons")
@Table(name = "TBL_SUSPENSION_REASONS")
public class Reasons {

    @Id
    @Column(name = "SUSPENSION_REASONS_NO")
    private int no;

    @Column(name = "SUSPENSION_REASONS_CONTENT")
    private String content;

    public Reasons() {}

    public Reasons(int no, String content) {
        this.no = no;
        this.content = content;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Reasons{" +
                "no=" + no +
                ", content='" + content + '\'' +
                '}';
    }
}
