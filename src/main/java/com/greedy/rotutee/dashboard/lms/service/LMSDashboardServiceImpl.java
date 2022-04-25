package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.LMSDashboardDTO;
import org.springframework.stereotype.Service;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.service
 * fileName : LMSDashboardServiceImpl
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */
@Service
public class LMSDashboardServiceImpl implements LMSDashboardService{

    @Override
    public LMSDashboardDTO findLMSDashboard(int memberNo) {

        LMSDashboardDTO dashboard = new LMSDashboardDTO();

        /* ToDo 조회 */
        
        /* 공지사항 조회 */

        return dashboard;
    }
}
