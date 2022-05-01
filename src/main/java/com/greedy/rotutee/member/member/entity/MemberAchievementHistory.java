package com.greedy.rotutee.member.member.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.member.member.entity
 * fileName : MemberAchievementHistory
 * author : 7sang
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 7sang 최초 생성
 */

@Entity(name = "MemberAchievementHistory")
@Table(name = "TBL_MEMBER_ACHIEVEMENT_HISTORY")
@SequenceGenerator(
        name = "MEMBER_ACHIEVEMENT_HISTORY_SEQ_GENERATOR",
        sequenceName = "MEMBER_ACHIEVEMENT_HISTORY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class MemberAchievementHistory {

    @Id
    @Column(name = "MEMBER_ACHIEVEMENT_HISTORY_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_ACHIEVEMENT_HISTORY_SEQ_GENERATOR"
    )
    private int historyNo;

    @JoinColumn(name = "MEMBER_ACHIEVEMENT_NO")
    @ManyToOne
    private MemberAchievement memberAchievement;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne
    private Member member;

    @Column(name = "ACHIEVEMENT_CHANGE_DATE")
    private Date changeDate;

    public MemberAchievementHistory() {}

    public MemberAchievementHistory(int historyNo, MemberAchievement memberAchievement, Member member) {
        this.historyNo = historyNo;
        this.memberAchievement = memberAchievement;
        this.member = member;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public int getHistoryNo() {
        return historyNo;
    }

    public void setHistoryNo(int historyNo) {
        this.historyNo = historyNo;
    }

    public MemberAchievement getMemberAchievement() {
        return memberAchievement;
    }

    public void setMemberAchievement(MemberAchievement memberAchievement) {
        this.memberAchievement = memberAchievement;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "MemberAchievementHistory{" +
                "historyNo=" + historyNo +
                ", memberAchievement=" + memberAchievement +
                '}';
    }

}
