package com.greedy.rotutee.lecture.lecture.dto;

import com.greedy.rotutee.member.dto.MemberDTO;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
}
