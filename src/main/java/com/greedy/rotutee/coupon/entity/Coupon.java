package com.greedy.rotutee.coupon.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * The type Coupon.
 */
@Entity
@Table (name="TBL_COUPON")
@SequenceGenerator(
        name="COUPON_NO_GENERATOR",
        sequenceName = "COUPON_NO",
        initialValue = 1,
        allocationSize = 1
)

public class Coupon {
    /**
     * The Publish coupon no.
     */
    @Id
    @Column(name = "COUPON_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COUPON_NO_GENERATOR"
    )
    private int publishCouponNo;

    /**
     * The Publish coupon name.
     */
    @Column(name = "COUPON_NAME")
    private String publishCouponName;

    /**
     * The Publish coupon content.
     */
    @Column(name = "COUPON_CONTENT")
    private String publishCouponContent;

    /**
     * The Publish coupon regist date.
     */
    @Column(name = "COUPON_REGISTRATION_DATE")
    private java.sql.Date publishCouponRegistDate;

    /**
     * The Discount rate.
     */
    @Column(name = "COUPON_DISCOUNT_RATE")
    private int discountRate;

    /**
     * The Expiration date.
     */
    @Column(name = "COUPON_EXPIRATION_DATE")
    private int expirationDate;

    /**
     * The Publish coupon status.
     */
    @Column(name = "COUPON_ACTIVATION_YN")
    private String publishCouponStatus;

    /**
     * Instantiates a new Coupon.
     */
    public Coupon() {
    }

    /**
     * Instantiates a new Coupon.
     *
     * @param publishCouponNo         the publish coupon no
     * @param publishCouponName       the publish coupon name
     * @param publishCouponContent    the publish coupon content
     * @param publishCouponRegistDate the publish coupon regist date
     * @param discountRate            the discount rate
     * @param expirationDate          the expiration date
     * @param publishCouponStatus     the publish coupon status
     */
    public Coupon(int publishCouponNo, String publishCouponName, String publishCouponContent, Date publishCouponRegistDate, int discountRate, int expirationDate, String publishCouponStatus) {
        this.publishCouponNo = publishCouponNo;
        this.publishCouponName = publishCouponName;
        this.publishCouponContent = publishCouponContent;
        this.publishCouponRegistDate = publishCouponRegistDate;
        this.discountRate = discountRate;
        this.expirationDate = expirationDate;
        this.publishCouponStatus = publishCouponStatus;
    }

    /**
     * Gets publish coupon no.
     * author : SeoYoung Kim
     * description :
     *
     * @return the publish coupon no
     */
    public int getPublishCouponNo() {
        return publishCouponNo;
    }

    /**
     * Sets publish coupon no.
     *
     * @param publishCouponNo the publish coupon no
     */
    public void setPublishCouponNo(int publishCouponNo) {
        this.publishCouponNo = publishCouponNo;
    }

    /**
     * Gets publish coupon name.
     * author : SeoYoung Kim
     * description :
     *
     * @return the publish coupon name
     */
    public String getPublishCouponName() {
        return publishCouponName;
    }

    /**
     * Sets publish coupon name.
     *
     * @param publishCouponName the publish coupon name
     */
    public void setPublishCouponName(String publishCouponName) {
        this.publishCouponName = publishCouponName;
    }

    /**
     * Gets publish coupon content.
     * author : SeoYoung Kim
     * description :
     *
     * @return the publish coupon content
     */
    public String getPublishCouponContent() {
        return publishCouponContent;
    }

    /**
     * Sets publish coupon content.
     *
     * @param publishCouponContent the publish coupon content
     */
    public void setPublishCouponContent(String publishCouponContent) {
        this.publishCouponContent = publishCouponContent;
    }

    /**
     * Gets publish coupon regist date.
     * author : SeoYoung Kim
     * description :
     *
     * @return the publish coupon regist date
     */
    public Date getPublishCouponRegistDate() {
        return publishCouponRegistDate;
    }

    /**
     * Sets publish coupon regist date.
     *
     * @param publishCouponRegistDate the publish coupon regist date
     */
    public void setPublishCouponRegistDate(Date publishCouponRegistDate) {
        this.publishCouponRegistDate = publishCouponRegistDate;
    }

    /**
     * Gets discount rate.
     * author : SeoYoung Kim
     * description :
     *
     * @return the discount rate
     */
    public int getDiscountRate() {
        return discountRate;
    }

    /**
     * Sets discount rate.
     *
     * @param discountRate the discount rate
     */
    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * Gets expiration date.
     * author : SeoYoung Kim
     * description :
     *
     * @return the expiration date
     */
    public int getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets expiration date.
     *
     * @param expirationDate the expiration date
     */
    public void setExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets publish coupon status.
     * author : SeoYoung Kim
     * description :
     *
     * @return the publish coupon status
     */
    public String getPublishCouponStatus() {
        return publishCouponStatus;
    }

    /**
     * Sets publish coupon status.
     *
     * @param publishCouponStatus the publish coupon status
     */
    public void setPublishCouponStatus(String publishCouponStatus) {
        this.publishCouponStatus = publishCouponStatus;
    }

    /**
     * methodName : toString
     * author : SeoYoung Kim
     * description :
     *
     * @return string
     */
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
