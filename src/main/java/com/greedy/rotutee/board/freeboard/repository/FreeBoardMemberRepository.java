package com.greedy.rotutee.board.freeboard.repository;


import com.greedy.rotutee.board.freeboard.entity.FreeBoardMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.board.repository
 * fileName : MemberRepository
 * author : soobeen
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-20        soobeen     최초 생성
 */

@Repository
public interface FreeBoardMemberRepository extends JpaRepository<FreeBoardMember, Integer> {
}
