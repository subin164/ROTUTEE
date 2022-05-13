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
    private int publishCouponNo;

    @Column(name = "COUPON_NAME")
    private String publishCouponName;

    @Column(name = "COUPON_CONTENT")
    private String publishCouponContent;

    @Column(name = "COUPON_REGISTRATION_DATE")
    private java.sql.Date publishCouponRegistDate;

    @Column(name = "COUPON_DISCOUNT_RATE")
    private int discountRate;

    @Column(name = "COUPON_EXPIRATION_DATE")
    private int expirationDate;

    @Column(name = "COUPON_ACTIVATION_YN")
    private String publishCouponStatus;

    public Coupon() {
    }

    public Coupon(int publishCouponNo, String publishCouponName, String publishCouponContent, Date publishCouponRegistDate, int discountRate, int expirationDate, String publishCouponStatus) {
        this.publishCouponNo = publishCouponNo;
        this.publishCouponName = publishCouponName;
        this.publishCouponContent = publishCouponContent;
        this.publishCouponRegistDate = publishCouponRegistDate;
        this.discountRate = discountRate;
        this.expirationDate = expirationDate;
        this.publishCouponStatus = publishCouponStatus;
    }

    public int getPublishCouponNo() {
        return publishCouponNo;
    }

    public void setPublishCouponNo(int publishCouponNo) {
        this.publishCouponNo = publishCouponNo;
    }

    public String getPublishCouponName() {
        return publishCouponName;
    }

    public void setPublishCouponName(String publishCouponName) {
        this.publishCouponName = publishCouponName;
    }

    public String getPublishCouponContent() {
        return publishCouponContent;
    }

    public void setPublishCouponContent(String publishCouponContent) {
        this.publishCouponContent = publishCouponContent;
    }

    public Date getPublishCouponRegistDate() {
        return publishCouponRegistDate;
    }

    public void setPublishCouponRegistDate(Date publishCouponRegistDate) {
        this.publishCouponRegistDate = publishCouponRegistDate;
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

    public String getPublishCouponStatus() {
        return publishCouponStatus;
    }

    public void setPublishCouponStatus(String publishCouponStatus) {
        this.publishCouponStatus = publishCouponStatus;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "publishCouponNo=" + publishCouponNo +
                ", publishCouponName='" + publishCouponName + '\'' +
                ", publishCouponContent='" + publishCouponContent + '\'' +
                ", publishCouponRegistDate=" + publishCouponRegistDate +
                ", discountRate=" + discountRate +
                ", expirationDate=" + expirationDate +
                ", publishCouponStatus='" + publishCouponStatus + '\'' +
                '}';
    }
}
