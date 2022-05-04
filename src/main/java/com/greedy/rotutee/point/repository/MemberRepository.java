package com.greedy.rotutee.point.repository;

import com.greedy.rotutee.point.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository(value = "Point_MemberRepository")
public interface MemberRepository extends JpaRepository<Member, Integer> {

}
