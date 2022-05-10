package com.greedy.rotutee.member.member.entity;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.member.member.entity
 * fileName : MemberInterstPart
 * author : 7sang
 * date : 2022-05-10
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-10 7sang 최초 생성
 */

@Entity(name = "Member_MemberInterestPart")
@Table(name = "TBL_MEMBER_INTEREST_PART")
@SequenceGenerator(
        name = "MEMBER_INTEREST_PART_SEQ_GENERATOR",
        sequenceName = "INTEREST_NO",
        initialValue = 1,
        allocationSize = 1
)
public class MemberInterestPart {

    @Id
    @Column(name = "INTEREST_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_INTEREST_PART_SEQ_GENERATOR"
    )
    private int no;

    @Column(name = "INTEREST_DEGREE")
    private int interestDegree;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "LECTURE_CATEGORY_NO")
    private LectureCategory lectureCategory;

    public MemberInterestPart() {}

    public MemberInterestPart(int no, int interestDegree, Member member, LectureCategory lectureCategory) {
        this.no = no;
        this.interestDegree = interestDegree;
        this.member = member;
        this.lectureCategory = lectureCategory;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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

    public LectureCategory getLectureCategory() {
        return lectureCategory;
    }

    public void setLectureCategory(LectureCategory lectureCategory) {
        this.lectureCategory = lectureCategory;
    }

    @Override
    public String toString() {
        return "MemberInterestPart{" +
                "no=" + no +
                ", interestDegree=" + interestDegree +
                ", member=" + member +
                ", lectureCategory=" + lectureCategory +
                '}';
    }
}
