package com.greedy.rotutee.coupon.dto;

import com.greedy.rotutee.member.member.dto.MemberDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MemberCouponBoxDTO {

    private int couponBoxNo;
    private java.sql.Date expirationDate;
    private java.sql.Date receivingDate;
    private CouponDTO couponNo;
    private MemberDTO memberNo;
    private String couponStatus;
}
