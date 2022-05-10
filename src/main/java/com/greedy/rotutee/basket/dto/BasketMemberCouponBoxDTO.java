package com.greedy.rotutee.basket.dto;

import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.basket.dto
 * fileName : BasketMemberCouponBoxDTO
 * author : soobeen
 * date : 2022-05-09
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-09          soobeen     최초 생성
 */

public class BasketMemberCouponBoxDTO {
    private int couponBoxNo;
    private Date couponExpirationDate;
    private Date couponRecevingDate;
    private BasketCouponDTO coupon;
    private MemberDTO member;
    private String couponStatus;

    public BasketMemberCouponBoxDTO() {}

    public BasketMemberCouponBoxDTO(int couponBoxNo, Date couponExpirationDate, Date couponRecevingDate, BasketCouponDTO coupon, MemberDTO member, String couponStatus) {
        this.couponBoxNo = couponBoxNo;
        this.couponExpirationDate = couponExpirationDate;
        this.couponRecevingDate = couponRecevingDate;
        this.coupon = coupon;
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

    public BasketCouponDTO getCoupon() {
        return coupon;
    }

    public void setCoupon(BasketCouponDTO coupon) {
        this.coupon = coupon;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
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
        return "BasketMemberCouponBoxDTO{" +
                "couponBoxNo=" + couponBoxNo +
                ", couponExpirationDate=" + couponExpirationDate +
                ", couponRecevingDate=" + couponRecevingDate +
                ", coupon=" + coupon +
//                ", member=" + member +
                ", couponStatus='" + couponStatus + '\'' +
                '}';
    }
}
