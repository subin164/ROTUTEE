package com.greedy.rotutee.lecture.request.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassDTO {

    private int classNo;
    private String className;
    private String videoPath;
    private ChapterDTO chapter;

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
