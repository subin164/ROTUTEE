package com.greedy.rotutee.main.controller.member.dto;

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

    @Override
    public String toString() {
        return "MemberDTO{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", introduction='" + introduction + '\'' +
                ", registrationDate=" + registrationDate +
                ", withdrawalDate=" + withdrawalDate +
                ", leaveStatusYn='" + leaveStatusYn + '\'' +
                ", rouletteChance='" + rouletteChance + '\'' +
                '}';
    }
}
