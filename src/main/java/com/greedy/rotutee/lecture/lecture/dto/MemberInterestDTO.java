package com.greedy.rotutee.lecture.lecture.dto;

/**
 * packageName      : com.greedy.rotutee.lecture.lecture.dto
 * fileName         : MemberInterestDTO
 * author           : SEOK
 * date             : 2022-05-06
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-06      SEOK         최초 생성
 */
public class MemberInterestDTO {

    private int interestNo;
    private int interestDegree;
    private MemberDTO member;
    private LectureCategoryDTO category;

    public MemberInterestDTO() {
    }

    public MemberInterestDTO(int interestNo, int interestDegree, MemberDTO member, LectureCategoryDTO category) {
        this.interestNo = interestNo;
        this.interestDegree = interestDegree;
        this.member = member;
        this.category = category;
    }

    public int getInterestNo() {
        return interestNo;
    }

    public void setInterestNo(int interestNo) {
        this.interestNo = interestNo;
    }

    public int getInterestDegree() {
        return interestDegree;
    }

    public void setInterestDegree(int interestDegree) {
        this.interestDegree = interestDegree;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public LectureCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(LectureCategoryDTO category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "MemberInterestDTO{" +
                "interestNo=" + interestNo +
                ", interestDegree=" + interestDegree +
                ", member=" + member +
                ", category=" + category +
                '}';
    }
}
