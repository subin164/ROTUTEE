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

    @Id
    @Column(name = "COUPON_BOX_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COUPON_BOX_SEQ_GENERATOR"
    )
    private int couponBoxNo;

    @Column(name = "COUPON_EXPIRATION_DATE")
    private Date expirationDate;

    @Column(name = "COUPON_RECEIVING_DATE")
    private Date receivingDate;

    @ManyToOne
    @JoinColumn(name = "COUPON_NO")
    private Coupon coupon;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @Column(name = "COUPON_STATUS")
    private String status;

    public MemberCouponBox() {
    }

    public MemberCouponBox(int couponBoxNo, Date expirationDate, Date receivingDate, Coupon coupon, Member member, String status) {
        this.couponBoxNo = couponBoxNo;
        this.expirationDate = expirationDate;
        this.receivingDate = receivingDate;
        this.coupon = coupon;
        this.member = member;
        this.status = status;
    }

    public int getCouponBoxNo() {
        return couponBoxNo;
    }

    public void setCouponBoxNo(int couponBoxNo) {
        this.couponBoxNo = couponBoxNo;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MemberCouponBox{" +
                "couponBoxNo=" + couponBoxNo +
                ", expirationDate=" + expirationDate +
                ", receivingDate=" + receivingDate +
                ", coupon=" + coupon +
                ", member=" + member +
                ", status='" + status + '\'' +
                '}';
    }
}
