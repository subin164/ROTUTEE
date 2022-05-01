package com.greedy.rotutee.dashboard.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.entity
 * fileName : LMSChapter
 * author : SeoYoung
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Lms_Chapter")
@Table(name = "TBL_CHAPTER")
@SequenceGenerator(
        name = "LMS_CHAPTER_SEQ_GENERATOR",
        sequenceName = "CHAPTER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class LMSChapter {

    @Id
    @Column(name = "CHAPTER_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LMS_CHAPTER_SEQ_GENERATOR"
    )
    private int chapterNo;

    @Column(name = "LECTURE_NO")
    private int lectureNo;

    @Column(name = "CHAPTER_NAME")
    private String chapterName;

    @Override
    public String toString() {
        return "LMSChapter{" +
                "chapterNo=" + chapterNo +
                ", lectureNo=" + lectureNo +
                ", chapterName='" + chapterName + '\'' +
                '}';
    }
}
