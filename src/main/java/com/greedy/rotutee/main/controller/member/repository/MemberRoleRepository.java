package com.greedy.rotutee.main.controller.member.repository;

import com.greedy.rotutee.main.controller.member.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Integer> {

    List<MemberRole> findMemberAuthorityByMemberAuthorityNo(Integer no);

}
