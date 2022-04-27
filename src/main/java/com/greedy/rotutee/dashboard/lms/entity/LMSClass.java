package com.greedy.rotutee.dashboard.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.entity
 * fileName : LMSClass
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
@Entity(name = "Lms_Class")
@Table(name = "TBL_CLASS")
@SequenceGenerator(
        name = "LMS_CLASS_SEQ_GENERATOR",
        sequenceName = "CLASS_NO",
        initialValue = 1,
        allocationSize = 1
)
public class LMSClass {

    @Id
    @Column(name = "CLASS_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LMS_CLASS_SEQ_GENERATOR"
    )
    private int classNo;

    @Column(name = "CLASS_NAME")
    private String className;

    @Column(name = "VIDEO_PATH")
    private String videoPath;

    @Column(name = "CHAPTER_NO")
    private int chapNo;

    @Override
    public String toString() {
        return "LMSClass{" +
                "classNo=" + classNo +
                ", className='" + className + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", chapNo=" + chapNo +
                '}';
    }
}
