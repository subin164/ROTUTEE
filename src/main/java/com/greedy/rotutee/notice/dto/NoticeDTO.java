package com.greedy.rotutee.notice.dto;

import com.greedy.rotutee.notice.entity.NoticeCateogry;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.notice.dto
 * fileName : NoticeDTO
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
public class NoticeDTO {

    private int noticeNo;
    private String noticeContent;
    private NoticeCateogry noticeCategory;
    private int memberNo;
    private Date noticedDate;
}
