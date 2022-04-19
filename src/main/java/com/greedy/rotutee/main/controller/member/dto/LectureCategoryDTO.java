package com.greedy.rotutee.main.controller.member.dto;

public class LectureCategoryDTO {

    private int no;
    private String name;

    public LectureCategoryDTO() {}

    public LectureCategoryDTO(int no, String name) {
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
