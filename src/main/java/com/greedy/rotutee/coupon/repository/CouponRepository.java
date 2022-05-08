package com.greedy.rotutee.coupon.repository;

import com.greedy.rotutee.coupon.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    Page<Coupon> findByCouponStatus(String n_, Pageable pageable);
}
