package com.greedy.rotutee.study.repository;


import com.greedy.rotutee.study.entity.Study;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StudyRepository extends JpaRepository<Study, Integer> {

  

    Page<Study> findByTitleContainingAndPostStatus(String searchCondition, String y, Pageable pageable);

    Page<Study> findByPostStatus(String n, Pageable pageable);
}
