package com.greedy.rotutee.member.admin.repository;

import com.greedy.rotutee.member.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "Admin_RoleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByNo(Integer no);
}
