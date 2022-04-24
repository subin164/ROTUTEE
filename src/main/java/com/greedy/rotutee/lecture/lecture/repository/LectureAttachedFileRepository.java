package com.greedy.rotutee.lecture.lecture.repository;

import com.greedy.rotutee.lecture.lecture.entity.AttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LectureAttachedFileRepository extends JpaRepository<AttachedFile, Integer> {
    @Query("select a.thumbnailFilePath from Lecture_AttachedFile a where a.lectureNo = :lectureNo and a.fileDeletionYN = 'N' and a.division = '강의'")
    String findThumbnailPathBylectureNo(int lectureNo);
}
