package com.greedy.rotutee.dashboard.mypage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.entity
 * fileName : DashboardCompletedLecture
 * author : SeoYoung
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 SeoYoung 최초 생성
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "Dashboard_Completedlecture")
@Table(name = "TBL_CUMULATIVE_TIME")
@SequenceGenerator(
        name = "DASHBOARD_COMPLETED_LECTURE_SEQ_GENERATOR",
        sequenceName = "CUMULATIVE_TIME_NO",
        initialValue = 1,
        allocationSize = 1
)
public class DashboardCompletedLecture {

    @Id
    @Column(name = "CUMULATIVE_TIME_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "DASHBOARD_COMPLETED_LECTURE_SEQ_GENERATOR"
    )
    private int timeNo;

    @Column(name = "FINISHED_WATCHING_YN")
    private String watchedStatus;

    @Column(name = "WATCH_COMPLETION_DATE")
    private Date completedDate;

    @ManyToOne
    @JoinColumn(name = "CLASS_NO")
    private DashboardClass lectureClass;

    @ManyToOne
    @JoinColumn(name = "MEMBER_LECTURE_NO")
    private DashboardMemberLecture memberLecture;

    public DashboardCompletedLecture() {}

    @Override
    public String toString() {
        return "DashboardCompletedLecture{" +
                "timeNo=" + timeNo +
                ", watchedStatus='" + watchedStatus + '\'' +
                ", completedDate=" + completedDate +
                ", lectureClass=" + lectureClass +
                ", memberLecture=" + memberLecture +
                '}';
    }
}
