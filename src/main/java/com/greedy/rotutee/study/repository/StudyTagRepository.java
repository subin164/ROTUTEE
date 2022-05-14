package com.greedy.rotutee.study.repository;

import com.greedy.rotutee.study.entity.StudyByTag;
import com.greedy.rotutee.study.entity.StudyTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface StudyTagRepository.
 */
public interface StudyTagRepository extends JpaRepository<StudyTag, Integer> {

}
