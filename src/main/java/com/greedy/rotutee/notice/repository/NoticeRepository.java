package com.greedy.rotutee.notice.repository;

import com.greedy.rotutee.notice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.greedy.rotutee.notice.repository
 * fileName : NoticeRepository
 * author : SeoYoung
 * date : 2022-05-11
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-11 SeoYoung 최초 생성
 */
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Page<Notice> findByMemberNo(Pageable pageable, int memberNo);

    Notice findByMemberNo(int memberNo);
}
