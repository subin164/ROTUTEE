package com.greedy.rotutee.basket.dto;

import com.greedy.rotutee.basket.entity.PaymentChapter;
import lombok.*;

/**
 * packageName : com.greedy.rotutee.basket.dto
 * fileName : PaymentClass
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
public class PaymentClassDTO {
    private int classNo;
    private String className;
    private String videoPath;
    private PaymentChapter chapter;

}
