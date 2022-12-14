package com.greedy.rotutee.basket.entity;

import javax.persistence.*;

/**
 * packageName      : com.greedy.rotutee.basket.entity
 * fileName         : ClassBasket
 * author           : SEOK
 * date             : 2022-05-03
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-03      SEOK         최초 생성
 */
@Entity(name = "Basket_ClassBasket")
@Table(name = "TBL_CLASS_BASKET")
@SequenceGenerator(
        name = "BASKET_CLASS_BASKET_SEQ_GENERATOR",
        sequenceName = "CLASS_BASKET_NO",
        allocationSize = 1,
        initialValue = 1
)
public class ClassBasket {

    @Id
    @Column(name = "CLASS_BASKET_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BASKET_CLASS_BASKET_SEQ_GENERATOR"
    )
    private int classBasketNo;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "LECTURE_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lecture lecture;

    @JoinColumn(name = "COUPON_NO")
    @ManyToOne(fetch= FetchType.LAZY)
    private BasketCoupon coupon;

    public ClassBasket() {
    }

    public ClassBasket(int classBasketNo, Member member, Lecture lecture,BasketCoupon coupon) {
        this.classBasketNo = classBasketNo;
        this.member = member;
        this.lecture = lecture;
        this.coupon = coupon;
    }


    public int getClassBasketNo() {
        return classBasketNo;
    }

    public void setClassBasketNo(int classBasketNo) {
        this.classBasketNo = classBasketNo;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public BasketCoupon getCoupon() { return coupon; }

    public void setCoupon(BasketCoupon coupon) { this.coupon = coupon; }

    @Override
    public String toString() {
        return "ClassBasket{" +
                "classBasketNo=" + classBasketNo +
                ", member=" + member +
                ", lecture=" + lecture +
                ", coupon=" + coupon+
                '}';
    }

}
