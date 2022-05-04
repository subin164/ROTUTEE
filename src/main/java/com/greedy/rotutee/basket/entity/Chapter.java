package com.greedy.rotutee.basket.entity;


import javax.persistence.*;
import java.util.List;

@Entity(name = "Basket_Chapter")
@Table(name = "TBL_CHAPTER")
public class Chapter {

    @Id
    @Column(name = "CHAPTER_NO")
    private int chapterNo;

    @Column(name = "CHAPTER_NAME")
    private String chapterName;

    @JoinColumn(name = "LECTURE_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lecture lecture;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.PERSIST)
    private List<Class> classList;

    public Chapter() {
    }

    public Chapter(int chapterNo, String chapterName, Lecture lecture, List<Class> classList) {
        this.chapterNo = chapterNo;
        this.chapterName = chapterName;
        this.lecture = lecture;
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

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
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
                ", lecture=" + lecture +
//                ", classList=" + classList +
                '}';
    }
}
