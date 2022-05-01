package com.greedy.rotutee.dashboard.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.dto
 * fileName : LMSChapterDTO
 * author : SeoYoung
 * date : 2022-04-28
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-28 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LMSChapterDTO {

    private int chapterNo;
    private int lectureNo;
    private List<LMSClassDTO> classesList;
    private String chapterName;

    @Override
    public String toString() {
        return "LMSChapterDTO{" +
                "chapterNo=" + chapterNo +
                ", lectureNo=" + lectureNo +
                ", classesList=" + classesList +
                ", chapterName='" + chapterName + '\'' +
                '}';
    }
}
