package com.greedy.rotutee.member.profile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * packageName : com.greedy.rotutee.member.entity
 * fileName : AchievementCondition
 * author : 7sang
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 7sang 최초 생성
 */

@Entity(name = "Member_AchievementCondition")
@Table(name = "TBL_CONDITION")
public class AchievementCondition {

    @Id
    @Column(name = "CONDITION_NO")
    private int no;

    @Column(name = "CONDITION_NAME")
    private String name;

    public AchievementCondition() {}

    public AchievementCondition(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
