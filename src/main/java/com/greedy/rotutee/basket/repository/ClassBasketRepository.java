package com.greedy.rotutee.basket.repository;

import com.greedy.rotutee.basket.entity.ClassBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName      : com.greedy.rotutee.basket.repository
 * fileName         : ClassBasketRepository
 * author           : SEOK
 * date             : 2022-05-03
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-03      SEOK         최초 생성
 */
@Repository
public interface ClassBasketRepository extends JpaRepository<ClassBasket, Integer> {

    List<ClassBasket> findByMemberNo(int memberNo);
}
