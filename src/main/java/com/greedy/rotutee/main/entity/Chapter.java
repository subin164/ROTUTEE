package com.greedy.rotutee.main.entity;


import javax.persistence.*;
import java.util.List;

@Entity(name = "Main_Chapter")
@Table(name = "TBL_CHAPTER")
@SequenceGenerator(
        name = "REQUEST_CHAPTER_SEQ_GENERATOR",
        sequenceName = "CHAPTER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Chapter {

    @Id
    @Column(name = "CHAPTER_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "REQUEST_CHAPTER_SEQ_GENERATOR"
    )
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
