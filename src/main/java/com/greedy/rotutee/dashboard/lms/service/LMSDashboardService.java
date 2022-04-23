package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.LMSDashboardDTO;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.service
 * fileName : LMSDashboardService
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */
public interface LMSDashboardService {
    LMSDashboardDTO findLMSDashboard(int memberNo);


}
