package com.greedy.rotutee.main.controller.member.dto;

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
