package com.greedy.rotutee.lecture.lecture.dto;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberDTO {

    private int no;
    private String name;
    private String email;
    private String pwd;
    private String nickname;
    private String phoneNum;
    private String introduction;
    private Date registrationDate;
    private Date withdrawalDate;
    private String leaveStatusYn;
    private String rouletteChance;
    private List<MemberRoleDTO> memberRoleList;

    private String imageName;
}
