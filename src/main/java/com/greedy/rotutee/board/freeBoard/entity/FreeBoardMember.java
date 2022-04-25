package com.greedy.rotutee.board.freeBoard.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.board.entity
 * fileName : Member
 * author : soobeen
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-20        soobeen     최초 생성
 */

@Entity(name = "FreeBoardMember")
@Table(name = "TBL_MEMBER")
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class FreeBoardMember {

    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR"
    )
    private int memberNo;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "MEMBER_EMAIL")
    private String memberEmail;

    @Column(name = "MEMBER_PASSWORD")
    private String memberPassword;

    @Column(name = "MEMBER_NICKNAME")
    private String memberNickName;

    @Column(name = "MEMBER_INTRODUCTION")
    private String memberIntroduction;

    @Column(name = "MEMBER_PHONE_NUM")
    private String memberPhoneNum;

    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

    @Column(name = "WITHDRAWAL_DATE")
    private Date withdrawalDate;

    @Column(name = "LEAVE_STATUS_YN")
    private char leaveStatusYN;

    @Column(name = "ROULETTE_CHANCE")
    private Integer rouletteChange;

    public FreeBoardMember() {
    }

    public FreeBoardMember(int memberNo, String memberName, String memberEmail, String memberPassword, String memberNickName, String memberIntroduction, String memberPhoneNum, Date registrationDate, Date withdrawalDate, char leaveStatusYN, Integer rouletteChange) {
        this.memberNo = memberNo;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberNickName = memberNickName;
        this.memberIntroduction = memberIntroduction;
        this.memberPhoneNum = memberPhoneNum;
        this.registrationDate = registrationDate;
        this.withdrawalDate = withdrawalDate;
        this.leaveStatusYN = leaveStatusYN;
        this.rouletteChange = rouletteChange;
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

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getMemberIntroduction() {
        return memberIntroduction;
    }

    public void setMemberIntroduction(String memberIntroduction) {
        this.memberIntroduction = memberIntroduction;
    }

    public String getMemberPhoneNum() {
        return memberPhoneNum;
    }

    public void setMemberPhoneNum(String memberPhoneNum) {
        this.memberPhoneNum = memberPhoneNum;
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

    public char getLeaveStatusYN() {
        return leaveStatusYN;
    }

    public void setLeaveStatusYN(char leaveStatusYN) {
        this.leaveStatusYN = leaveStatusYN;
    }

    public Integer getRouletteChange() {
        return rouletteChange;
    }

    public void setRouletteChange(Integer rouletteChange) {
        this.rouletteChange = rouletteChange;
    }

    @Override
    public String toString() {
        return "FreeBoardMember{" +
                "memberNo=" + memberNo +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberNickName='" + memberNickName + '\'' +
                ", memberIntroduction='" + memberIntroduction + '\'' +
                ", memberPhoneNum='" + memberPhoneNum + '\'' +
                ", registrationDate=" + registrationDate +
                ", withdrawalDate=" + withdrawalDate +
                ", leaveStatusYN=" + leaveStatusYN +
                ", rouletteChange=" + rouletteChange +
                '}';
    }
}
