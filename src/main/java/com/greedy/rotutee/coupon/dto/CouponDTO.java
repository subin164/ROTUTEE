package com.greedy.rotutee.coupon.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CouponDTO {

    private int publishCouponNo;
    private String publishCouponName;
    private String publishCouponContent;
    private java.sql.Date publishCouponRegistDate;
    private int discountRate;
    private int expirationDate;
    private String publishCouponStatus;

}
