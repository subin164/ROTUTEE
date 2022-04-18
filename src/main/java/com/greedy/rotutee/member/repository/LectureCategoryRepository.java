package com.greedy.rotutee.member.repository;

import com.greedy.rotutee.member.entity.LectureCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureCategoryRepository extends JpaRepository<LectureCategory, Integer> {

}
