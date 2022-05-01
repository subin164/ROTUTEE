package com.greedy.rotutee.dashboard.mypage.repository;

import com.greedy.rotutee.dashboard.mypage.entity.DashboardLecture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.repository
 * fileName : LectureRepository
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */


public interface DashboardLectureRepository extends JpaRepository<DashboardLecture, Integer> {
    DashboardLecture findBymemberNo(int memberNo);

    DashboardLecture findByLectureNo(int lectureNo);
}
