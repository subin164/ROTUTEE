package com.greedy.rotutee.coupon.dto;

import com.greedy.rotutee.member.member.dto.MemberDTO;
import lombok.*;

/**
 * The type MemberCouponBoxDTO.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MemberCouponBoxDTO {

    /**
     * The Coupon box no.
     */
    private int couponBoxNo;
    /**
     * The Expiration date.
     */
    private java.sql.Date expirationDate;
    /**
     * The Receiving date.
     */
    private java.sql.Date receivingDate;
    /**
     * The Coupon.
     */
    private CouponDTO coupon;
    /**
     * The Member no.
     */
    private MemberDTO memberNo;
    /**
     * The Coupon status.
     */
    private String couponStatus;
}
