package com.greedy.rotutee.lecture.request.repository;

import com.greedy.rotutee.lecture.request.entity.LectureCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName      : com.greedy.rotutee.lecture.request.repository
 * fileName         : RequestLectureCategoryRepository
 * author           : SEOK
 * date             : 2022-04-26
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-04-26      SEOK         최초 생성
 */
@Repository
public interface RequestLectureCategoryRepository extends JpaRepository<LectureCategory, Integer> {
    LectureCategory findByLectureCategoryNo(int categoryNo);

    LectureCategory findByLectureCategoryName(String searchValue);
}
