package com.greedy.rotutee.member.repository;

import com.greedy.rotutee.member.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Integer> {

    List<MemberRole> findMemberRoleByMemberRoleNo(Integer no);

}
