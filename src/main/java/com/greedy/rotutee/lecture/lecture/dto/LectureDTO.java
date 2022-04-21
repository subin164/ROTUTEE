package com.greedy.rotutee.lecture.lecture.dto;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureDTO {

    private int lectureNo;
    private String lectureName;
    private int lecturePrice;
    private String lectureLevel;
    private String lectureSummary;
    private String lecturedetails;
    private String revisionHistory;
    private String lectureApprovalStatus;
    private Date lectureOpeningDate;
    private int memberNo;
    private MemberDTO tutor;
    private Date applicationDate;
    private String applicationDivision;
    private int lectureCategoryNo;
    private LectureCategoryDTO category;

    @Override
    public String toString() {
        return "LectureDTO{" +
                "lectureNo=" + lectureNo +
                ", lectureName='" + lectureName + '\'' +
                ", lecturePrice=" + lecturePrice +
                ", lectureLevel='" + lectureLevel + '\'' +
                ", lectureSummary='" + lectureSummary + '\'' +
                ", lecturedetails='" + lecturedetails + '\'' +
                ", revisionHistory='" + revisionHistory + '\'' +
                ", lectureApprovalStatus='" + lectureApprovalStatus + '\'' +
                ", lectureOpeningDate=" + lectureOpeningDate +
                ", memberNo=" + memberNo +
                ", tutor=" + tutor +
                ", applicationDate=" + applicationDate +
                ", applicationDivision='" + applicationDivision + '\'' +
                ", lectureCategoryNo=" + lectureCategoryNo +
                ", category=" + category +
                '}';
    }
}
