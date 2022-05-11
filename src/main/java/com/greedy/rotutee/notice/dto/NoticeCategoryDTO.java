package com.greedy.rotutee.notice.dto;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * packageName : com.greedy.rotutee.notice.dto
 * fileName : NoticeCategoryDTO
 * author : SeoYoung
 * date : 2022-05-11
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-11 SeoYoung 최초 생성
 */
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class NoticeCategoryDTO {

    private int noticeCategoryNo;
    private String noticeCategoryName;
}
