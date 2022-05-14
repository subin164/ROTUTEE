package com.greedy.rotutee.study.repository;

import com.greedy.rotutee.study.entity.StudyByTag;
import com.greedy.rotutee.study.entity.StudyTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface StudyByTagRepository.
 */
public interface StudyByTagRepository extends JpaRepository<StudyByTag, Integer>{


    /**
     * methodName : findByStudyStudyNo
     * author : SeoYoung Kim
     * description :
     *
     * @param no
     * @return list
     */
    List<StudyByTag> findByStudyStudyNo(int no);

}
