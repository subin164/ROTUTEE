package com.greedy.rotutee.main.repository;

import com.greedy.rotutee.main.entity.AttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * packageName      : com.greedy.rotutee.main.repository
 * fileName         : MainAttachedFileRepository
 * author           : SEOK
 * date             : 2022-05-06
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-06      SEOK         최초 생성
 */
public interface MainAttachedFileRepository extends JpaRepository<AttachedFile, Integer> {

    @Query(value = "select A.* from TBL_ATTACHED_FILE A join TBL_LECTURE B on A.LECTURE_NO = B.LECTURE_NO join TBL_LECTURE_CATEGORY C on B.LECTURE_CATEGORY_NO = C.LECTURE_CATEGORY_NO WHERE C.LECTURE_CATEGORY_NO  = :lectureCategoryNo and B.LECTURE_APPROVAL_STATUS = '승인' AND A.DIVISION = '배너' ORDER BY A.ATTACHED_FILE_NO DESC", nativeQuery = true)
    List<AttachedFile> findByCategoryNo(int lectureCategoryNo);

    @Query(value = "select A.* from TBL_ATTACHED_FILE A join TBL_LECTURE B on A.LECTURE_NO = B.LECTURE_NO WHERE A.DIVISION = '배너' AND B.LECTURE_APPROVAL_STATUS = '승인' ORDER BY B.LECTURE_OPENING_DATE DESC" ,nativeQuery = true)
    List<AttachedFile> findByRecentLectureBanner();
}
