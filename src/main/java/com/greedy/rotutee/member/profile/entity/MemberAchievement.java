package com.greedy.rotutee.member.profile.entity;

import com.greedy.rotutee.member.member.entity.Member;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.member.entity
 * fileName : MemberAchievement
 * author : 7sang
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 7sang 최초 생성
 */

@Entity(name = "Profile_MemberAchievement")
@Table(name = "TBL_MEMBER_ACHIEVEMENT")
public class MemberAchievement {

    @Id
    @Column(name = "MEMBER_ACHIEVEMENT_NO")
    private int memberAchievementNo;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne
    private Member member;

    @JoinColumn(name = "ACHIEVEMENT_NO")
    @ManyToOne
    private Achievement achievement;

    @Column(name = "ACHIEVEMENT_GET_DATE")
    private Date getDat;

    public MemberAchievement() {}

    public MemberAchievement(int memberAchievementNo, Member member, Achievement achievement, Date getDat) {
        this.memberAchievementNo = memberAchievementNo;
        this.member = member;
        this.achievement = achievement;
        this.getDat = getDat;
    }

    public int getMemberAchievementNo() {
        return memberAchievementNo;
    }

    public void setMemberAchievementNo(int memberAchievementNo) {
        this.memberAchievementNo = memberAchievementNo;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public Date getGetDat() {
        return getDat;
    }

    public void setGetDat(Date getDat) {
        this.getDat = getDat;
    }
}
