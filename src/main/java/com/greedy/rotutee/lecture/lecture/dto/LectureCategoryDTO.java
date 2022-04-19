package com.greedy.rotutee.lecture.lecture.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LectureCategoryDTO {

    private int lectureCategoryNo;
    private String lectureCategoryName;
}
