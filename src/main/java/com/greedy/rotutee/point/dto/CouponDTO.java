package com.greedy.rotutee.point.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.point.dto
 * fileName : CouponDTO
 * author : 7sang
 * date : 2022-05-02
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-02 7sang 최초 생성
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CouponDTO {

    private int couponNo;
    private String couponName;
    private String couponContent;
    private Date registrationDate;
    private int discountRate;
    private int expirationDate;
    private String activationYn;
}
