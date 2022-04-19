package com.greedy.rotutee.lecture.lecture.repository;

import com.greedy.rotutee.lecture.lecture.entity.Lecture;
import com.greedy.rotutee.lecture.lecture.service.LectureMainService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureMainRepository extends JpaRepository<Lecture, Integer> {


    List<Lecture> findBylectureApprovalStatus(String lectureApprovalStatus);
}
