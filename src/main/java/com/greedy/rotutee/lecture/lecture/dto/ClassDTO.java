package com.greedy.rotutee.lecture.lecture.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ClassDTO {

    private int classNo;
    private String className;
    private String videoPath;
    private int chapterNo;
    private ChapterDTO chapter;

}
