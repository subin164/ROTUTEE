package com.greedy.rotutee.lecture.lecture.repository;

import com.greedy.rotutee.lecture.lecture.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    List<Chapter> findByLectureNo(int lectureNo);
}
