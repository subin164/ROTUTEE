package com.greedy.rotutee.board.FQABoard.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.dto
 * fileName : BoardDTO
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
public class BoardDTO {

    private int no;
    private String title;
    private String content;
    private Date creationDate;
    private Date modifiedDate;
    private Date deletionDate;
    private char deleteYN;
    private int viewCount;
    private BoardCategoryDTO boardCategory;
    private MemberDTO member;
    private int reportCount;
    private char bulletinBoardSecretYN;
    @JsonIgnoreProperties(value = {"board"})
    private List<BoardAnswerDTO> boardAnswerList;

    @Override
    public String toString() {
        return "BoardDTO{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", modifiedDate=" + modifiedDate +
                ", deletionDate=" + deletionDate +
                ", deleteYN=" + deleteYN +
                ", viewCount=" + viewCount +
                ", boardCategory=" + boardCategory +
                ", member=" + member +
                ", reportCount=" + reportCount +
                ", bulletinBoardSecretYN=" + bulletinBoardSecretYN +
                '}';
    }
}
