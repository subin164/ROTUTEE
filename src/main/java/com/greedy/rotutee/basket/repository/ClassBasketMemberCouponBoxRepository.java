package com.greedy.rotutee.basket.repository;

import com.greedy.rotutee.basket.entity.BasketCoupon;
import com.greedy.rotutee.basket.entity.BasketMemberCouponBox;
import com.greedy.rotutee.basket.entity.Member;
import com.greedy.rotutee.basket.entity.MemberInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.basket.repository
 * fileName : ClassBasketMemberCouponBoxRepository
 * author : soobeen
 * date : 2022-05-09
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-09          soobeen     최초 생성
 */

public interface ClassBasketMemberCouponBoxRepository extends JpaRepository<BasketMemberCouponBox, Integer> {

    List<BasketMemberCouponBox> findByMemberNoAndCouponStatus(int memberNo, String couponStatus);

    BasketMemberCouponBox findByBasketCouponAndMember(BasketCoupon basketCoupon, Member memberEntity);
}
