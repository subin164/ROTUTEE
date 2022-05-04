package com.greedy.rotutee.coupon.repository;

import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.coupon.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    List<Coupon> findByCouponStatus(String n);
}
