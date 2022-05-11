package com.greedy.rotutee.member.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * packageName : com.greedy.rotutee.member.member.entity
 * fileName : SecessionReason
 * author : 7sang
 * date : 2022-05-10
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-10 7sang 최초 생성
 */

@Entity(name = "Member_SecessionReason")
@Table(name = "TBL_SECESSION_REASON")
public class SecessionReason {

    @Id
    @Column(name = "SECESSION_REASON_NO")
    private int no;

    @Column(name = "SECESSION_REASON_NAME")
    private String name;

    public SecessionReason() {}

    public SecessionReason(int no, String name) {
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
        return "SecessionReason{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
