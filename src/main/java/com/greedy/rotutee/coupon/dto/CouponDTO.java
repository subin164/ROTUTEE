package com.greedy.rotutee.coupon.dto;

import lombok.*;

/**
 * The type CouponDTO.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CouponDTO {

    /**
     * The Publish coupon no.
     */
    private int publishCouponNo;
    /**
     * The Publish coupon name.
     */
    private String publishCouponName;
    /**
     * The Publish coupon content.
     */
    private String publishCouponContent;
    /**
     * The Publish coupon regist date.
     */
    private java.sql.Date publishCouponRegistDate;
    /**
     * The Discount rate.
     */
    private int discountRate;
    /**
     * The Expiration date.
     */
    private int expirationDate;
    /**
     * The Publish coupon status.
     */
    private String publishCouponStatus;

}
