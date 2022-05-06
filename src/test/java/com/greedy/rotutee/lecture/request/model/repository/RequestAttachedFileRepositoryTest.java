package com.greedy.rotutee.lecture.request.model.repository;

import com.greedy.rotutee.lecture.request.entity.AttachedFile;
import com.greedy.rotutee.lecture.request.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestAttachedFileRepositoryTest extends JpaRepository<AttachedFile, Integer> {

    AttachedFile findByLectureAndFileDeletionYNAndDivision(Lecture lectureEntity, String deletion, String division);

}
