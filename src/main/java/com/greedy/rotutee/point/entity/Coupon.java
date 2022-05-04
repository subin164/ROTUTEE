package com.greedy.rotutee.point.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.point.entity
 * fileName : Coupon
 * author : 7sang
 * date : 2022-05-02
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-02 7sang 최초 생성
 */

@Entity(name = "Point_Coupon")
@Table(name = "TBL_COUPON")
public class Coupon {

    @Id
    @Column(name = "COUPON_NO")
    private int couponNo;

    @Column(name = "COUPON_NAME")
    private String couponName;

    @Column(name = "COUPON_CONTENT")
    private String couponContent;

    @Column(name = "COUPON_REGISTRATION_DATE")
    private Date registrationDate;

    @Column(name = "COUPON_DISCOUNT_RATE")
    private int discountRate;

    @Column(name = "COUPON_EXPIRATION_DATE")
    private int expirationDate;

    @Column(name = "COUPON_ACTIVATION_YN")
    private String activationYn;

    public Coupon() {}

    public Coupon(int couponNo, String couponName, String couponContent, Date registrationDate, int discountRate, int expirationDate, String activationYn) {
        this.couponNo = couponNo;
        this.couponName = couponName;
        this.couponContent = couponContent;
        this.registrationDate = registrationDate;
        this.discountRate = discountRate;
        this.expirationDate = expirationDate;
        this.activationYn = activationYn;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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

    public String getActivationYn() {
        return activationYn;
    }

    public void setActivationYn(String activationYn) {
        this.activationYn = activationYn;
    }
}
