package com.greedy.rotutee.lecture.request.dto;

/**
 * packageName      : com.greedy.rotutee.lecture.request.dto
 * fileName         : NoticeCategoryDTO
 * author           : SEOK
 * date             : 2022-05-11
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-11      SEOK         최초 생성
 */
public class NoticeCategoryDTO {

    private int noticeCategoryNo;
    private String noticeCategoryName;

    public NoticeCategoryDTO() {
    }

    public NoticeCategoryDTO(int noticeCategoryNo, String noticeCategoryName) {
        this.noticeCategoryNo = noticeCategoryNo;
        this.noticeCategoryName = noticeCategoryName;
    }

    public int getNoticeCategoryNo() {
        return noticeCategoryNo;
    }

    public void setNoticeCategoryNo(int noticeCategoryNo) {
        this.noticeCategoryNo = noticeCategoryNo;
    }

    public String getNoticeCategoryName() {
        return noticeCategoryName;
    }

    public void setNoticeCategoryName(String noticeCategoryName) {
        this.noticeCategoryName = noticeCategoryName;
    }

    @Override
    public String toString() {
        return "NoticeCategoryDTO{" +
                "noticeCategoryNo=" + noticeCategoryNo +
                ", noticeCategoryName='" + noticeCategoryName + '\'' +
                '}';
    }
}
