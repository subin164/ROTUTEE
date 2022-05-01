package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findMemberByEmail(String username);

    List<Member> findByMemberRoleListRoleNo(int i);

    Page<Member> findByMemberRoleListRoleNo(int i, Pageable pageable);
}
