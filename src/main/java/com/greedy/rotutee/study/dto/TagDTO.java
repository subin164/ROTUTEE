package com.greedy.rotutee.study.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TagDTO {

    private int tagNo;
    private StudyDTO StudyNo;
    private String tagName;

}
