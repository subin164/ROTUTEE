package com.greedy.rotutee.member.tutorRequest.repository;

import com.greedy.rotutee.member.tutorRequest.dto.TutorApplyDTO;
import com.greedy.rotutee.member.tutorRequest.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.repository
 * fileName : QualificationRepositroy
 * author : 7sang
 * date : 2022-04-23
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-23 7sang 최초 생성
 */


@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Integer> {
    List<Qualification> findByTutorApply(TutorApplyDTO tutorApply);
}
