package com.greedy.rotutee.study.dto;

import com.greedy.rotutee.member.member.dto.MemberDTO;
import lombok.*;

import java.sql.Date;

/**
 * The type Study reply dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudyReplyDTO {
    /**
     * The Reply no.
     */
    private int replyNo;
    /**
     * The Reply content.
     */
    private String replyContent;
    /**
     * The Reply status.
     */
    private String replyStatus;
    /**
     * The Reply report count.
     */
    private int replyReportCount;
    /**
     * The Reply write date.
     */
    private java.sql.Date replyWriteDate;
    /**
     * The Reply modify date.
     */
    private java.sql.Date replyModifyDate;
    /**
     * The Reply remove date.
     */
    private java.sql.Date replyRemoveDate;
    /**
     * The Study no.
     */
    private int studyNo;
    /**
     * The Writer.
     */
    private MemberDTO writer;

}
