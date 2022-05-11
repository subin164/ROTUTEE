package com.greedy.rotutee.member.member.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Member_AttendanceHistory")
@Table(name = "TBL_ATTENDANCE_HISTORY")
@SequenceGenerator(
        name = "ATTENDANCE_HISTORY_SEQ_GENERATOR",
        sequenceName = "ATTENDANCE_HISTORY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class AttendanceHistory {

    @Id
    @Column(name = "ATTENDANCE_HISTORY_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ATTENDANCE_HISTORY_SEQ_GENERATOR"
    )
    private int historyNo;

    @Column(name = "ATTENDANCE_DATE")
    private Date attendanceDate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    public AttendanceHistory() {}

    public AttendanceHistory(int historyNo, Date attendanceDate, Member member) {
        this.historyNo = historyNo;
        this.attendanceDate = attendanceDate;
        this.member = member;
    }

    public int getHistoryNo() {
        return historyNo;
    }

    public void setHistoryNo(int historyNo) {
        this.historyNo = historyNo;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "AttendanceHistory{" +
                "historyNo=" + historyNo +
                ", attendanceDate=" + attendanceDate +
                '}';
    }
}
