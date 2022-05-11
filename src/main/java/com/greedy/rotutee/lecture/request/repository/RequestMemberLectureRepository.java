package com.greedy.rotutee.lecture.request.repository;

import com.greedy.rotutee.lecture.request.entity.MemberLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName      : com.greedy.rotutee.lecture.request.repository
 * fileName         : RequestMemberLectureRepository
 * author           : SEOK
 * date             : 2022-05-11
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-11      SEOK         최초 생성
 */
@Repository
public interface RequestMemberLectureRepository extends JpaRepository<MemberLecture, Integer> {
}
