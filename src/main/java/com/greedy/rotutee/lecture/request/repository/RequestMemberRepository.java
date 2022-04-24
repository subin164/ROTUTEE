package com.greedy.rotutee.lecture.request.repository;

import com.greedy.rotutee.lecture.request.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestMemberRepository extends JpaRepository<Member, Integer> {
    Member findByNo(int memberNo);
}
