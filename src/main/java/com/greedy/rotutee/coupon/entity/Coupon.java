package com.greedy.rotutee.coupon.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table (name="TBL_COUPON")
@SequenceGenerator(
        name="COUPON_NO_GENERATOR",
        sequenceName = "COUPON_NO",
        initialValue = 1,
        allocationSize = 1
)

public class Coupon {
    @Id
    @Column(name = "COUPON_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COUPON_NO_GENERATOR"
    )
    private int couponNo;

    @Column(name = "COUPON_NAME")
    private String couponName;

    @Column(name = "COUPON_CONTENT")
    private String couponContent;

    @Column(name = "COUPON_REGISTRATION_DATE")
    private java.sql.Date couponRegistDate;

    @Column(name = "COUPON_DISCOUNT_RATE")
    private int discountRate;

    @Column(name = "COUPON_EXPIRATION_DATE")
    private int expirationDate;

    @Column(name = "COUPON_ACTIVATION_YN")
    private String couponStatus;

    public Coupon() {
    }

    public Coupon(int couponNo, String couponName, String couponContent, Date couponRegistDate, int discountRate, int expirationDate, String couponStatus) {
        this.couponNo = couponNo;
        this.couponName = couponName;
        this.couponContent = couponContent;
        this.couponRegistDate = couponRegistDate;
        this.discountRate = discountRate;
        this.expirationDate = expirationDate;
        this.couponStatus = couponStatus;
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

    public Date getCouponRegistDate() {
        return couponRegistDate;
    }

    public void setCouponRegistDate(Date couponRegistDate) {
        this.couponRegistDate = couponRegistDate;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "couponNo=" + couponNo +
                ", couponName='" + couponName + '\'' +
                ", couponContent='" + couponContent + '\'' +
                ", couponRegistDate=" + couponRegistDate +
                ", discountRate=" + discountRate +
                ", expirationDate=" + expirationDate +
                ", couponStatus='" + couponStatus + '\'' +
                '}';
    }
}
