package com.greedy.rotutee.member.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * packageName : com.greedy.rotutee.member.repository
 * fileName : AchievementRepository
 * author : 7sang
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 7sang 최초 생성
 */

@Repository
public class AchievementHistoryRepository {

    public int findAchievement(EntityManager entityManager, int memberNo) {

        String jpql = "SELECT a.memberAchievement.achievement.achievementNo " +
                "FROM Member_MemberAchievementHistory a " +
                "WHERE a.historyNo = " +
                "(SELECT max(a.historyNo) " +
                        "FROM Member_MemberAchievementHistory a " +
                       "WHERE a.member.no = :memberNo)";

        return (int) entityManager.createQuery(jpql).setParameter("memberNo", memberNo).getSingleResult();
    }
}
