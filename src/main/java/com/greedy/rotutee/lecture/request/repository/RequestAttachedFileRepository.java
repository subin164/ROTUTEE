package com.greedy.rotutee.lecture.request.repository;

import com.greedy.rotutee.lecture.request.entity.AttachedFile;
import com.greedy.rotutee.lecture.request.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestAttachedFileRepository extends JpaRepository<AttachedFile, Integer> {

}
