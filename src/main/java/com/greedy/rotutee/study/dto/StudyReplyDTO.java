package com.greedy.rotutee.study.dto;

import com.greedy.rotutee.member.member.dto.MemberDTO;
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
    private java.sql.Date replyRemoveDate;
    private int studyNo;
    private MemberDTO writer;

}
