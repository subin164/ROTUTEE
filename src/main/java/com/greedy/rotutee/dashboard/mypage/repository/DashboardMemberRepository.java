package com.greedy.rotutee.dashboard.mypage.repository;

import com.greedy.rotutee.dashboard.mypage.entity.DashboardMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.repository
 * fileName : MemberRepository
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */


public interface DashboardMemberRepository extends JpaRepository<DashboardMember, Integer> {
}
