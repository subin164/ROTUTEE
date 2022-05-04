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

    @Query("select a from Main_Lecture a where a.lectureOpeningDate is not null order by a.lectureOpeningDate asc")
    List<Lecture> findRecentLectureList();
}
