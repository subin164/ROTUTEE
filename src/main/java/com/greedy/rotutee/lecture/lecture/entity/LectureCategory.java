package com.greedy.rotutee.lecture.lecture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Lecture_LectureCategory")
@Table(name = "TBL_LECTURE_CATEGORY")
public class LectureCategory {

    @Id
    @Column(name = "LECTURE_CATEGORY_NO")
    private int lectureCategoryNo;

    @Column(name = "LECTURE_CATEGORY_NAME")
    private String lectureCategoryName;

    public LectureCategory() {
    }

    public LectureCategory(int lectureCategoryNo, String lectureCategoryName) {
        this.lectureCategoryNo = lectureCategoryNo;
        this.lectureCategoryName = lectureCategoryName;
    }

    public int getLectureCategoryNo() {
        return lectureCategoryNo;
    }

    public void setLectureCategoryNo(int lectureCategoryNo) {
        this.lectureCategoryNo = lectureCategoryNo;
    }

    public String getLectureCategoryName() {
        return lectureCategoryName;
    }

    public void setLectureCategoryName(String lectureCategoryName) {
        this.lectureCategoryName = lectureCategoryName;
    }

    @Override
    public String toString() {
        return "LectureCategory{" +
                "lectureCategoryNo=" + lectureCategoryNo +
                ", lectureCategoryName='" + lectureCategoryName + '\'' +
                '}';
    }
}
