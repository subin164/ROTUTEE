package com.greedy.rotutee.board.freeBoard.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FreeBoardDTO {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private Date boardCreationDate;
    private Date boardModifiedDate;
    private Date boardDeletionDate;
    private char boardDeleteYN;
    private int boardViewCount;
    private FreeBoardCategoryDTO freeBoardCategory;
    private FreeBoardMemberDTO  freeBoardMember;
    private int boardReportCount;
    private char bulletinBoardSecretYN;
    private FreeBoardAnswerDTO freeBoardAnswerList;
}
