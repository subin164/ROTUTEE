package com.greedy.rotutee.basket.dto;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.basket.dto
 * fileName : CumulateTimeDTO
 * author : soobeen
 * date : 2022-05-12
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-12          soobeen     최초 생성
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CumulateTimeDTO {

    private int cumlateTimeNo;
    private String finishedWatchingYN;
    private Date watchCompletionDate;
    private ClassDTO cumClass;
    private MemberLectureDTO memberLecture;


}
