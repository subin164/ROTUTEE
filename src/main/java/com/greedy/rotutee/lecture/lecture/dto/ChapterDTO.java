package com.greedy.rotutee.lecture.lecture.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChapterDTO {

    private int chapterNo;
    private String chapterName;
    private int lectureNo;
    private LectureDTO lecture;
    private List<ClassDTO> classList;

}
