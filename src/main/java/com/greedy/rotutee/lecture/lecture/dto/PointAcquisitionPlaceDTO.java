package com.greedy.rotutee.lecture.lecture.dto;

import lombok.*;

/**
 * packageName      : com.greedy.rotutee.lecture.lecture.dto
 * fileName         : PointAcquisitionPlaceDTO
 * author           : SEOK
 * date             : 2022-05-09
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-09      SEOK         최초 생성
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
