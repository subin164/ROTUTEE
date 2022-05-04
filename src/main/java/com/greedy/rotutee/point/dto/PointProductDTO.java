package com.greedy.rotutee.point.dto;

import lombok.*;

/**
 * packageName : com.greedy.rotutee.point.dto
 * fileName : PointProductDTO
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
public class PointProductDTO {

    private int productNo;
    private String productName;
    private int productPrice;
    private int remainingNumber;
    private int totalSalesCount;
    private String productStatus;
    private String productContent;
    private CouponDTO coupon;
}
