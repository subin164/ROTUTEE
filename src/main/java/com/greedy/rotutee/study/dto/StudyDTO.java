package com.greedy.rotutee.study.dto;

import com.greedy.rotutee.member.dto.MemberDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StudyDTO {

    private int studyNo;
    private String content;
    private String title;
    private java.sql.Date writeDate;
    private int limited;
    private String linked;
    private int MemberNo;
    private MemberDTO writer;
    private java.sql.Date endDate;
    private String status;
    private int tag;
}