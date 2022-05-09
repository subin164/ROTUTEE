package com.greedy.rotutee.member.tutorRequest.repository;

import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.tutorRequest.entity.TutorApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.repository
 * fileName : TutorRequestRepository
 * author : 7sang
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-22 7sang 최초 생성
 */

@Repository
public interface TutorApplyRepository extends JpaRepository<TutorApply, Integer> {

    TutorApply findTutorApplyByMember(Member member);

    Page<TutorApply> findByApplyYnNot(String status, Pageable pageable);

    Page<TutorApply> findByApplyYn(String status, Pageable pageable);

    Page<TutorApply> findByApplyYnNotAndMemberNo(String status, int memberNo, Pageable pageable);

    Page<TutorApply> findByMemberNo(int memberNo, Pageable pageable);


    Page<TutorApply> findByMemberEmailContaining(String searchValue, Pageable pageable);

    Page<TutorApply> findByMemberNameContaining(String searchValue, Pageable pageable);

    Page<TutorApply> findByApplyYnContaining(String searchValue, Pageable pageable);
}
