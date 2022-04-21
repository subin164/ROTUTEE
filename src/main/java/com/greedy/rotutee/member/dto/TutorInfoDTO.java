package com.greedy.rotutee.member.dto;

import lombok.*;

/**
 * packageName : com.greedy.rotutee.member.dto
 * fileName : TutoInfoDTO
 * author : 7sang
 * date : 2022-04-21
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-21 7sang 최초 생성
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TutorInfoDTO {

    private MemberDTO member;
    private String address;
    private String bankName;
    private String accountNumber;
}
