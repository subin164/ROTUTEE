package com.greedy.rotutee.main.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ClassDTO {

    private int classNo;
    private String className;
    private String videoPath;
    private ChapterDTO chapter;
    private List<MultipartFile> fileList;

    private List<QuizDTO> quizList;
    private List<AttachedFileDTO> videoList;

    public ClassDTO() {
    }

    public ClassDTO(int classNo, String className, String videoPath, ChapterDTO chapter, List<MultipartFile> fileList, List<QuizDTO> quizList, List<AttachedFileDTO> videoList) {
        this.classNo = classNo;
        this.className = className;
        this.videoPath = videoPath;
        this.chapter = chapter;
        this.fileList = fileList;
        this.quizList = quizList;
        this.videoList = videoList;
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

    public List<AttachedFileDTO> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<AttachedFileDTO> videoList) {
        this.videoList = videoList;
    }

    public List<MultipartFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<MultipartFile> fileList) {
        this.fileList = fileList;
    }

    @Override
    public String toString() {
        return "ClassDTO{" +
                "classNo=" + classNo +
                ", className='" + className + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", chapter=" + chapter +
                ", fileList=" + fileList +
                '}';
    }
}
