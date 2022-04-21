package com.greedy.rotutee.board.dto;

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
    private String boardDeleteYN;
    private int boardViewCount;
    private int boardCategoryNo;
    private int  memberNo;
    private int boardReportCount;
    private String bulletinBoardSecretYN;

}
