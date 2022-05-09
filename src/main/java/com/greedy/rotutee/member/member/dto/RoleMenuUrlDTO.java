package com.greedy.rotutee.member.member.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RoleMenuUrlDTO {

    private int roleMenuUrlNo;
    private MenuUrlDTO menuUrl;
    private MenuDetailDTO menuDetail;
    private RoleDTO role;
}
