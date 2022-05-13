package com.greedy.rotutee.member.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * packageName : com.greedy.rotutee.member.member.entity
 * fileName : Menu
 * author : 7sang
 * date : 2022-05-13
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-13 7sang 최초 생성
 */

@Entity(name = "Memmber_Menu")
@Table(name = "TBL_MENU")
public class Menu {

    @Id
    @Column(name = "MENU_NO")
    private int no;

    @Column(name = "MENU_URL")
    private String url;

    public Menu() {}

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "no=" + no +
                ", url='" + url + '\'' +
                '}';
    }
}
