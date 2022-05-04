package com.greedy.rotutee.board.serviceBoard.dto;

import lombok.*;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.dto
 * fileName : BoardCategoryDTO
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
public class BoardCategoryDTO {

    private int no;
    private String name;
    private BoardCategoryDTO boardCategory;
    private List<BoardCategoryDTO> upperCategoryList;
}
