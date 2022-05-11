package com.greedy.rotutee.study.repository;

import com.greedy.rotutee.study.entity.StudyByTag;
import com.greedy.rotutee.study.entity.StudyTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyByTagRepository extends JpaRepository<StudyByTag, Integer>{


    List<StudyByTag> findByStudyStudyNo(int no);

}
