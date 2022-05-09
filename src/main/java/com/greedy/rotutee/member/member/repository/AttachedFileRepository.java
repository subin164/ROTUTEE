package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.AttachedFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.member.member.repository
 * fileName : AttachedFileRepository
 * author : 7sang
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-22 7sang 최초 생성
 */

@Repository(value = "Member_AttachedFileRepository")
public interface AttachedFileRepository extends JpaRepository<AttachedFile, Integer> {


    List<AttachedFile> findByMemberNoAndDivision(int memberNo, String division);

    Page<AttachedFile> findByMemberNoAndDivisionAndFileDeletionYn(int memberNo, String division, String n, Pageable pageable);
}
