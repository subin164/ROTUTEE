package com.greedy.rotutee.board.serviceBoard.entity;

import javax.persistence.*;
import java.sql.Date;


@Entity(name = "ServiceBoard_Member")
@Table(name = "TBL_MEMBER")
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Member {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR"
    )
    @Column(name = "MEMBER_NO")
    private int no;

    @Column(name = "MEMBER_NAME")
    private String name;

    @Column(name = "MEMBER_EMAIL")
    private String email;

    @Column(name = "MEMBER_PASSWORD")
    private String pwd;

    @Column(name = "MEMBER_NICKNAME")
    private String nickname;

    @Column(name = "MEMBER_PHONE_NUM")
    private String phoneNum;

    @Column(name = "MEMBER_INTRODUCTION")
    private String introduction;

    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

    @Column(name = "WITHDRAWAL_DATE")
    private Date withdrawalDate;

    @Column(name = "LEAVE_STATUS_YN")
    private String leaveStatusYn;

    @Column(name = "ROULETTE_CHANCE")
    private String rouletteChance;


    public Member() {}

    public Member(int no, String name, String email, String pwd, String nickname, String phoneNum, String introduction, Date registrationDate, Date withdrawalDate, String leaveStatusYn, String rouletteChance) {
        this.no = no;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.nickname = nickname;
        this.phoneNum = phoneNum;
        this.introduction = introduction;
        this.registrationDate = registrationDate;
        this.withdrawalDate = withdrawalDate;
        this.leaveStatusYn = leaveStatusYn;
        this.rouletteChance = rouletteChance;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public String getLeaveStatusYn() {
        return leaveStatusYn;
    }

    public void setLeaveStatusYn(String leaveStatusYn) {
        this.leaveStatusYn = leaveStatusYn;
    }

    public String getRouletteChance() {
        return rouletteChance;
    }

    public void setRouletteChance(String rouletteChance) {
        this.rouletteChance = rouletteChance;
    }

    @Override
    public String toString() {
        return "Member{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", introduction='" + introduction + '\'' +
                ", registrationDate=" + registrationDate +
                ", withdrawalDate=" + withdrawalDate +
                ", leaveStatusYn='" + leaveStatusYn + '\'' +
                ", rouletteChance='" + rouletteChance + '\'' +
                '}';
    }


}
