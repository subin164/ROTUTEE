package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.AttendanceHistory;
import com.greedy.rotutee.member.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository(value = "Member_AttendanceHistoryRepository")
public interface AttendanceHistoryRepository extends JpaRepository<AttendanceHistory, Integer> {
//    AttendanceHistory findByMemberAttendanceDate(Member member, Date date);
}
