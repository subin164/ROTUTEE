package com.greedy.rotutee.main.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRoleDTO {

    private int memberAuthorityNo;
    private RoleDTO authority;
    private MemberDTO member;

    @Override
    public String toString() {
        return "MemberRoleDTO{" +
                "memberAuthorityNo=" + memberAuthorityNo +
                ", authority=" + authority +
//                ", member=" + member +
                '}';
    }
}
