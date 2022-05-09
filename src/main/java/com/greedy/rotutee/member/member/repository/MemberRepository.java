package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "Member_MemberRepository")
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findMemberByEmail(String username);

    List<Member> findByMemberRoleListRoleNo(int i);

    Page<Member> findByMemberRoleListRoleNo(int i, Pageable pageable);

    Page<Member> findByNameContaining(String searchValue, Pageable pageable);

    Page<Member> findByEmailContaining(String searchValue, Pageable pageable);
}
