package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.SuspensionHitory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.member.member.repository
 * fileName : SuspensionHitoryRepository
 * author : 7sang
 * date : 2022-04-28
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-28 7sang 최초 생성
 */

@Repository
public interface SuspensionHitoryRepository extends JpaRepository<SuspensionHitory, Integer> {
}
