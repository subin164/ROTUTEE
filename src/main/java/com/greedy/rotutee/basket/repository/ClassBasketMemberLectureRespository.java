package com.greedy.rotutee.basket.repository;

import com.greedy.rotutee.basket.entity.Lecture;
import com.greedy.rotutee.basket.entity.Member;
import com.greedy.rotutee.basket.entity.MemberLecture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.greedy.rotutee.basket.repository
 * fileName : ClassBasketMemberLectureRespository
 * author : soobeen
 * date : 2022-05-12
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-12          soobeen     최초 생성
 */

public interface ClassBasketMemberLectureRespository extends JpaRepository<MemberLecture, Integer> {
    Lecture findByLectureNo(int lectureNo);

    Member findByNo(int memberNo);

    MemberLecture findByMemberNoAndLectureNo(int memberNo, int lectureNo);
}
