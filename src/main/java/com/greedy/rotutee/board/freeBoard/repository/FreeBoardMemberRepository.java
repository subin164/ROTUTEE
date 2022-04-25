package com.greedy.rotutee.board.freeBoard.repository;


import com.greedy.rotutee.board.freeBoard.entity.FreeBoardMember;
import org.springframework.data.jpa.repository.JpaRepository;

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

public interface FreeBoardMemberRepository extends JpaRepository<FreeBoardMember, Integer> {
}
