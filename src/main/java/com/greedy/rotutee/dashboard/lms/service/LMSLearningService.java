package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.LecturePlayDTO;
import com.greedy.rotutee.dashboard.lms.entity.LMSChapter;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.service
 * fileName : LMSService
 * author : SeoYoung
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 SeoYoung 최초 생성
 */
public interface LMSLearningService {


    LecturePlayDTO findLecturePlay(int lectureNo, int memberNo);

    void modifyVideoWatchingStatus(int timeNo, String status);
}
