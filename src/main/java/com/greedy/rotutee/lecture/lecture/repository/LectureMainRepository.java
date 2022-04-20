package com.greedy.rotutee.lecture.lecture.repository;

import com.greedy.rotutee.lecture.lecture.entity.Lecture;
import com.greedy.rotutee.lecture.lecture.service.LectureMainService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureMainRepository extends JpaRepository<Lecture, Integer> {


    List<Lecture> findBylectureApprovalStatus(String lectureApprovalStatus);

    List<Lecture> findBylectureNameContaining(String searchValue);

    @Query(value = "select a from Lecture_Lecture a join Lecture_Member b on (a.tutor.no = b.no) where a.lectureApprovalStatus = '승인' and b.name = :searchValue", nativeQuery = true)
    List<Lecture> findLecturesByTutorName(@Param("searchValue") String searchValue);
}
