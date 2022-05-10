package com.greedy.rotutee.lecture.lecture.repository;

import com.greedy.rotutee.lecture.lecture.entity.LectureCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName      : com.greedy.rotutee.lecture.lecture.repository
 * fileName         : LectureCategoryRepository
 * author           : SEOK
 * date             : 2022-05-10
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-10      SEOK         최초 생성
 */
@Repository
public interface LectureCategoryMainRepository extends JpaRepository<LectureCategory, Integer> {

    LectureCategory findByLectureCategoryName(String categoryName);
}
