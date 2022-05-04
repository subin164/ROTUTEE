package com.greedy.rotutee.coupon.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CouponDTO {

    private int couponNo;
    private String couponName;
    private String couponContent;
    private java.sql.Date couponRegistDate;
    private int discountRate;
    private int expirationDate;
    private String couponStatus;

}
