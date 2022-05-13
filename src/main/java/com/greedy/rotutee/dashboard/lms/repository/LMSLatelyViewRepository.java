package com.greedy.rotutee.dashboard.lms.repository;

import com.greedy.rotutee.dashboard.lms.entity.LMSLatelyViewClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.repository
 * fileName : LMSLatelyViewRepository
 * author : SeoYoung
 * date : 2022-04-26
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-26 SeoYoung 최초 생성
 */
public interface LMSLatelyViewRepository extends JpaRepository<LMSLatelyViewClass, Integer> {

    List<LMSLatelyViewClass> findByMemberLectureNoOrderByTimeNoDesc(int lectureNo);

    List<LMSLatelyViewClass> findByMemberLectureNoOrderByTimeNoAsc(int memberLectureNo);

    LMSLatelyViewClass findByLmsClassClassNo(int classNo);
}
