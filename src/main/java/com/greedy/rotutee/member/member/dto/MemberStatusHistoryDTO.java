package com.greedy.rotutee.member.member.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberStatusHistoryDTO {

    private int historyNo;
    private String status;
    private Date historyDate;
    private MemberDTO member;
    @JsonManagedReference
    private SuspensionHitoryDTO suspensionHitory;
    @JsonManagedReference
    private MemberSecessionHistoryDTO memberSecessionHistory;
}
