package com.greedy.rotutee.member.repository;

import com.greedy.rotutee.member.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRoleRepository extends JpaRepository<com.greedy.rotutee.member.entity.MemberRole, Integer> {

    List<MemberRole> findMemberAuthorityByMemberAuthorityNo(Integer no);

}
