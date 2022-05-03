package com.greedy.rotutee.basket.repository;

import com.greedy.rotutee.basket.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName      : com.greedy.rotutee.basket.repository
 * fileName         : ClassBasketMemberRepository
 * author           : SEOK
 * date             : 2022-05-04
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-04      SEOK         최초 생성
 */
@Repository
public interface ClassBasketMemberRepository extends JpaRepository<Member, Integer> {
    Member findByNo(int memberNo);
}
