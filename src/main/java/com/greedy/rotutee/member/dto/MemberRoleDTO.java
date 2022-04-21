package com.greedy.rotutee.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberRoleDTO {

    private int memberRoleNo;
    private RoleDTO role;
    private MemberDTO member;

}
