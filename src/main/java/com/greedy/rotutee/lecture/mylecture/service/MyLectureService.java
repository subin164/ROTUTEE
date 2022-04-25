package com.greedy.rotutee.lecture.mylecture.service;

import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardLectureDTO;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.lecture.mylecture.service
 * fileName : MyLectureService
 * author : SeoYoung
 * date : 2022-04-24
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-24 SeoYoung 최초 생성
 */
public interface MyLectureService {
    List<DashboardLectureDTO> findLearningList(int memberNo);


}
