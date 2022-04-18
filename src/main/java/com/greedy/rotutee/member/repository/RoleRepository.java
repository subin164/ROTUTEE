package com.greedy.rotutee.member.repository;

import com.greedy.rotutee.member.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByNo(Integer no);
}
