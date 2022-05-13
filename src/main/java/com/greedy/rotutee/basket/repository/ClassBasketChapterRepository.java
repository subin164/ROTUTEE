package com.greedy.rotutee.basket.repository;

import com.greedy.rotutee.basket.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.basket.repository
 * fileName : ClassBasketChapterRepository
 * author : soobeen
 * date : 2022-05-12
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-12          soobeen     최초 생성
 */

public interface ClassBasketChapterRepository extends JpaRepository<Chapter, Integer> {
    List<Chapter> findByLectureLectureNo(int lectureNo);
}
