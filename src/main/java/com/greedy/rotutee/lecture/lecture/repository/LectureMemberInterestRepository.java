package com.greedy.rotutee.lecture.lecture.repository;

import com.greedy.rotutee.lecture.lecture.entity.LectureCategory;
import com.greedy.rotutee.lecture.lecture.entity.Member;
import com.greedy.rotutee.lecture.lecture.entity.MemberInterest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName      : com.greedy.rotutee.lecture.lecture.repository
 * fileName         : LectureMemberInterestRepository
 * author           : SEOK
 * date             : 2022-05-06
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-06      SEOK         최초 생성
 */
public interface LectureMemberInterestRepository extends JpaRepository<MemberInterest, Integer> {
    MemberInterest findByMemberAndCategory(Member member, LectureCategory categoryEntity);
}
