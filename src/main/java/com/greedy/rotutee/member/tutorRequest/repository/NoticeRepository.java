package com.greedy.rotutee.member.tutorRequest.repository;

import com.greedy.rotutee.member.tutorRequest.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.repository
 * fileName : NoticeRepository
 * author : 7sang
 * date : 2022-05-14
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-14 7sang 최초 생성
 */

@Repository(value = "Rquest_NoticeRepository")
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}
