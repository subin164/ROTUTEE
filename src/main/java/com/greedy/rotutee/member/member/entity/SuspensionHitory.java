package com.greedy.rotutee.member.member.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.member.member.entity
 * fileName : SuspensionHitory
 * author : 7sang
 * date : 2022-04-28
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-28 7sang 최초 생성
 */

@Entity(name = "SuspensionHitory")
@Table(name = "TBL_SUSPENSION_HISTORY")
@SequenceGenerator(
        name = "SUSPENSION_HISTORY_SEQ_GENERATOR",
        sequenceName = "SUSPENSION_HISTORY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class SuspensionHitory {

    @Id
    @Column(name = "SUSPENSION_HISTORY_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SUSPENSION_HISTORY_SEQ_GENERATOR"
    )
    private int historyNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_STATUS_HISTORY_NO")
    private MemberStatusHistory MemberStatusHistory;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "SUSPENSION_REASONS_NO")
    private Reasons reasons;

    public SuspensionHitory() {}

    public SuspensionHitory(int historyNo, com.greedy.rotutee.member.member.entity.MemberStatusHistory memberStatusHistory, Date startDate, Date endDate, Reasons reasons) {
        this.historyNo = historyNo;
        MemberStatusHistory = memberStatusHistory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reasons = reasons;
    }

    public int getHistoryNo() {
        return historyNo;
    }

    public void setHistoryNo(int historyNo) {
        this.historyNo = historyNo;
    }

    public com.greedy.rotutee.member.member.entity.MemberStatusHistory getMemberStatusHistory() {
        return MemberStatusHistory;
    }

    public void setMemberStatusHistory(com.greedy.rotutee.member.member.entity.MemberStatusHistory memberStatusHistory) {
        MemberStatusHistory = memberStatusHistory;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Reasons getReasons() {
        return reasons;
    }

    public void setReasons(Reasons reasons) {
        this.reasons = reasons;
    }

    @Override
    public String toString() {
        return "SuspensionHitory{" +
                "historyNo=" + historyNo +
//                ", MemberStatusHistory=" + MemberStatusHistory +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reasons=" + reasons +
                '}';
    }
}
