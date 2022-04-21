package com.greedy.rotutee.dashboard.mypage.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.entity
 * fileName : DashboardLectureWatch
 * author : SeoYoung
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 SeoYoung 최초 생성
 */
@Entity(name = "dashboardLectureWatch")
@Table(name = "TBL_LECTURE_WATCH_HISTORY")
@SequenceGenerator(
        name = "DASHBOARD_LECTURE_WATCH_SEQ_GENERATOR",
        sequenceName = "LECTURE_WATCH_HISTORY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class DashboardLectureWatch {

    @Id
    @Column(name = "LECTURE_WATCH_HISTORY_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "DASHBOARD_LECTURE_WATCH_SEQ_GENERATOR"
    )
    private int watchNo;

    @Column(name = "LECTURE_WATCH_DATE")
    private Date watchedDate;

    @ManyToOne
    @JoinColumn(name = "LECTURE_NO")
    private DashboardLecture lecture;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    public DashboardLectureWatch() {}

    public DashboardLectureWatch(int watchNo, Date watchedDate, DashboardLecture lecture, int memberNo) {
        this.watchNo = watchNo;
        this.watchedDate = watchedDate;
        this.lecture = lecture;
        this.memberNo = memberNo;
    }

    public int getWatchNo() {
        return watchNo;
    }

    public void setWatchNo(int watchNo) {
        this.watchNo = watchNo;
    }

    public Date getWatchedDate() {
        return watchedDate;
    }

    public void setWatchedDate(Date watchedDate) {
        this.watchedDate = watchedDate;
    }

    public DashboardLecture getLecture() {
        return lecture;
    }

    public void setLecture(DashboardLecture lecture) {
        this.lecture = lecture;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    @Override
    public String toString() {
        return "DashboardLectureWatch{" +
                "watchNo=" + watchNo +
                ", watchedDate=" + watchedDate +
                ", lecture=" + lecture +
                ", memberNo=" + memberNo +
                '}';
    }
}
