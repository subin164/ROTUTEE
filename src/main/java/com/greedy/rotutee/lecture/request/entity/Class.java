package com.greedy.rotutee.lecture.request.entity;

import javax.persistence.*;

@Entity(name = "Request_Class")
@Table(name = "TBL_CLASS")
public class Class {

    @Id
    @Column(name = "CLASS_NO")
    private int classNo;

    @Column(name = "CLASS_NAME")
    private String className;

    @Column(name = "VIDEO_PATH")
    private String videoPath;

    @JoinColumn(name = "CHAPTER_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Chapter chapter;

    public Class() {
    }

    public Class(int classNo, String className, String videoPath, Chapter chapter) {
        this.classNo = classNo;
        this.className = className;
        this.videoPath = videoPath;
        this.chapter = chapter;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classNo=" + classNo +
                ", className='" + className + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", chapter=" + chapter +
                '}';
    }
}
