package com.greedy.rotutee.main.dto;

import java.util.List;


public class ChapterDTO {

    private int chapterNo;
    private String chapterName;
    private LectureDTO lecture;
    private List<ClassDTO> classList;

    public ChapterDTO() {
    }

    public ChapterDTO(int chapterNo, String chapterName, LectureDTO lecture, List<ClassDTO> classList) {
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

    public LectureDTO getLecture() {
        return lecture;
    }

    public void setLecture(LectureDTO lecture) {
        this.lecture = lecture;
    }

    public List<ClassDTO> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassDTO> classList) {
        this.classList = classList;
    }

    @Override
    public String toString() {
        return "ChapterDTO{" +
                "chapterNo=" + chapterNo +
                ", chapterName='" + chapterName + '\'' +
                ", lecture=" + lecture +
//                ", classList=" + classList +
                '}';
    }
}
