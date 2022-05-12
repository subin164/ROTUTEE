package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.member.member.repository
 * fileName : LoginHistoryRepository
 * author : 7sang
 * date : 2022-05-12
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-12 7sang 최초 생성
 */

@Repository(value = "Member_LoginHistoryRepository")
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Integer> {
}
