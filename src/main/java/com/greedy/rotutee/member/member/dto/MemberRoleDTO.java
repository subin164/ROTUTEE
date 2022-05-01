package com.greedy.rotutee.member.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRoleDTO {

    private int memberRoleNo;
    private RoleDTO role;
    private MemberDTO member;

    @Override
    public String toString() {
        return "MemberRoleDTO{" +
                "memberRoleNo=" + memberRoleNo +
                '}';
    }
}
