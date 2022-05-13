package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.RoleMenuUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleMenuUrlRepository extends JpaRepository<RoleMenuUrl, Integer> {

    @Query("select DISTINCT a from RoleMenuUrl a join fetch a.menu where a.role.no = :no")
    List<RoleMenuUrl> findByRoleNo(Integer no);
}
