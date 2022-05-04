package com.greedy.rotutee.point.dto;

import lombok.*;


/**
 * packageName : com.greedy.rotutee.point.dto
 * fileName : PointAcquisitionPlaceDTO
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
public class PointAcquisitionPlaceDTO {

    private int placeNo;
    private String placeName;
    private Integer point;
}
