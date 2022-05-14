package com.greedy.rotutee.study.repository;


import com.greedy.rotutee.study.entity.Study;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The interface StudyRepository.
 */
public interface StudyRepository extends JpaRepository<Study, Integer> {


    /**
     * methodName : findByTitleContainingAndPostStatus
     * author : SeoYoung Kim
     * description :
     *
     * @param search   condition
     * @param y
     * @param pageable
     * @return page
     */
    Page<Study> findByTitleContainingAndPostStatus(String searchCondition, String y, Pageable pageable);

    /**
     * methodName : findByPostStatus
     * author : SeoYoung Kim
     * description :
     *
     * @param n
     * @param pageable
     * @return page
     */
    Page<Study> findByPostStatus(String n, Pageable pageable);
}
