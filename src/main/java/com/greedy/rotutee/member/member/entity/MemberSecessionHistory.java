package com.greedy.rotutee.member.member.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.member.member.entity
 * fileName : MemberSecessionHistory
 * author : 7sang
 * date : 2022-05-10
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-10 7sang 최초 생성
 */

@Entity(name = "Member_MemberSecessionHistory")
@Table(name = "TBL_MEMBER_SECESSION_HISTORY")
@SequenceGenerator(
        name = "MEMBER_SECESSION_SEQ_GENERATOR",
        sequenceName = "MEMBER_SECESSION_HISTORY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class MemberSecessionHistory {


    @Id
    @Column(name = "SECESSION_HISTORY_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SECESSION_SEQ_GENERATOR"
    )
    private int historyNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_STATUS_HISTORY_NO")
    private MemberStatusHistory MemberStatusHistory;

    @ManyToOne
    @JoinColumn(name = "SECESSION_REASON_NO")
    private SecessionReason secessionReason;

    @Column(name = "SECESSION_DATE")
    private Date secessionDate;

    @Column(name = "SECESSION_CONTENT")
    private String content;

    public MemberSecessionHistory() {}

    public MemberSecessionHistory(int historyNo, com.greedy.rotutee.member.member.entity.MemberStatusHistory memberStatusHistory, SecessionReason secessionReason, Date secessionDate, String content) {
        this.historyNo = historyNo;
        MemberStatusHistory = memberStatusHistory;
        this.secessionReason = secessionReason;
        this.secessionDate = secessionDate;
        this.content = content;
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

    public SecessionReason getSecessionReason() {
        return secessionReason;
    }

    public void setSecessionReason(SecessionReason secessionReason) {
        this.secessionReason = secessionReason;
    }

    public Date getSecessionDate() {
        return secessionDate;
    }

    public void setSecessionDate(Date secessionDate) {
        this.secessionDate = secessionDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MemberSecessionHistory{" +
                "historyNo=" + historyNo +
//                ", MemberStatusHistory=" + MemberStatusHistory +
                ", secessionReason=" + secessionReason +
                ", secessionDate=" + secessionDate +
                ", content='" + content + '\'' +
                '}';
    }
}
