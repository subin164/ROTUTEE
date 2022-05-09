package com.greedy.rotutee.lecture.lecture.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName      : com.greedy.rotutee.lecture.lecture.entity
 * fileName         : PointHistory
 * author           : SEOK
 * date             : 2022-05-09
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-09      SEOK         최초 생성
 */
@Entity(name = "Lecture_PointHistory")
@Table(name = "TBL_POINT_HISTORY")
@SequenceGenerator(
        name = "LECTURE_POINT_HISTORY_SEQ_GENERATOR",
        sequenceName = "POINT_HISTORY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class PointHistory {

    @Id
    @Column(name = "POINT_HISTORY_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LECTURE_POINT_HISTORY_SEQ_GENERATOR"
    )
    private int historyNo;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(name = "POINT_CHANGE_DATE")
    private Date changeDate;

    @Column(name = "POINT_CHANGE_POINTS")
    private int changePoint;

    @Column(name = "POINT_FINAL_POINTS")
    private int finalPoint;

    @JoinColumn(name = "ACQUISITION_PLACE_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private PointAcquisitionPlace pointAcquisitionPlace;

    @Column(name = "POINT_DIVISION")
    private String division;

    public PointHistory() {
    }

    public PointHistory(int historyNo, Member member, Date changeDate, int changePoint, int finalPoint, PointAcquisitionPlace pointAcquisitionPlace, String division) {
        this.historyNo = historyNo;
        this.member = member;
        this.changeDate = changeDate;
        this.changePoint = changePoint;
        this.finalPoint = finalPoint;
        this.pointAcquisitionPlace = pointAcquisitionPlace;
        this.division = division;
    }

    public int getHistoryNo() {
        return historyNo;
    }

    public void setHistoryNo(int historyNo) {
        this.historyNo = historyNo;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public int getChangePoint() {
        return changePoint;
    }

    public void setChangePoint(int changePoint) {
        this.changePoint = changePoint;
    }

    public int getFinalPoint() {
        return finalPoint;
    }

    public void setFinalPoint(int finalPoint) {
        this.finalPoint = finalPoint;
    }

    public PointAcquisitionPlace getPointAcquisitionPlace() {
        return pointAcquisitionPlace;
    }

    public void setPointAcquisitionPlace(PointAcquisitionPlace pointAcquisitionPlace) {
        this.pointAcquisitionPlace = pointAcquisitionPlace;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return "PointHistory{" +
                "historyNo=" + historyNo +
                ", member=" + member +
                ", changeDate=" + changeDate +
                ", changePoint=" + changePoint +
                ", finalPoint=" + finalPoint +
                ", pointAcquisitionPlace=" + pointAcquisitionPlace +
                ", division='" + division + '\'' +
                '}';
    }
}
