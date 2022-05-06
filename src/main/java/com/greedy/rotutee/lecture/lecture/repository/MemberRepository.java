package com.greedy.rotutee.lecture.lecture.repository;

import com.greedy.rotutee.lecture.lecture.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName      : com.greedy.rotutee.lecture.lecture.repository
 * fileName         : MemberRepository
 * author           : SEOK
 * date             : 2022-05-06
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-06      SEOK         최초 생성
 */
public interface MemberRepository extends JpaRepository<Member, Integer> {
}
