package com.greedy.rotutee.member.member.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Member_LoginHistory")
@Table(name = "TBL_LOGIN_HISTORY")
@SequenceGenerator(
        name = "LOGIN_HISTORY_SEQ_GENERATOR",
        sequenceName = "LOGIN_HISTORY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class LoginHistory {

    @Id
    @Column(name = "LOGIN_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LOGIN_HISTORY_SEQ_GENERATOR"
    )
    private int loginNo;

    @Column(name = "LOGIN_DATE")
    private Date loginDate;

    @Column(name = "LOGIN_IP")
    private String loginIp;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    public LoginHistory() {}

    public LoginHistory(int loginNo, Date loginDate, Member member) {
        this.loginNo = loginNo;
        this.loginDate = loginDate;
        this.member = member;
    }

    public int getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(int loginNo) {
        this.loginNo = loginNo;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
