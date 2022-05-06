package com.greedy.rotutee.main.repository;

import com.greedy.rotutee.main.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName      : com.greedy.rotutee.main.repository
 * fileName         : MainMemberRepository
 * author           : SEOK
 * date             : 2022-05-06
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-06      SEOK         최초 생성
 */
public interface MainMemberRepository extends JpaRepository<Member, Integer> {
}
