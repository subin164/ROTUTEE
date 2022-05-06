package com.greedy.rotutee.board.serviceBoard.repository;


import com.greedy.rotutee.board.serviceBoard.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "ServiceBoard_MemberRepository")
public interface MemberRepository extends JpaRepository<Member, Integer> {
}
