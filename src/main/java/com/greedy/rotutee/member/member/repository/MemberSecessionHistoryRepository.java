package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.MemberSecessionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.member.member.repository
 * fileName : MemberSecessionHistoryRepository
 * author : 7sang
 * date : 2022-05-10
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-10 7sang 최초 생성
 */

@Repository(value = "Member_MemberSecessionHistoryRepository")
public interface MemberSecessionHistoryRepository extends JpaRepository<MemberSecessionHistory, Integer> {
}
