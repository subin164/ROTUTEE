package com.greedy.rotutee.board.dto;

import lombok.*;

import javax.persistence.Column;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.board.dto
 * fileName : MemberDTO
 * author : soobeen
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-20          soobeen     최초 생성
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

    private int memberNo;

    private String memberName;

    private String memberEmail;

    private String memberPassword;

    private String memberNickName;

    private String memberIntroduction;

    private String memberPhoneNum;

    private Date registrationDate;

    private Date withdrawalDate;

    private char leaveStatusYN;

    private int rouletteChange;

}
