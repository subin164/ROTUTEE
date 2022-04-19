package com.greedy.rotutee.board.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {
    private int boardNo;
    private String title;
    private String content;
    private Date createdDate;
    private Date modifiedDate;
    private Date deletedDate;
    private String deleteStatus;
    private int count;
    private int categoryNo;
    private int reportCount;
    private String secretStatus;
}
