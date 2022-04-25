package com.greedy.rotutee.dashboard.mypage.service;

import com.greedy.rotutee.dashboard.mypage.dto.tutee.MypageDashboardDTO;
import com.greedy.rotutee.dashboard.mypage.dto.tutor.MypageTutorDTO;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.service
 * fileName : MypageDashboardService
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */
public interface MypageDashboardService {


    MypageDashboardDTO findTuteeDashboard(int memberNo);

    MypageTutorDTO findTutorDashboard(int memberNo);

}
