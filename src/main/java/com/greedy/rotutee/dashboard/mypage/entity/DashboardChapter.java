package com.greedy.rotutee.dashboard.mypage.entity;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.entity
 * fileName : DashboardChapter
 * author : SeoYoung
 * date : 2022-04-21
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-21 SeoYoung 최초 생성
 */
@Entity(name = "Dashboard_Chapter")
@Table(name = "TBL_CHAPTER")
@SequenceGenerator(
        name = "DASHBOARD_CHAPTER_SEQ_GENERATOR",
        sequenceName = "CHAPTER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class DashboardChapter {
    @Id
    @Column(name = "CHAPTER_NO")
    private int chapterNo;

    @ManyToOne
    @JoinColumn(name = "LECTURE_NO")
    private DashboardLecture lecture;

    @Column(name = "CHAPTER_NAME")
    private String chapterName;

    public DashboardChapter() {}

    public DashboardChapter(int chapterNo, DashboardLecture lecture, String chapterName) {
        this.chapterNo = chapterNo;
        this.lecture = lecture;
        this.chapterName = chapterName;
    }

    public int getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(int chapterNo) {
        this.chapterNo = chapterNo;
    }

    public DashboardLecture getLecture() {
        return lecture;
    }

    public void setLecture(DashboardLecture lecture) {
        this.lecture = lecture;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    @Override
    public String toString() {
        return "DashboardChapter{" +
                "chapterNo=" + chapterNo +
                ", lecture=" + lecture +
                ", chapterName='" + chapterName + '\'' +
                '}';
    }
}
