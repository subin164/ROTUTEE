package com.greedy.rotutee.member.member.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuspensionHitoryDTO {


    private int historyNo;
//    @JsonManagedReference
//    private MemberStatusHistoryDTO MemberStatusHistory;
    private Date startDate;
    private Date endDate;
    @JsonManagedReference
    private ReasonsDTO reasons;
}
