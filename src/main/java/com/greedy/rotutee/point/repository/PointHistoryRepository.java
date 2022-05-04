package com.greedy.rotutee.point.repository;

import com.greedy.rotutee.point.dto.PointHistoryDTO;
import com.greedy.rotutee.point.entity.Member;
import com.greedy.rotutee.point.entity.PointHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * packageName : com.greedy.rotutee.point.repository
 * fileName : PointHistoryRepository
 * author : 7sang
 * date : 2022-05-02
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-02 7sang 최초 생성
 */

@Repository(value = "Point_PointHistoryRepository")
public interface PointHistoryRepository extends JpaRepository<PointHistory, Integer> {

    Page<PointHistory> findByMemberNoAndDivision(int memberNo, String division, Pageable pageable);

    Page<PointHistory> findByMemberAndDivision(Member member, String division, Pageable pageable);
}
