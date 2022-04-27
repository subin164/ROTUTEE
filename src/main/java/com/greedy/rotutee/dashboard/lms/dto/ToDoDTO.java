package com.greedy.rotutee.dashboard.lms.dto;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.dto
 * fileName : ToDoDTO
 * author : SeoYoung
 * date : 2022-04-25
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-25 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ToDoDTO {

    private int todoNo;
    private String content;
    private Date registedDate;
    private String achievementStatus;
    private int lectureNo;
    private int memberLectureNo;
    private int memberNo;
    private int dailyProgress;
    private int weekProgress;
    private int monthProgress;

}
