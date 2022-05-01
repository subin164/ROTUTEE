package com.greedy.rotutee.member.member.dto;

import lombok.*;

/**
 * packageName : com.greedy.rotutee.member.dto
 * fileName : AchievementDTO
 * author : 7sang
 * date : 2022-04-21
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-21 7sang 최초 생성
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AchievementDTO {

    private int achievementNo;
    private String achievementName;
    private String content;
    private String achievementPrecondition;
    private AchievementConditionDTO achievementCondition;
}
