package com.greedy.rotutee.member.admin.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Admin_MenuUrl")
@Table(name = "TBL_MENU_URL")
public class MenuUrl {

    @Id
    @Column(name = "MENU_URL_NO")
    private int no;

    @Column(name = "MENU_URL_NAME")
    private String name;


    public MenuUrl() {}

    public MenuUrl(int no, String name) {
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
        return "MenuUrl{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
