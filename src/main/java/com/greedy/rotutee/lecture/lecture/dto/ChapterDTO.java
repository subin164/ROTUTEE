package com.greedy.rotutee.lecture.lecture.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChapterDTO {

    private int chapterNo;
    private String chapterName;
    private int lectureNo;
    private List<ClassDTO> classList;

    @Override
    public String toString() {
        return "ChapterDTO{" +
                "chapterNo=" + chapterNo +
                ", chapterName='" + chapterName + '\'' +
                ", lectureNo=" + lectureNo +
//                ", classList=" + classList +
                '}';
    }
}
