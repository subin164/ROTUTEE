package com.greedy.rotutee.basket.repository;

import com.greedy.rotutee.basket.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName      : com.greedy.rotutee.basket.repository
 * fileName         : ClassBasketLectureRepository
 * author           : SEOK
 * date             : 2022-05-04
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-04      SEOK         최초 생성
 */
@Repository
public interface ClassBasketLectureRepository extends JpaRepository<Lecture, Integer> {
    Lecture findByLectureNo(int lectureNo);
}
