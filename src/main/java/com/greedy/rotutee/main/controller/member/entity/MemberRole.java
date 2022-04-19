package com.greedy.rotutee.main.controller.member.entity;

import javax.persistence.*;

@Entity(name = "MemberRole")
@Table(name = "TBL_MEMBER_ROLE")
@SequenceGenerator(
        name = "MEMBER_ROLE_SEQ_GENERATOR",
        sequenceName = "MEMBER_ROLE_NO",
        initialValue = 1,
        allocationSize = 1
)
public class MemberRole {

    @Id
    @Column(name = "MEMBER_ROLE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_ROLE_SEQ_GENERATOR"
    )
    private int memberRoleNo;

    @ManyToOne
    @JoinColumn(name = "ROLE_NO")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    public MemberRole() {}

    public MemberRole(int memberRoleNo, Role role, Member member) {
        this.memberRoleNo = memberRoleNo;
        this.role = role;
        this.member = member;
    }

    public int getMemberRoleNo() {
        return memberRoleNo;
    }

    public void setMemberRoleNo(int memberRoleNo) {
        this.memberRoleNo = memberRoleNo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
                "memberRoleNo=" + memberRoleNo +
                ", role=" + role +
                ", member=" + member +
                '}';
    }
}
