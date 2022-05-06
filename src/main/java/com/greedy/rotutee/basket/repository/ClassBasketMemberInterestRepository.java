package com.greedy.rotutee.basket.repository;

import com.greedy.rotutee.basket.entity.Member;
import com.greedy.rotutee.basket.entity.LectureCategory;
import com.greedy.rotutee.basket.entity.MemberInterest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName      : com.greedy.rotutee.basket.repository
 * fileName         : ClassBasketMemberInterestRepository
 * author           : SEOK
 * date             : 2022-05-06
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-06      SEOK         최초 생성
 */
public interface ClassBasketMemberInterestRepository extends JpaRepository<MemberInterest, Integer> {
    MemberInterest findByMemberAndCategory(Member member, LectureCategory categoryEntity);
}
