package com.greedy.rotutee.lecture.lecture.dto;

import lombok.*;

import java.sql.Date;

/**
 * packageName      : com.greedy.rotutee.lecture.lecture.dto
 * fileName         : PointHistoryDTO
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
public class PointHistoryDTO {

    private int historyNo;
    private MemberDTO member;
    private Date changeDate;
    private int changePoint;
    private int finalPoint;
    private PointAcquisitionPlaceDTO pointAcquisitionPlace;
    private String division;
}
