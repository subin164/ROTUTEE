package com.greedy.rotutee.basket.dto;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.basket.dto
 * fileName : bsketCouponDTO
 * author : soobeen
 * date : 2022-05-09
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-09          soobeen     최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BasketCouponDTO {
    private int couponNo ;
    private String couponName;
    private String couponContent;
    private Date couponRegistrationDate;
    private int couponDiscountRate;
    private int couponExpirationDate;
    private String couponActivationYn;
    private Date couponDeletionDate;
    private Date couponModifiedDate;
}
