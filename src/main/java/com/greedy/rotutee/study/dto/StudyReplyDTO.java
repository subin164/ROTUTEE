package com.greedy.rotutee.study.dto;

import com.greedy.rotutee.lecture.lecture.dto.MemberDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudyReplyDTO {
    private int replyNo;
    private String replyContent;
    private String replyStatus;
    private int replyReportCount;
    private java.sql.Date replyWriteDate;
    private java.sql.Date replyModifyDate;
    private int studyNO;
    private MemberDTO writer;

}
