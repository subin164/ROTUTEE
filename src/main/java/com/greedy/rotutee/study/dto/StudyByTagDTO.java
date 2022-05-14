package com.greedy.rotutee.study.dto;

import lombok.*;

/**
 * The type StudyByTagDTO.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StudyByTagDTO {

    /**
     * The Study by tag no.
     */
    private int StudyByTagNo;
    /**
     * The Study.
     */
    private StudyDTO study;
    /**
     * The Tag.
     */
    private TagDTO tag;
}
