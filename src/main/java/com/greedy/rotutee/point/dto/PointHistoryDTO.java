package com.greedy.rotutee.point.dto;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.point.dto
 * fileName : PointHistoryDTO
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
public class PointHistoryDTO {

    private int historyNo;
    private MemberDTO member;
    private Date changeDate;
    private int changePoint;
    private int finalPoint;
    private PointAcquisitionPlaceDTO pointAcquisitionPlace;
    private PointProductDTO pointProduct;
    private String division;
}
