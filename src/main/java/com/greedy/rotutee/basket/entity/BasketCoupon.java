package com.greedy.rotutee.basket.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.basket.entity
 * fileName : BasketCoupon
 * author : soobeen
 * date : 2022-05-09
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-09          soobeen     최초 생성
 */
@Entity(name = "BasketCoupon")
@Table(name = "TBL_COUPON")
@SequenceGenerator(
        name = "BASKET_COUPON_SEQ_GENERATOR",
        sequenceName = "ANSWER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class BasketCoupon {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BASKET_COUPON_SEQ_GENERATOR"
    )
    @Column(name = "COUPON_NO")
    private int couponNo ;

    @Column(name ="COUPON_NAME")
    private String couponName;

    @Column(name ="COUPON_CONTENT" )
    private String couponContent;

    @Column(name = "COUPON_REGISTRATION_DATE")
    private Date couponRegistrationDate;

    @Column(name = "COUPON_DISCOUNT_RATE")
    private int couponDiscountRate;

    @Column(name ="COUPON_EXPIRATION_DATE")
    private int couponExpirationDate;

    @Column(name = "COUPON_ACTICATION_YN")
    private String couponActivationYn;

    @Column(name = "COUPON_DELETION_DATE")
    private Date couponDeletionDate;

    @Column(name = "COUPON_MODIFIED_DATE")
    private Date couponModifiedDate;

    public BasketCoupon() {
    }

    public BasketCoupon(int couponNo, String couponName, String couponContent, Date couponRegistrationDate
                        , int couponDiscountRate, int couponExpirationDate, String couponActivationYn
                        , Date couponDeletionDate, Date couponModifiedDate) {
        this.couponNo = couponNo;
        this.couponName = couponName;
        this.couponContent = couponContent;
        this.couponRegistrationDate = couponRegistrationDate;
        this.couponDiscountRate = couponDiscountRate;
        this.couponExpirationDate = couponExpirationDate;
        this.couponActivationYn = couponActivationYn;
        this.couponDeletionDate = couponDeletionDate;
        this.couponModifiedDate = couponModifiedDate;
    }

    public int getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(int couponNo) {
        this.couponNo = couponNo;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponContent() {
        return couponContent;
    }

    public void setCouponContent(String couponContent) {
        this.couponContent = couponContent;
    }

    public Date getCouponRegistrationDate() {
        return couponRegistrationDate;
    }

    public void setCouponRegistrationDate(Date couponRegistrationDate) {
        this.couponRegistrationDate = couponRegistrationDate;
    }

    public int getCouponDiscountRate() {
        return couponDiscountRate;
    }

    public void setCouponDiscountRate(int couponDiscountRate) {
        this.couponDiscountRate = couponDiscountRate;
    }

    public int getCouponExpirationDate() {
        return couponExpirationDate;
    }

    public void setCouponExpirationDate(int couponExpirationDate) {
        this.couponExpirationDate = couponExpirationDate;
    }

    public String getCouponActivationYn() {
        return couponActivationYn;
    }

    public void setCouponActivationYn(String couponActivationYn) {
        this.couponActivationYn = couponActivationYn;
    }

    public Date getCouponDeletionDate() {
        return couponDeletionDate;
    }

    public void setCouponDeletionDate(Date couponDeletionDate) {
        this.couponDeletionDate = couponDeletionDate;
    }

    public Date getCouponModifiedDate() {
        return couponModifiedDate;
    }

    public void setCouponModifiedDate(Date couponModifiedDate) {
        this.couponModifiedDate = couponModifiedDate;
    }

    @Override
    public String toString() {
        return "BasketCoupon{" +
                "couponNo=" + couponNo +
                ", couponName='" + couponName + '\'' +
                ", couponContent='" + couponContent + '\'' +
                ", couponRegistrationDate=" + couponRegistrationDate +
                ", couponDiscountRate=" + couponDiscountRate +
                ", couponExpirationDate=" + couponExpirationDate +
                ", couponActivationYn='" + couponActivationYn + '\'' +
                ", couponDeletionDate=" + couponDeletionDate +
                ", couponModifiedDate=" + couponModifiedDate +
                '}';
    }
}
