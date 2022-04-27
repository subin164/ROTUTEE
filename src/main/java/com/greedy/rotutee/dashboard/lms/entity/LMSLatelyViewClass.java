package com.greedy.rotutee.dashboard.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

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
@Entity(name = "Lms_LatelyViewClass")
@Table(name = "TBL_CUMULATIVE_TIME")
@SequenceGenerator(
        name = "LMS_LATELY_CLASS_SEQ_GENERATOR",
        sequenceName = "CUMULATIVE_TIME_NO",
        initialValue = 1,
        allocationSize = 1
)
public class LMSLatelyViewClass {

    @Id
    @Column(name = "CUMULATIVE_TIME_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LMS_LATELY_CLASS_SEQ_GENERATOR"
    )
    private int timeNo;

    @Column(name = "FINISHED_WATCHING_YN")
    private String completedStatus;

    @Column(name = "WATCH_COMPLETION_DATE")
    private Date completedDate;

    @ManyToOne
    @JoinColumn(name = "CLASS_NO")
    private LMSClass lmsClass;

    @Column(name = "MEMBER_LECTURE_NO")
    private int memberLectureNo;

    @Override
    public String toString() {
        return "LMSLatelyViewClass{" +
                "timeNo=" + timeNo +
                ", completedStatus='" + completedStatus + '\'' +
                ", completedDate=" + completedDate +
                ", lmsClass=" + lmsClass +
                ", memberLectureNo=" + memberLectureNo +
                '}';
    }
}
