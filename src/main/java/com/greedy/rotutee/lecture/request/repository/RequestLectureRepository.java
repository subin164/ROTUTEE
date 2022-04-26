package com.greedy.rotutee.lecture.request.repository;

import com.greedy.rotutee.lecture.request.entity.Lecture;
import com.greedy.rotutee.lecture.request.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestLectureRepository extends JpaRepository<Lecture, Integer> {
    List<Lecture> findByTutor(Member tutor);
}