package com.greedy.rotutee.notice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * packageName : com.greedy.rotutee.notice.entity
 * fileName : NoticeCateogry
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
@Entity(name = "Notice_Category")
@Table(name = "TBL_NOTICE_CATEGORY")
public class NoticeCateogry {
    @Id
    @Column(name = "NOTICE_CATEGORY_NO")
    private int noticeCategoryNo;

    @Column(name = "NOTICE_CATEGORY_NAME")
    private String noticeCategoryName;

    @Override
    public String toString() {
        return "NoticeCateogry{" +
                "noticeCategoryNo=" + noticeCategoryNo +
                ", noticeCategoryName='" + noticeCategoryName + '\'' +
                '}';
    }
}
