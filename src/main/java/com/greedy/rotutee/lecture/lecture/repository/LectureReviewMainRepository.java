package com.greedy.rotutee.lecture.lecture.repository;

import com.greedy.rotutee.lecture.lecture.entity.LectureReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureReviewMainRepository extends JpaRepository<LectureReview, Integer> {

    List<LectureReview> findLectureReviewByLectureNoAndLectureReviewRemoveYN(int lectureNo, String status);

    @Query("select avg(a.lectureGrade) from Lecture_LectureReview a where a.lectureNo = :lectureNo")
    Integer findfindGradeAverageByLectureNo(int lectureNo);
}
