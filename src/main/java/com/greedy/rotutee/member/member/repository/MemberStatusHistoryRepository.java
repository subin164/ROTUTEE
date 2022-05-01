package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.MemberStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * packageName : com.greedy.rotutee.member.member.repository
 * fileName : MemberStatusHistoryRepository
 * author : 7sang
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 7sang 최초 생성
 */

@Repository
public interface MemberStatusHistoryRepository extends JpaRepository<MemberStatusHistory, Integer> {

}
