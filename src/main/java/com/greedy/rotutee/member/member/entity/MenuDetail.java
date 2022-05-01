package com.greedy.rotutee.member.member.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "MenuDetail")
@Table(name = "TBL_MENU_DETAIL")
public class MenuDetail {

    @Id
    @Column(name = "MENU_DETAIL_NO")
    private int no;

    @Column(name = "MENU_DETAIL_NAME")
    private String name;

    public MenuDetail() {}

    public MenuDetail(int no, String name) {
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
        return "MenuDetail{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
