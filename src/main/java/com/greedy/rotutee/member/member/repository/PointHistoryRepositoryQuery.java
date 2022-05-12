package com.greedy.rotutee.member.member.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * packageName : com.greedy.rotutee.point.repository
 * fileName : PointHistoryRepository
 * author : 7sang
 * date : 2022-05-02
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-02 7sang 최초 생성
 */

@Repository(value = "Member_PointHistoryRepositoryQuery")
public class PointHistoryRepositoryQuery {

    public int findMemberPoint(EntityManager entityManager, int memberNo) {

        String jpql = "SELECT a.finalPoint " +
                "FROM Point_PointHistory a " +
                "WHERE a.historyNo = " +
                "(SELECT max(b.historyNo) " +
                "FROM Point_PointHistory b " +
                "WHERE b.member.no = :memberNo)";

        return (int) entityManager.createQuery(jpql).setParameter("memberNo", memberNo).getSingleResult();
    }
}
