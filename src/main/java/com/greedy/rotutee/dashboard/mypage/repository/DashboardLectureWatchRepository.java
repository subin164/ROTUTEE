package com.greedy.rotutee.dashboard.mypage.repository;

import com.greedy.rotutee.dashboard.mypage.entity.DashboardLecture;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardLectureWatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.repository
 * fileName : DashboardLectureWatchRepository
 * author : SeoYoung
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 SeoYoung 최초 생성
 */
public interface DashboardLectureWatchRepository extends JpaRepository<DashboardLectureWatch, Integer> {
 List<DashboardLectureWatch> findBymemberNo(int memberNo);
}
