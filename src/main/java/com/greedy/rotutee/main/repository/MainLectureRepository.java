package com.greedy.rotutee.main.repository;

import com.greedy.rotutee.main.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName      : com.greedy.rotutee.main.repository
 * fileName         : MainLectureRepository
 * author           : SEOK
 * date             : 2022-05-04
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-04      SEOK         최초 생성
 */
@Repository
public interface MainLectureRepository extends JpaRepository<Lecture, Integer> {

    @Query("select a from Main_Lecture a where a.lectureOpeningDate is not null order by a.lectureOpeningDate desc")
    List<Lecture> findRecentLectureList();

    @Query(value = "SELECT DISTINCT\n" +
            "    A.*\n" +
            "   FROM TBL_LECTURE A\n" +
            "   RIGHT JOIN TBL_LECTURE_REVIEW B ON A.LECTURE_NO = B.LECTURE_NO\n" +
            "  WHERE (SELECT COUNT(*)\n" +
            "           FROM TBL_LECTURE_REVIEW) > 0\n" +
            " ORDER BY (SELECT AVG(C.LECTURE_GRADE)\n" +
            "            FROM TBL_LECTURE_REVIEW C) ASC", nativeQuery = true)
    List<Lecture> findPopularLectureList();
}
