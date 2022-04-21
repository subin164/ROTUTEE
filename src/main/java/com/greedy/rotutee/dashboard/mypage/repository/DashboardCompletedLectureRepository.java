package com.greedy.rotutee.dashboard.mypage.repository;

import com.greedy.rotutee.dashboard.mypage.entity.DashboardCompletedLecture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.repository
 * fileName : DashboardCompletedLectureRepository
 * author : SeoYoung
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 SeoYoung 최초 생성
 */
public interface DashboardCompletedLectureRepository extends JpaRepository<DashboardCompletedLecture, Integer> {
}
