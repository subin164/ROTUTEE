package com.greedy.rotutee.main.controller.member.repository;

import com.greedy.rotutee.main.controller.member.entity.LectureCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureCategoryRepository extends JpaRepository<LectureCategory, Integer> {

}
