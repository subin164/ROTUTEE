package com.greedy.rotutee.member.tutorRequest.repository;

import com.greedy.rotutee.member.tutorRequest.dto.TutorApplyDTO;
import com.greedy.rotutee.member.tutorRequest.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.repository
 * fileName : CareerRepository
 * author : 7sang
 * date : 2022-04-23
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-23 7sang 최초 생성
 */

@Repository
public interface CareerRepository extends JpaRepository<Career, Integer> {
    List<Career> findByTutorApply(TutorApplyDTO tutorApply);
}
