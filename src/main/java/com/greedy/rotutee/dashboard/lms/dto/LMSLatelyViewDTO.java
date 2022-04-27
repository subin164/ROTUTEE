package com.greedy.rotutee.dashboard.lms.dto;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.dto
 * fileName : LMSLatelyViewDTO
 * author : SeoYoung
 * date : 2022-04-26
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-26 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LMSLatelyViewDTO {

    private int timeNo;
    private String completedStatus;
    private Date completedDate;
    private LMSClassDTO lmsClass;
    private int memberLectureNo;
}
