package com.greedy.rotutee.main.entity;

import javax.persistence.*;

@Entity(name = "Main_MemberAuthority")
@Table(name = "TBL_MEMBER_ROLE")
public class MemberRole {

    @Id
    @Column(name = "MEMBER_ROLE_NO")
    private int memberAuthorityNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_NO")
    private Role authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    public MemberRole() {}

    public MemberRole(int memberAuthorityNo, Role authority, Member member) {
        this.memberAuthorityNo = memberAuthorityNo;
        this.authority = authority;
        this.member = member;
    }

    public int getMemberAuthorityNo() {
        return memberAuthorityNo;
    }

    public void setMemberAuthorityNo(int memberAuthorityNo) {
        this.memberAuthorityNo = memberAuthorityNo;
    }

    public Role getAuthority() {
        return authority;
    }

    public void setAuthority(Role authority) {
        this.authority = authority;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "MemberRole{" +
                "memberAuthorityNo=" + memberAuthorityNo +
                ", authority=" + authority +
                ", member=" + member +
                '}';
    }
}
