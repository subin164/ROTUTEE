package com.greedy.rotutee.member.profile.dto;

import lombok.*;

/**
 * packageName : com.greedy.rotutee.member.dto
 * fileName : AddressDTO
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
public class AddressDTO {

    private String zipCode;
    private String address1;
    private String address2;
}
