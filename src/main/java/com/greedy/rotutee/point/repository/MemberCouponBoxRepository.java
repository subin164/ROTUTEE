package com.greedy.rotutee.point.repository;

import com.greedy.rotutee.point.entity.MemberCouponBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.point.repository
 * fileName : MemberCouponBoxRepository
 * author : 7sang
 * date : 2022-05-03
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-03 7sang 최초 생성
 */

@Repository(value = "Point_MemberCouponBoxRepository")
public interface MemberCouponBoxRepository extends JpaRepository<MemberCouponBox, Integer> {
}
