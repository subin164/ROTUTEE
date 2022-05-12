package com.greedy.rotutee.basket.repository;

import com.greedy.rotutee.basket.entity.BasketCoupon;
import com.greedy.rotutee.basket.entity.MemberInterest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.greedy.rotutee.basket.repository
 * fileName : ClassBasketCouponRepository
 * author : soobeen
 * date : 2022-05-12
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-12          soobeen     최초 생성
 */

public interface ClassBasketCouponRepository extends JpaRepository<BasketCoupon, Integer> {

}
