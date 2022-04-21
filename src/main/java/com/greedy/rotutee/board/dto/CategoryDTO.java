package com.greedy.rotutee.board.dto;

import lombok.*;

/**
 * packageName : com.greedy.rotutee.board.dto
 * fileName : CategoryDTO
 * author : soobeen
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-20          soobeen     최초 생성
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO {
    private int boardCategoryNo;
    private String boardCategoryName;
    private int upperCategoryNo;


}
