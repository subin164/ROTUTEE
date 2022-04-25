package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findMemberByEmail(String username);
}
