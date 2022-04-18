package com.greedy.rotutee.member.repository;

import com.greedy.rotutee.member.entity.RoleMenuUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleMenuUrlRepository extends JpaRepository<RoleMenuUrl, Integer> {

    List<RoleMenuUrl> findRoleMenuUrlByRoleNo(Integer no);
}
