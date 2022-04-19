package com.greedy.rotutee.main.controller.member.dto;

public class MemberRoleDTO {

    private int memberAuthorityNo;
    private RoleDTO authority;
    private MemberDTO member;

    public MemberRoleDTO() {}

    public MemberRoleDTO(int memberAuthorityNo, RoleDTO authority, MemberDTO member) {
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

    public RoleDTO getAuthority() {
        return authority;
    }

    public void setAuthority(RoleDTO authority) {
        this.authority = authority;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "MemberRoleDTO{" +
                "memberAuthorityNo=" + memberAuthorityNo +
                ", authority=" + authority +
                '}';
    }
}
