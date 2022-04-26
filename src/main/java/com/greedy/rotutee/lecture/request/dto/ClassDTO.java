package com.greedy.rotutee.lecture.request.dto;

import java.util.List;

public class ClassDTO {

    private int classNo;
    private String className;
    private String videoPath;
    private ChapterDTO chapter;

    private List<QuizDTO> quizList;

    public ClassDTO() {
    }

    public ClassDTO(int classNo, String className, String videoPath, ChapterDTO chapter, List<QuizDTO> quizList) {
        this.classNo = classNo;
        this.className = className;
        this.videoPath = videoPath;
        this.chapter = chapter;
        this.quizList = quizList;
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

    public ChapterDTO getChapter() {
        return chapter;
    }

    public void setChapter(ChapterDTO chapter) {
        this.chapter = chapter;
    }

    public List<QuizDTO> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<QuizDTO> quizList) {
        this.quizList = quizList;
    }

    @Override
    public String toString() {
        return "ClassDTO{" +
                "classNo=" + classNo +
                ", className='" + className + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", chapter=" + chapter +
                '}';
    }
}
