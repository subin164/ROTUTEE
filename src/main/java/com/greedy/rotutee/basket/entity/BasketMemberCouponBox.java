package com.greedy.rotutee.basket.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.basket.entity
 * fileName : BasketMemberCouponBox
 * author : soobeen
 * date : 2022-05-09
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-09          soobeen     최초 생성
 */

@Entity(name = "BasketMemberCouponBox")
@Table(name = "TBL_MEMBER_COUPON_BOX")
@SequenceGenerator(
        name = "BASKET_COUPON_BOX_SEQ_GENERATOR",
        sequenceName = "ANSWER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class BasketMemberCouponBox {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BASKET_COUPON_BOX_SEQ_GENERATOR"
    )
    @Column(name = "COUPON_BOX_NO")
    private int couponBoxNo;

    @Column(name = "COUPON_EXPIRATION_DATE")
    private Date couponExpirationDate;

    @Column(name = "COUPON_RECEVING_DATE")
    private Date couponRecevingDate;


    @JoinColumn(name = "COUPON_NO")
    @ManyToOne
    private BasketCoupon basketCoupon;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne
    private Member member;

    @Column(name = "COUPON_STATUS")
    private String couponStatus;

    public BasketMemberCouponBox() {
    }

    public BasketMemberCouponBox(int couponBoxNo, Date couponExpirationDate, Date couponRecevingDate
                                    , BasketCoupon basketCoupon, Member member, String couponStatus) {
        this.couponBoxNo = couponBoxNo;
        this.couponExpirationDate = couponExpirationDate;
        this.couponRecevingDate = couponRecevingDate;
        this.basketCoupon = basketCoupon;
        this.member = member;
        this.couponStatus = couponStatus;
    }

    public int getCouponBoxNo() {
        return couponBoxNo;
    }

    public void setCouponBoxNo(int couponBoxNo) {
        this.couponBoxNo = couponBoxNo;
    }

    public Date getCouponExpirationDate() {
        return couponExpirationDate;
    }

    public void setCouponExpirationDate(Date couponExpirationDate) {
        this.couponExpirationDate = couponExpirationDate;
    }

    public Date getCouponRecevingDate() {
        return couponRecevingDate;
    }

    public void setCouponRecevingDate(Date couponRecevingDate) {
        this.couponRecevingDate = couponRecevingDate;
    }

    public BasketCoupon getBasketCoupon() {
        return basketCoupon;
    }

    public void setBasketCoupon(BasketCoupon basketCoupon) {
        this.basketCoupon = basketCoupon;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    @Override
    public String toString() {
        return "BasketMemberCouponBox{" +
                "couponBoxNo=" + couponBoxNo +
                ", couponExpirationDate=" + couponExpirationDate +
                ", couponRecevingDate=" + couponRecevingDate +
                ", basketCoupon=" + basketCoupon +
                ", member=" + member +
                ", couponStatus='" + couponStatus + '\'' +
                '}';
    }
}
