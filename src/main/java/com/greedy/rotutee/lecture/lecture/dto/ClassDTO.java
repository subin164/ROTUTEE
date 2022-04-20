package com.greedy.rotutee.lecture.lecture.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassDTO {

    private int classNo;
    private String className;
    private String videoPath;
    private int chapterNo;
    private ChapterDTO chapter;

    @Override
    public String toString() {
        return "ClassDTO{" +
                "classNo=" + classNo +
                ", className='" + className + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", chapterNo=" + chapterNo +
//                ", chapter=" + chapter +
                '}';
    }
}
