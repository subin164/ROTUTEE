package com.greedy.rotutee.coupon.repository;

import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

}
