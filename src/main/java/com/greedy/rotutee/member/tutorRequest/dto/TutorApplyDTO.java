package com.greedy.rotutee.member.tutorRequest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import lombok.*;

import java.sql.Date;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.dto
 * fileName : tutorRequestDTO
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
public class TutorApplyDTO {

    private int applyHistoryNo;
    private MemberDTO member;
    private Date applyDate;
    private String applyYn;
    private Date applyStatusDate;
    private MemberDTO admin;
    @JsonIgnoreProperties(value = "tutorApply")
    private List<CareerDTO> careerList;
    @JsonIgnoreProperties(value = "tutorApply")
    private List<QualificationDTO> qualificationList;

    @Override
    public String toString() {
        return "TutorApplyDTO{" +
                "applyHistoryNo=" + applyHistoryNo +
                ", member=" + member +
                ", applyDate=" + applyDate +
                ", applyYn='" + applyYn + '\'' +
                ", applyStatusDate=" + applyStatusDate +
                ", admin=" + admin +
//                ", careerList=" + careerList +
//                ", qualificationList=" + qualificationList +
                '}';
    }
}
