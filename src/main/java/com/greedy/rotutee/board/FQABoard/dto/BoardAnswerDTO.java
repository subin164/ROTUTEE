package com.greedy.rotutee.board.FQABoard.dto;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.dto
 * fileName : BoardAnswerDTO
 * author : 7sang
 * date : 2022-05-04
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-04 7sang 최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardAnswerDTO {

    private int no;
    private String content;
    private char answerYn;
    private int reportCount;
    private Date createdDate;
    private Date modifyDate;
    private Date deleteDate;
    private BoardDTO board;
    private MemberDTO member;
}
