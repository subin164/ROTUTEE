package com.greedy.rotutee.main.controller.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "LectureCategory")
@Table(name = "TBL_LECTURE_CATEGORY")
public class LectureCategory {

    @Id
    @Column(name = "LECTURE_CATEGORY_NO")
    private int no;

    @Column(name = "LECTURE_CATEGORY_NAME")
    private String name;

    public LectureCategory() {}

    public LectureCategory(int no, String name) {
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
        return "LectureCategory{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
