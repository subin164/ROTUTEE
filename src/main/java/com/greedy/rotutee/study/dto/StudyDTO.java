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

    private int no;
    private String content;
    private String title;
    private java.sql.Date writeDate;
    private int limited;
    private MemberDTO memberNo;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private String status;
    private String tag;
}