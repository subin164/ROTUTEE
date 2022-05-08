package com.greedy.rotutee.coupon.repository;

import com.greedy.rotutee.coupon.entity.MemberCouponBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCouponBoxRepository extends JpaRepository<MemberCouponBox, Integer> {
}
