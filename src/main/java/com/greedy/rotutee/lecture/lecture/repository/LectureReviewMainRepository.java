package com.greedy.rotutee.lecture.lecture.repository;

import com.greedy.rotutee.lecture.lecture.entity.LectureReview;
import com.greedy.rotutee.lecture.lecture.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureReviewMainRepository extends JpaRepository<LectureReview, Integer> {

    @Query(value = "select a from Lecture_LectureReview a where a.lectureNo = :lectureNo and a.lectureReviewRemoveYN = 'N'", nativeQuery = false)
    List<LectureReview> findLectureReviewByLectureNoAndLectureReviewRemoveYN(@Param("lectureNo") int lectureNo);

    @Query(value = "select avg(a.lectureGrade) from Lecture_LectureReview a where a.lectureNo = :lectureNo", nativeQuery = false)
    Integer findfindGradeAverageByLectureNo(@Param("lectureNo") int lectureNo);

    List<LectureReview> findByWriterAndLectureNo(Member memberEntity, int lectureNo);
}
