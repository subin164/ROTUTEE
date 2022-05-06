package com.greedy.rotutee.board.serviceBoard.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class BoardCategoryDTO {

    private int no;
    private String name;
    private BoardCategoryDTO boardCategory;
    @JsonIgnoreProperties(value = {"boardCategory"})
    private List<BoardCategoryDTO> upperCategoryList;

    @Override
    public String toString() {
        return "BoardCategoryDTO{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", upperCategoryList=" + upperCategoryList +
                '}';
    }
}
