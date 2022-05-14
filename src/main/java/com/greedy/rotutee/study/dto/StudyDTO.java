package com.greedy.rotutee.study.dto;

import com.greedy.rotutee.member.member.dto.MemberDTO;
import lombok.*;

import java.util.List;

/**
 * The type StudyDTO.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudyDTO {

    /**
     * The Study no.
     */
    private int studyNo;                //작성글 번호
    /**
     * The Title.
     */
    private String title;               //작성글 제목
    /**
     * The Content.
     */
    private String content;             //작성글 내용
    /**
     * The Start date.
     */
    private java.sql.Date startDate;    //작성일
    /**
     * The End date.
     */
    private java.sql.Date endDate;      //모집 마감일
    /**
     * The Modify date.
     */
    private java.sql.Date modifyDate;   //수정 날짜
    /**
     * The Remove date.
     */
    private java.sql.Date removeDate;   //삭재 날짜
    /**
     * The Writer.
     */
    private MemberDTO writer;           //작성자
    /**
     * The Limited.
     */
    private int limited;                //모집인원
    /**
     * The Count.
     */
    private int count;                  //조회수
    /**
     * The Linked.
     */
    private String linked;              //신청링크
    /**
     * The Recruit status.
     */
    private String recruitStatus;       //모집상태
    /**
     * The Post status.
     */
    private String postStatus;          //삭재상태

}