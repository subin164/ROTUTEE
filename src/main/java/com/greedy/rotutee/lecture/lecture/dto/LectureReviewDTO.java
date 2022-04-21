package com.greedy.rotutee.lecture.lecture.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LectureReviewDTO {

    private int lectureReviewNo;
    private int lectureGrade;
    private String lectureReviewContent;
    private Date lectureReviewDate;
    private String lectureReviewRemoveYN;
    private MemberDTO writer;
    private int lectureNo;
}
