package com.greedy.rotutee.lecture.request.entity;


import javax.persistence.*;
import java.util.List;

@Entity(name = "Request_Chapter")
@Table(name = "TBL_CHAPTER")
public class Chapter {

    @Id
    @Column(name = "CHAPTER_NO")
    private int chapterNo;

    @Column(name = "CHAPTER_NAME")
    private String chapterName;

    @Column(name = "LECTURE_NO")
    private int lectureNo;

    @OneToMany(mappedBy = "chapter")
    private List<Class> classList;

    public Chapter() {
    }

    public Chapter(int chapterNo, String chapterName, int lectureNo, List<Class> classList) {
        this.chapterNo = chapterNo;
        this.chapterName = chapterName;
        this.lectureNo = lectureNo;
        this.classList = classList;
    }

    public int getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(int chapterNo) {
        this.chapterNo = chapterNo;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(int lectureNo) {
        this.lectureNo = lectureNo;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "chapterNo=" + chapterNo +
                ", chapterName='" + chapterName + '\'' +
                ", lectureNo=" + lectureNo +
//                ", classList=" + classList +
                '}';
    }
}
