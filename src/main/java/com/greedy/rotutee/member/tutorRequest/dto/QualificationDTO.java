package com.greedy.rotutee.member.tutorRequest.dto;

import lombok.*;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.dto
 * fileName : QualificationDTO
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
public class QualificationDTO {

    private int qualificationNo;
    private TutorApplyDTO tutorApply;
    private Date acquisitionDate;
    private String name;
    private String rating;

    @Override
    public String toString() {
        return "QualificationDTO{" +
                "qualificationNo=" + qualificationNo +
//                ", tutorApply=" + tutorApply +
                ", acquisitionDate=" + acquisitionDate +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
