package com.greedy.rotutee.lecture.request.repository;

import com.greedy.rotutee.lecture.request.entity.AttachedFile;
import com.greedy.rotutee.lecture.request.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestAttachedFileRepository extends JpaRepository<AttachedFile, Integer> {
    AttachedFile findByLectureAndFileDeletionYNAndDivision(Lecture lectureEntity, String deletion, String division);

//    @Query("select a from Request_AttachedFile a where a.lecture = :lectureNo and a.fileDeletionYN = 'N' and a.division = '강의'")
//    AttachedFile findLectureThumbnail(int lectureNo);

}
