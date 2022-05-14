package com.greedy.rotutee.lecture.mylecture.service;

import com.greedy.rotutee.dashboard.lms.dto.LMSAttachmentDTO;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardLectureDTO;
import com.greedy.rotutee.lecture.mylecture.dto.MyLectureDTO;

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

    List<MyLectureDTO> findMyLectureList(int memberNo);

}
