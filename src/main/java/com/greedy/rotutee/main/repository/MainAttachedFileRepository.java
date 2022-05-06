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

    @Query(value = "select a.* from TBL_ATTACHED_FILE a join TBL_LECTURE b on a.LECTURE_NO = b.LECTURE_NO join TBL_LECTURE_CATEGORY c on b.LECTURE_CATEGORY_NO = c.LECTURE_CATEGORY_NO where c.LECTURE_CATEGORY_NO  = 3 and a.DIVISION = '배너'", nativeQuery = true)
    List<AttachedFile> findByCategoryNo(int lectureCategoryNo);
}
