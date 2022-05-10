package com.greedy.rotutee.member.member.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSecessionHistoryDTO {

    private int historyNo;
//    @JsonManagedReference
//    private MemberStatusHistoryDTO MemberStatusHistory;
    @JsonManagedReference
    private SecessionReasonDTO secessionReason;
    private Date secessionDate;
    private String content;
}
