package com.greedy.rotutee.member.member.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.member.member.entity
 * fileName : MemberStatusHistory
 * author : 7sang
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 7sang 최초 생성
 */

@Entity(name = "Member_MemberStatusHistory")
@Table(name = "TBL_MEMBER_STATUS_HISTORY")
@SequenceGenerator(
        name = "MEMBER_STATUS_SEQ_GENERATOR",
        sequenceName = "MEMBER_STATUS_HISTORY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class MemberStatusHistory {

    @Id
    @Column(name = "MEMBER_STATUS_HISTORY_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_STATUS_SEQ_GENERATOR"
    )
    private int historyNo;

    @Column(name = "MEMBER_STATUS")
    private String status;

    @Column(name = "MEMBER_STATUS_HISTORY_DATE")
    private Date historyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @OneToOne(mappedBy = "MemberStatusHistory", cascade = CascadeType.PERSIST)
    private SuspensionHitory suspensionHitory;

    public MemberStatusHistory() {}

    public MemberStatusHistory(int historyNo, String status, Date historyDate, Member member) {
        this.historyNo = historyNo;
        this.status = status;
        this.historyDate = historyDate;
        this.member = member;
    }

    public SuspensionHitory getSuspensionHitory() {
        return suspensionHitory;
    }

    public void setSuspensionHitory(SuspensionHitory suspensionHitory) {
        this.suspensionHitory = suspensionHitory;
    }

    public int getHistoryNo() {
        return historyNo;
    }

    public void setHistoryNo(int historyNo) {
        this.historyNo = historyNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Date historyDate) {
        this.historyDate = historyDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "MemberStatusHistory{" +
                "historyNo=" + historyNo +
                ", status='" + status + '\'' +
                ", historyDate=" + historyDate +
                ", member=" + member +
                '}';
    }
}