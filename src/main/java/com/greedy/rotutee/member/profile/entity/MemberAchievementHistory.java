package com.greedy.rotutee.member.profile.entity;

import com.greedy.rotutee.member.member.entity.Member;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.member.entity
 * fileName : MemberAchievementHistory
 * author : 7sang
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 7sang 최초 생성
 */

@Entity(name = "Member_MemberAchievementHistory")
@Table(name = "TBL_MEMBER_ACHIEVEMENT_HISTORY")
public class MemberAchievementHistory {

    @Id
    @Column(name = "MEMBER_ACHIEVEMENT_HISTORY_NO")
    private int historyNo;

    @JoinColumn(name = "MEMBER_ACHIEVEMENT_NO")
    @ManyToOne
    private MemberAchievement memberAchievement;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne
    private Member member;

    public MemberAchievementHistory() {}

    public MemberAchievementHistory(int historyNo, MemberAchievement memberAchievement, Member member) {
        this.historyNo = historyNo;
        this.memberAchievement = memberAchievement;
        this.member = member;
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
