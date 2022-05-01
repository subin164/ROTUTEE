package com.greedy.rotutee.member.profile.repository;

import com.greedy.rotutee.member.profile.entity.MemberAchievementHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.member.member.repository
 * fileName : MemberAchievementHistoryRepository
 * author : 7sang
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 7sang 최초 생성
 */

@Repository(value = "Profile_MemberAchievementHistoryRepository")
public interface MemberAchievementHistoryRepository extends JpaRepository<MemberAchievementHistory, Integer> {
}
