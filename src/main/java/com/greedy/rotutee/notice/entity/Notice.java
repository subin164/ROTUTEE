package com.greedy.rotutee.notice.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.notice.entity
 * fileName : Notice
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
@Entity(name = "Notice")
@Table(name = "TBL_NOTICE")
@SequenceGenerator(
        name = "NOTICE_SEQ_GENERATOR",
        sequenceName = "TODO_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Notice {

    @Id
    @Column(name = "NOTICE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "NOTICE_SEQ_GENERATOR"
    )
    private int noticeNo;

    @Column(name = "NOTICE_CONTENTS")
    private String noticeContent;

    @ManyToOne
    @JoinColumn(name = "NOTICE_CATEGORY_NO")
    private NoticeCateogry noticeCategory;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Column(name = "NOTICE_TIME")
    private Date noticedDate;

    @Override
    public String toString() {
        return "Notice{" +
                "noticeNo=" + noticeNo +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeCategory=" + noticeCategory +
                ", memberNo=" + memberNo +
                ", noticedDate=" + noticedDate +
                '}';
    }
}
