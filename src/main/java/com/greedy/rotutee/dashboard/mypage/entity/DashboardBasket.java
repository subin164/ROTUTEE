package com.greedy.rotutee.dashboard.mypage.entity;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.basket.entity
 * fileName : DashboardBasket
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */

@Entity(name = "dashboardBasket")
@Table(name = "TBL_CLASS_BASKET")
@SequenceGenerator(
        name = "DASHBOARD_BASKET_SEQ_GENERATOR",
        sequenceName = "CLASS_BASKET_NO",
        initialValue = 1,
        allocationSize = 1
)
public class DashboardBasket {

    @Id
    @Column(name = "CLASS_BASKET_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "DASHBOARD_BASKET_SEQ_GENERATOR"
    )
    private int basketNo;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Column(name = "LECTURE_NO")
    private int lectureNo;

    public DashboardBasket() {}

    public DashboardBasket(int basketNo, int memberNo, int lectureNo) {
        this.basketNo = basketNo;
        this.memberNo = memberNo;
        this.lectureNo = lectureNo;
    }

    public int getBasketNo() {
        return basketNo;
    }

    public void setBasketNo(int basketNo) {
        this.basketNo = basketNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(int lectureNo) {
        this.lectureNo = lectureNo;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "basketNo=" + basketNo +
                ", memberNo=" + memberNo +
                ", lectureNo=" + lectureNo +
                '}';
    }
}
