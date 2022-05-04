package com.greedy.rotutee.board.freeboard.dto;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * packageName : com.greedy.rotutee.board.dto
 * fileName : FreeBoardAnswerDTO
 * author : soobeen
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-22          soobeen     최초 생성
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardAnswerDTO {
    private int answerNo;
    private String answerContent;
    private char answerYN;
    private int answerReportCount;
    private Timestamp answerCreatedDate;
    private Date answerModifyDate;
    private Date answerDeleteDate;
    private FreeBoardDTO freeBoard;
    private FreeBoardMemberDTO freeBoardMember;


}
