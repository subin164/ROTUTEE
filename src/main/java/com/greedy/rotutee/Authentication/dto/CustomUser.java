package com.greedy.rotutee.Authentication.dto;


import com.greedy.rotutee.member.dto.MemberDTO;
import com.greedy.rotutee.member.dto.MemberRoleDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class CustomUser extends User {

    private int no;
    private String name;
    private String email;
    private String pwd;
    private String nickname;
    private String phoneNumber;
    private String introduction;
    private Date registrationDate;
    private Date withdrawalDate;
    private String leaveStatusYn;
    private String rouletteChance;
    private List<MemberRoleDTO> memberRoleList;

    public CustomUser(MemberDTO member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getEmail(), member.getPwd(), authorities);
        setDetails(member);
    }


    private void setDetails(MemberDTO member){
        this.no = member.getNo();
        this.name = member.getName();
        this.email = member.getEmail();
        this.pwd = member.getPwd();
        this.nickname = member.getNickname();
        this.phoneNumber = member.getPhoneNum();
        this.introduction = member.getIntroduction();
        this.registrationDate = member.getRegistrationDate();
        this.withdrawalDate = member.getWithdrawalDate();
        this.leaveStatusYn = member.getLeaveStatusYn();
        this.rouletteChance = member.getRouletteChance();
        this.memberRoleList = member.getMemberRoleList();
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public List<MemberRoleDTO> getMemberRoleList() {
        return memberRoleList;
    }

    public void setMemberRoleList(List<MemberRoleDTO> memberRoleList) {
        this.memberRoleList = memberRoleList;
    }

    @Override
    public String toString() {
        return "CustomUser{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", introduction='" + introduction + '\'' +
                ", registrationDate=" + registrationDate +
                ", withdrawalDate=" + withdrawalDate +
                ", leaveStatusYn='" + leaveStatusYn + '\'' +
                ", rouletteChance='" + rouletteChance + '\'' +
                ", memberRoleList=" + memberRoleList +
                '}';
    }
}