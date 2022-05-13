package com.greedy.rotutee.dashboard.lms.repository;

import com.greedy.rotutee.dashboard.lms.entity.LMSAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.repository
 * fileName : LMSAttachmentRepository
 * author : SeoYoung
 * date : 2022-05-13
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-13 SeoYoung 최초 생성
 */
public interface LMSAttachmentRepository extends JpaRepository<LMSAttachment, Integer> {

    LMSAttachment findByMemberNoAndDivisionAndFileDeletionYN(int memberNo, String division, String deletionStatus);
}
