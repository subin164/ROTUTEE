package com.greedy.rotutee.member.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class   MemberStatusHistory {

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

//    @JsonIgnoreProperties(value = "MemberStatusHistory")
    @OneToOne(mappedBy = "MemberStatusHistory", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private SuspensionHitory suspensionHitory;

    @JsonIgnoreProperties(value = "MemberStatusHistory")
    @JsonManagedReference
    @OneToOne(mappedBy = "MemberStatusHistory", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private MemberSecessionHistory memberSecessionHistory;

    public MemberStatusHistory() {}

    public MemberStatusHistory(int historyNo, String status, Date historyDate, Member member, SuspensionHitory suspensionHitory, MemberSecessionHistory memberSecessionHistory) {
        this.historyNo = historyNo;
        this.status = status;
        this.historyDate = historyDate;
        this.member = member;
        this.suspensionHitory = suspensionHitory;
        this.memberSecessionHistory = memberSecessionHistory;
    }

    public MemberSecessionHistory getMemberSecessionHistory() {
        return memberSecessionHistory;
    }

    public void setMemberSecessionHistory(MemberSecessionHistory memberSecessionHistory) {
        this.memberSecessionHistory = memberSecessionHistory;
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
                ", suspensionHitory=" + suspensionHitory +
                '}';
    }
}
