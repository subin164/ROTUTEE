package com.greedy.rotutee.main.repository;

import com.greedy.rotutee.main.entity.Member;
import com.greedy.rotutee.main.entity.MemberInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * packageName      : com.greedy.rotutee.main.repository
 * fileName         : MainMemberInterestRepository
 * author           : SEOK
 * date             : 2022-05-06
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-06      SEOK         최초 생성
 */
public interface MainMemberInterestRepository extends JpaRepository<MemberInterest, Integer> {

    List<MemberInterest> findByMemberOrderByInterestDegreeDesc(Member member);
}
