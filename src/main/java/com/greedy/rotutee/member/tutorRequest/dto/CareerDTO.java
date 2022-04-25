package com.greedy.rotutee.member.tutorRequest.dto;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.dto
 * fileName : CareerDTO
 * author : 7sang
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-22 7sang 최초 생성
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CareerDTO {

    private int CareerNo;
    private TutorApplyDTO tutorApply;
    private Date startDate;
    private Date endDate;
    private String position;
    private String company;
    private String responsibilities;

    @Override
    public String toString() {
        return "CareerDTO{" +
                "CareerNo=" + CareerNo +
//                ", tutorApply=" + tutorApply +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", position='" + position + '\'' +
                ", company='" + company + '\'' +
                ", responsibilities='" + responsibilities + '\'' +
                '}';
    }
}
