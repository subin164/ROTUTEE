package com.greedy.rotutee.basket.entity;

import javax.persistence.*;

/**
 * packageName      : com.greedy.rotutee.basket.entity
 * fileName         : MemberInterest
 * author           : SEOK
 * date             : 2022-05-06
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-06      SEOK         최초 생성
 */
@Entity(name = "Basket_MemberInterest")
@Table(name = "TBL_MEMBER_INTEREST_PART")
@SequenceGenerator(
        name = "INTEREST_NO_SEQ_GENERATOR",
        sequenceName = "INTEREST_NO",
        allocationSize = 1,
        initialValue = 1
)
public class MemberInterest {

    @Id
    @Column(name = "INTEREST_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "INTEREST_NO_SEQ_GENERATOR"
    )
    private int interestNo;

    @Column(name = "INTEREST_DEGREE")
    private int interestDegree;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne
    private Member member;

    @JoinColumn(name = "LECTURE_CATEGORY_NO")
    @ManyToOne
    private LectureCategory category;

    public MemberInterest() {
    }

    public MemberInterest(int interestNo, int interestDegree, Member member, LectureCategory category) {
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LectureCategory getCategory() {
        return category;
    }

    public void setCategory(LectureCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "MemberInterest{" +
                "interestNo=" + interestNo +
                ", interestDegree=" + interestDegree +
                ", member=" + member +
                ", category=" + category +
                '}';
    }
}
