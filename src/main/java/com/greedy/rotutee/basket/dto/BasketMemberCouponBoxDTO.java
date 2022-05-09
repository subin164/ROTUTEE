package com.greedy.rotutee.basket.dto;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.basket.dto
 * fileName : BasketMemberCouponBoxDTO
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
public class BasketMemberCouponBoxDTO {
    private int couponBoxNo;
    private Date couponExpirationDate;
    private Date couponRecevingDate;
    private BasketCouponDTO coupon;
    private MemberDTO member;
    private String couponStatus;

}
