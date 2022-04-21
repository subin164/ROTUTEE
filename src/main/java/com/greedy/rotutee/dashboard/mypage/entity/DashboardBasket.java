package com.greedy.rotutee.dashboard.mypage.entity;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private DashboardMember member;

    @ManyToOne
    @JoinColumn(name = "LECTURE_NO")
    private DashboardLecture lecture;

    public DashboardBasket() {}

    public DashboardBasket(int basketNo, DashboardMember member, DashboardLecture lecture) {
        this.basketNo = basketNo;
        this.member = member;
        this.lecture = lecture;
    }

    public int getBasketNo() {
        return basketNo;
    }

    public void setBasketNo(int basketNo) {
        this.basketNo = basketNo;
    }

    public DashboardMember getMember() {
        return member;
    }

    public void setMember(DashboardMember member) {
        this.member = member;
    }

    public DashboardLecture getLecture() {
        return lecture;
    }

    public void setLecture(DashboardLecture lecture) {
        this.lecture = lecture;
    }

    @Override
    public String toString() {
        return "DashboardBasket{" +
                "basketNo=" + basketNo +
                ", member=" + member +
                ", lecture=" + lecture +
                '}';
    }
}
