package com.greedy.rotutee.member.repository;

import com.greedy.rotutee.member.entity.AttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.member.repository
 * fileName : ProfileRepository
 * author : 7sang
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 7sang 최초 생성
 */

@Repository
public interface ProfileRepository extends JpaRepository<AttachedFile, Integer> {
}
