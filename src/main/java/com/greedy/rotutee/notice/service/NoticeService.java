package com.greedy.rotutee.notice.service;

import com.greedy.rotutee.notice.dto.NoticeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * packageName : com.greedy.rotutee.notice.service
 * fileName : NoticeService
 * author : SeoYoung
 * date : 2022-05-11
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-11 SeoYoung 최초 생성
 */
public interface NoticeService {
    Page<NoticeDTO> findNoticeList(int memberNo, Pageable pageable);
}
