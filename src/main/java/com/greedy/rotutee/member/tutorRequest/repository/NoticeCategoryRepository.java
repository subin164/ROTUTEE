package com.greedy.rotutee.member.tutorRequest.repository;

import com.greedy.rotutee.member.tutorRequest.entity.NoticeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.repository
 * fileName : NoticeCategoryRepositroy
 * author : 7sang
 * date : 2022-05-14
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-14 7sang 최초 생성
 */

@Repository(value = "Rquest_NoticeCategoryRepository")
public interface NoticeCategoryRepository extends JpaRepository<NoticeCategory, Integer> {
}
