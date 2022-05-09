package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.MemberStatusHistory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.member.member.repository
 * fileName : MemberStatusHistoryRepositoryImpl
 * author : 7sang
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 7sang 최초 생성
 */

@Repository
public class MemberStatusHistoryRepositoryQuery {

    public MemberStatusHistory findMemberStatus(EntityManager entityManager, int memberNo) {

        String jpql = "SELECT a " +
                "FROM Member_MemberStatusHistory a " +
                "WHERE a.historyNo = " +
                "(SELECT max(b.historyNo) " +
                "FROM Member_MemberStatusHistory b " +
                "WHERE b.member.no = :memberNo)";

        return (MemberStatusHistory) entityManager.createQuery(jpql).setParameter("memberNo", memberNo).getSingleResult();
    }
}
