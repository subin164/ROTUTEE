package com.greedy.rotutee.member.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.member.repository
 * fileName : TutorInfoRepository
 * author : 7sang
 * date : 2022-04-21
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-21 7sang 최초 생성
 */

@Repository
public interface TutorInfoRepository extends JpaRepository<TutorInfo, Integer> {
}
