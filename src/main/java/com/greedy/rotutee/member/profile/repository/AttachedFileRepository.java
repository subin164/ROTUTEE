package com.greedy.rotutee.member.profile.repository;

import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.profile.entity.AttachedFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.member.profile.repository
 * fileName : AttachedFileRepository
 * author : 7sang
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-22 7sang 최초 생성
 */

@Repository(value = "Profile_AttachedFileRepository")
public interface AttachedFileRepository extends JpaRepository<AttachedFile, Integer> {

    AttachedFile findByMemberNo(int memeberNo);

    AttachedFile findByMemberNoAndDivision(int memeberNo, String division);

    Page<AttachedFile> findByDivisionAndFileDeletionYn(String division, String n_, Pageable pageable);

//    void deleteByMemberNo(Member member);
}
