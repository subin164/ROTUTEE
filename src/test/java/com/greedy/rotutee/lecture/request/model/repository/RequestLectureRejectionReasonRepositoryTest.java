package com.greedy.rotutee.lecture.request.model.repository;

import com.greedy.rotutee.lecture.request.entity.LectureRejectionReason;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName      : com.greedy.rotutee.lecture.request.repository
 * fileName         : requestLectureRejectionReasonRepository
 * author           : SEOK
 * date             : 2022-05-02
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-02      SEOK         최초 생성
 */
public interface RequestLectureRejectionReasonRepositoryTest extends JpaRepository<LectureRejectionReason, Integer> {
    LectureRejectionReason findByLectureRejectionReasonNo(int rejectionCategoryNo);
}
