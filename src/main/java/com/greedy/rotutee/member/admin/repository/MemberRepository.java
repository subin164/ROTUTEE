package com.greedy.rotutee.member.admin.repository;

import com.greedy.rotutee.member.admin.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "Admin_MemberRepository")
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findMemberByEmail(String username);

    List<Member> findByMemberRoleListRoleNo(int i);

    Page<Member> findByMemberRoleListRoleNo(int i, Pageable pageable);
}
