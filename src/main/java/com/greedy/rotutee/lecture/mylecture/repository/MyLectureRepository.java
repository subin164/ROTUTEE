package com.greedy.rotutee.lecture.mylecture.repository;

import com.greedy.rotutee.lecture.mylecture.entity.MyLecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.lecture.mylecture.repository
 * fileName : MyLectureRepository
 * author : SeoYoung
 * date : 2022-05-10
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-10 SeoYoung 최초 생성
 */
public interface MyLectureRepository extends JpaRepository<MyLecture, Integer> {
    List<MyLecture> findByMemberMemberNo(int memberNo);
}
