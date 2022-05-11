package com.greedy.rotutee.dashboard.mypage.service;

import com.greedy.rotutee.board.serviceBoard.dto.BoardDTO;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.MypageDashboardDTO;
import com.greedy.rotutee.dashboard.mypage.dto.tutor.MypageTutorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    Page<BoardDTO> findAllMyPost(int memberNo, Pageable pageable);
}
