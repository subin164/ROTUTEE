package com.greedy.rotutee.member.tutorRequest.repository;

import com.greedy.rotutee.member.tutorRequest.entity.AttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.repository
 * fileName : AttachedFileRepository
 * author : 7sang
 * date : 2022-05-14
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-14 7sang 최초 생성
 */

@Repository(value = "Apply_AttachedFileRepository")
public interface AttachedFileRepository extends JpaRepository<AttachedFile, Integer> {
}
