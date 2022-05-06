package com.greedy.rotutee.board.FQABoard.dto;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.dto
 * fileName : MemberDTO
 * author : 7sang
 * date : 2022-05-04
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-04 7sang 최초 생성
 */
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
}
