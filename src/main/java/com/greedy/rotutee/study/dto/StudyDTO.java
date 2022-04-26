package com.greedy.rotutee.study.dto;

import com.greedy.rotutee.member.member.dto.MemberDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudyDTO {

    private int studyNo;                //작성글 번호
    private String title;               //작성글 제목
    private String content;             //작성글 내용
    private java.sql.Date startDate;    //작성일
    private java.sql.Date endDate;      //모집 마감일
    private MemberDTO writer;           //작성자
    private int limited;                //모집인원
    private int count;                  //조회수
    private String linked;              //신청링크
    private String status;              //모집상태

}