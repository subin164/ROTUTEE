package com.greedy.rotutee.coupon.entity;

import com.greedy.rotutee.member.member.entity.Member;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.point.entity
 * fileName : MemberCouponBox
 * author : 7sang
 * date : 2022-05-03
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-03 7sang 최초 생성
 */
@Entity(name = "MemberCouponBox")
@Table(name = "TBL_MEMBER_COUPON_BOX")
@SequenceGenerator(
        name = "COUPON_BOX_SEQ_GENERATOR",
        sequenceName = "COUPON_BOX_NO",
        initialValue = 1,
        allocationSize = 1
)
public class MemberCouponBox {

    /**
     * The Coupon box no.
     */
    @Id
    @Column(name = "COUPON_BOX_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COUPON_BOX_SEQ_GENERATOR"
    )
    private int couponBoxNo;

    /**
     * The Expiration date.
     */
    @Column(name = "COUPON_EXPIRATION_DATE")
    private Date expirationDate;

    /**
     * The Receiving date.
     */
    @Column(name = "COUPON_RECEIVING_DATE")
    private Date receivingDate;

    /**
     * The Coupon.
     */
    @ManyToOne
    @JoinColumn(name = "COUPON_NO")
    private Coupon coupon;

    /**
     * The Member.
     */
    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    /**
     * The Coupon status.
     */
    @Column(name = "COUPON_STATUS")
    private String couponStatus;

    /**
     * Instantiates a new Member coupon box.
     */
    public MemberCouponBox() {
    }

    /**
     * Instantiates a new Member coupon box.
     *
     * @param couponBoxNo    the coupon box no
     * @param expirationDate the expiration date
     * @param receivingDate  the receiving date
     * @param coupon         the coupon
     * @param member         the member
     * @param couponStatus   the coupon status
     */
    public MemberCouponBox(int couponBoxNo, Date expirationDate, Date receivingDate, Coupon coupon, Member member, String couponStatus) {
        this.couponBoxNo = couponBoxNo;
        this.expirationDate = expirationDate;
        this.receivingDate = receivingDate;
        this.coupon = coupon;
        this.member = member;
        this.couponStatus = couponStatus;
    }

    /**
     * Gets coupon box no.
     * author : SeoYoung Kim
     * description :
     *
     * @return the coupon box no
     */
    public int getCouponBoxNo() {
        return couponBoxNo;
    }

    /**
     * Sets coupon box no.
     *
     * @param couponBoxNo the coupon box no
     */
    public void setCouponBoxNo(int couponBoxNo) {
        this.couponBoxNo = couponBoxNo;
    }

    /**
     * Gets expiration date.
     * author : SeoYoung Kim
     * description :
     *
     * @return the expiration date
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets expiration date.
     *
     * @param expirationDate the expiration date
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets receiving date.
     * author : SeoYoung Kim
     * description :
     *
     * @return the receiving date
     */
    public Date getReceivingDate() {
        return receivingDate;
    }

    /**
     * Sets receiving date.
     *
     * @param receivingDate the receiving date
     */
    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }

    /**
     * Gets coupon.
     * author : SeoYoung Kim
     * description :
     *
     * @return the coupon
     */
    public Coupon getCoupon() {
        return coupon;
    }

    /**
     * Sets coupon.
     *
     * @param coupon the coupon
     */
    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    /**
     * Gets member.
     * author : SeoYoung Kim
     * description :
     *
     * @return the member
     */
    public Member getMember() {
        return member;
    }

    /**
     * Sets member.
     *
     * @param member the member
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * Gets coupon status.
     * author : SeoYoung Kim
     * description :
     *
     * @return the coupon status
     */
    public String getCouponStatus() {
        return couponStatus;
    }

    /**
     * Sets coupon status.
     *
     * @param couponStatus the coupon status
     */
    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
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
        return "MemberCouponBox{" +
                "couponBoxNo=" + couponBoxNo +
                ", expirationDate=" + expirationDate +
                ", receivingDate=" + receivingDate +
                ", coupon=" + coupon +
                ", member=" + member +
                ", couponStatus='" + couponStatus + '\'' +
                '}';
    }
}
