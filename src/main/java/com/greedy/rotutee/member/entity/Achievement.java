package com.greedy.rotutee.member.entity;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.member.entity
 * fileName : Achievement
 * author : 7sang
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 7sang 최초 생성
 */

@Entity(name = "Member_Achievement")
@Table(name = "TBL_ACHIEVEMENT")
public class Achievement {

    @Id
    @Column(name = "ACHIEVEMENT_NO")
    private int achievementNo;

    @Column(name = "ACHIEVEMENT_NAME")
    private String achievementName;

    @Column(name = "ACHIEVEMENT_CONTENT")
    private String content;

    @Column(name = "ACHIEVEMENT_PRECONDITION")
    private String achievementPrecondition;

    @JoinColumn(name = "CONDITION_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private AchievementCondition achievementCondition;

    public Achievement() {}

    public Achievement(int achievementNo, String achievementName, String content, String achievementPrecondition, AchievementCondition achievementCondition) {
        this.achievementNo = achievementNo;
        this.achievementName = achievementName;
        this.content = content;
        this.achievementPrecondition = achievementPrecondition;
        this.achievementCondition = achievementCondition;
    }

    public int getAchievementNo() {
        return achievementNo;
    }

    public void setAchievementNo(int achievementNo) {
        this.achievementNo = achievementNo;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAchievementPrecondition() {
        return achievementPrecondition;
    }

    public void setAchievementPrecondition(String achievementPrecondition) {
        this.achievementPrecondition = achievementPrecondition;
    }

    public AchievementCondition getAchievementCondition() {
        return achievementCondition;
    }

    public void setAchievementCondition(AchievementCondition achievementCondition) {
        this.achievementCondition = achievementCondition;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "achievementNo=" + achievementNo +
                ", achievementName='" + achievementName + '\'' +
                ", content='" + content + '\'' +
                ", achievementPrecondition='" + achievementPrecondition + '\'' +
                '}';
    }
}
