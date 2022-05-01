package com.greedy.rotutee.member.profile.repository;

import com.greedy.rotutee.member.member.entity.Member;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.member.entity
 * fileName : TutorInfo
 * author : 7sang
 * date : 2022-04-21
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-21 7sang 최초 생성
 */

@Entity(name = "Member_TutorInfo")
@Table(name = "TBL_TUTOR_INFO")
public class TutorInfo {

    @Id
    @Column(name = "MEMBER_NO")
    private int memberNo;

    @JoinColumn(name = "MEMBER_NO")
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    public TutorInfo() {}

    public TutorInfo(int memberNo, Member member, String address, String bankName, String accountNumber) {
        this.memberNo = memberNo;
        this.member = member;
        this.address = address;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "TutorInfo{" +
                "memberNo=" + memberNo +
                ", member=" + member +
                ", address='" + address + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
