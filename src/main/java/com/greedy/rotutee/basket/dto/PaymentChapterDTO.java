package com.greedy.rotutee.basket.dto;

import lombok.*;

/**
 * packageName : com.greedy.rotutee.basket.dto
 * fileName : PaymentChapterDTO
 * author : soobeen
 * date : 2022-05-13
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-13          soobeen     최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentChapterDTO {

    private int chapterNo;
    private String chapterName;
}
