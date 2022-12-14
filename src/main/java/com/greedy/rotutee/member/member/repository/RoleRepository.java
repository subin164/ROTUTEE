package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "Member_RoleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByNo(Integer no);
}
