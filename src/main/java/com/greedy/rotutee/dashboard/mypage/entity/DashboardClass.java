package com.greedy.rotutee.dashboard.mypage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.entity
 * fileName : DashboardClass
 * author : SeoYoung
 * date : 2022-04-21
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-21 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="Dashboard_Class")
@Table(name = "TBL_CLASS")
@SequenceGenerator(
        name = "DASHBOARD_CLASS_SEQ_GENERATOR",
        sequenceName = "CLASS_NO",
        initialValue = 1,
        allocationSize = 1
)
public class DashboardClass {

    @Id
    @Column(name = "CLASS_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "DASHBOARD_CLASS_SEQ_GENERATOR"
    )
    private int classNo;

    @Column(name = "CLASS_NAME")
    private String className;

    @Column(name = "VIDEO_PATH")
    private String videoPath;

    @ManyToOne
    @JoinColumn(name = "CHAPTER_NO")
    private DashboardChapter chapter;



}
