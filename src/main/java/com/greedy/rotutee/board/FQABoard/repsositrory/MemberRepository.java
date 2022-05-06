package com.greedy.rotutee.board.FQABoard.repsositrory;


import com.greedy.rotutee.board.FQABoard.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "FQABoard_MemberRepository")
public interface MemberRepository extends JpaRepository<Member, Integer> {
}
