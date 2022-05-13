package com.greedy.rotutee.dashboard.lms.repository;

import com.greedy.rotutee.dashboard.lms.entity.LMSChapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.repository
 * fileName : LMSChapterRepository
 * author : SeoYoung
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 SeoYoung 최초 생성
 */
public interface LMSChapterRepository extends JpaRepository<LMSChapter, Integer> {
    List<LMSChapter> findByLectureNo(int lectureNo);

    List<LMSChapter> findByLectureNoOrderByChapterNoAsc(int lectureNo);
}
