package com.greedy.rotutee.coupon.repository;

import com.greedy.rotutee.coupon.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface CouponRepository.
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    /**
     * methodName : findByPublishCouponStatus
     * author : SeoYoung Kim
     * description :
     *
     * @param n
     * @param pageable
     * @return page
     */
    Page<Coupon> findByPublishCouponStatus(String n_, Pageable pageable);
}
