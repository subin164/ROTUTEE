package com.greedy.rotutee.lecture.lecture.repository;

import com.greedy.rotutee.lecture.lecture.entity.MemberLecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLectureMainRepository extends JpaRepository<MemberLecture, Integer> {
    int countByLectureNo(int lectureNo);
}
