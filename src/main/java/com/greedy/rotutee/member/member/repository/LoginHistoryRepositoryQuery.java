package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.LoginHistory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository(value = "Member_LoginHistoryRepositoryQuery")
public class LoginHistoryRepositoryQuery {

    public List<LoginHistory> findMemberLoginHistory(EntityManager entityManager, int memberNo) {

        String jpql = "SELECT a " +
                "FROM Member_LoginHistory a " +
                "WHERE a.loginNo = " +
                "(SELECT max(b.loginNo) " +
                "FROM Member_LoginHistory b " +
                "WHERE b.member.no = :memberNo)";

        List<LoginHistory> loginHistory = entityManager.createQuery(jpql, LoginHistory.class).setParameter("memberNo", memberNo).getResultList();

        return loginHistory;
    }
}
