package com.greedy.rotutee.main.controller.member.repository;

import com.greedy.rotutee.main.controller.member.entity.RoleMenuUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityMenuUrlRepository extends JpaRepository<RoleMenuUrl, Integer> {

    List<RoleMenuUrl> findAuthorityMenuUrlByAuthorityNo(Integer no);
}
