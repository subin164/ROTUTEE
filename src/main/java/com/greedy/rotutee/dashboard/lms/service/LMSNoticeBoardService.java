package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.LMSNormalBoardDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSNoticeBoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.service
 * fileName : LMSNormalBoardService
 * author : SeoYoung
 * date : 2022-05-09
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-09 SeoYoung 최초 생성
 */
public interface LMSNoticeBoardService {
    Page<LMSNoticeBoardDTO> findNoticeList(Pageable pageable, Map<String, String> searchMap, int lectureNo);

    LMSNoticeBoardDTO findNoticeDetail(int noticeNo);

    void registNotice(LMSNoticeBoardDTO notice);

    void modifyNotice(LMSNoticeBoardDTO notice);

    void removeNotice(int boardNo);
}
