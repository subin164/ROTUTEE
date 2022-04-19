package com.greedy.rotutee.member.repository;

import com.greedy.rotutee.member.entity.Member;
import com.greedy.rotutee.member.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findMemberByEmail(String username);
}
