package com.greedy.rotutee.dashboard.mypage.entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.entity
 * fileName : DashboardMember
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */
@Entity(name = "dashboardMember")
@Table(name = "TBL_MEMBER")
@SequenceGenerator(
        name = "DASHBOARD_MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class DashboardMember {

    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "DASHBOARD_MEMBER_SEQ_GENERATOR"
    )
    private int memberNo;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "MEMBER_EMAIL")
    private String email;

    @Column(name = "MEMBER_PASSWORD")
    private String pwd;

    @Column(name = "MEMBER_NICKNAME")
    private String nickname;

    @Column(name = "MEMBER_PHONE_NUM")
    private String phone;

    @Column(name = "MEMBER_INTRODUCTION")
    private String introduction;

    @Column(name = "REGISTRATION_DATE")
    private Date registedDate;

    @Column(name = "WITHDRAWAL_DATE")
    private Date withdrawedDate;

    @Column(name = "LEAVE_STATUS_YN")
    private String withdrawalStatusYn;

    @Column(name = "ROULETTE_CHANCE")
    private String rouletteChance;

    public DashboardMember() {}

    public DashboardMember(int memberNo, String memberName, String email, String pwd, String nickname, String phone, String introduction, Date registedDate, Date withdrawedDate, String withdrawalStatusYn, String rouletteChance) {
        this.memberNo = memberNo;
        this.memberName = memberName;
        this.email = email;
        this.pwd = pwd;
        this.nickname = nickname;
        this.phone = phone;
        this.introduction = introduction;
        this.registedDate = registedDate;
        this.withdrawedDate = withdrawedDate;
        this.withdrawalStatusYn = withdrawalStatusYn;
        this.rouletteChance = rouletteChance;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getRegistedDate() {
        return registedDate;
    }

    public void setRegistedDate(Date registedDate) {
        this.registedDate = registedDate;
    }

    public Date getWithdrawedDate() {
        return withdrawedDate;
    }

    public void setWithdrawedDate(Date withdrawedDate) {
        this.withdrawedDate = withdrawedDate;
    }

    public String getwithdrawalStatusYn() {
        return withdrawalStatusYn;
    }

    public void setwithdrawalStatusYn(String withdrawalStatusYn) {
        this.withdrawalStatusYn = withdrawalStatusYn;
    }

    public String getRouletteChance() {
        return rouletteChance;
    }

    public void setRouletteChance(String rouletteChance) {
        this.rouletteChance = rouletteChance;
    }

    @Override
    public String toString() {
        return "DashboardMember{" +
                "memberNo=" + memberNo +
                ", memberName='" + memberName + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", introduction='" + introduction + '\'' +
                ", registedDate=" + registedDate +
                ", withdrawedDate=" + withdrawedDate +
                ", withdrawalStatusYn='" + withdrawalStatusYn + '\'' +
                ", rouletteChance='" + rouletteChance + '\'' +
                '}';
    }
}
