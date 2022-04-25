package com.greedy.rotutee.member.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RoleDTO {

    private int no;           //권한코트
    private String name;        //권한명
    private String desc;        //권한설정

}
