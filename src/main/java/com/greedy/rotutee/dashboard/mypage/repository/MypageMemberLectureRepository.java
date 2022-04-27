package com.greedy.rotutee.dashboard.mypage.repository;

import com.greedy.rotutee.dashboard.mypage.entity.DashboardMember;
import com.greedy.rotutee.dashboard.mypage.entity.MyPageMemberLecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.repository
 * fileName : MypageMemberLectureRepository
 * author : SeoYoung
 * date : 2022-04-24
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-24 SeoYoung 최초 생성
 */
public interface MypageMemberLectureRepository extends JpaRepository<MyPageMemberLecture, Integer> {

    List<MyPageMemberLecture> findBymember(DashboardMember member);

    MyPageMemberLecture findByLectureLectureNoAndMemberMemberNo(int lectureNo, int memberNo);
}
