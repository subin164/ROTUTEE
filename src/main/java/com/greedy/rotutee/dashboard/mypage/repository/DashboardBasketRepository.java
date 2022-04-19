package com.greedy.rotutee.dashboard.mypage.repository;

import com.greedy.rotutee.dashboard.mypage.dto.DashboardBasketDTO;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardBasket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.basket.repository
 * fileName : BasketRepository
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */


public interface DashboardBasketRepository extends JpaRepository<DashboardBasket, Integer> {

    List<DashboardBasket> findBymemberNo(int memberNo);
}
