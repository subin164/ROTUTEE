package com.greedy.rotutee.notice.service;

import com.greedy.rotutee.notice.dto.NoticeDTO;
import com.greedy.rotutee.notice.entity.Notice;
import com.greedy.rotutee.notice.repository.NoticeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * packageName : com.greedy.rotutee.notice.service
 * fileName : NoticeServiceImpl
 * author : SeoYoung
 * date : 2022-05-11
 * description : 알림 비즈니스 로직
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-11 SeoYoung 최초 생성
 */
@Service
public class NoticeServiceImpl implements NoticeService{

    private NoticeRepository noticeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository, ModelMapper modelMapper) {
        this.noticeRepository = noticeRepository;
        this.modelMapper = modelMapper;
    }


    /**
     * methodName : findNoticeList
     * author : SeoYoung Kim
     * description : 알림 조회
     *
     * @param memberNo
     * @param pageable 알림 페이징 처리
     * @return page
     */
    @Override
    public Page<NoticeDTO> findNoticeList(int memberNo, Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() -1, pageable.getPageSize(),
                Sort.by("noticeNo").descending());

        Page<Notice> noticeEntities = noticeRepository.findByMemberNo(pageable, memberNo);

        return noticeEntities.map(Notice -> modelMapper.map(Notice, NoticeDTO.class));
    }
}
