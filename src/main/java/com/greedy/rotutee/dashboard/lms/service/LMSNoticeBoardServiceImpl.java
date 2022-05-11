package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.LMSNoticeBoardDTO;
import com.greedy.rotutee.dashboard.lms.entity.LMSNotice;
import com.greedy.rotutee.dashboard.lms.repository.LMSNoticeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Map;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.service
 * fileName : LMSNormalBoardServiceImpl
 * author : SeoYoung
 * date : 2022-05-09
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-09 SeoYoung 최초 생성
 */
@Service
public class LMSNoticeBoardServiceImpl implements LMSNoticeBoardService {

    private LMSNoticeRepository lmsNoticeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public LMSNoticeBoardServiceImpl(LMSNoticeRepository lmsNoticeRepository, ModelMapper modelMapper) {
        this.lmsNoticeRepository = lmsNoticeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<LMSNoticeBoardDTO> findNoticeList(Pageable pageable, Map<String, String> searchMap, int lectureNo) {

        System.out.println("pageable = " + pageable);
        int categoryNo = 10;

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() -1, pageable.getPageSize(),
                Sort.by("boardNo").descending());

        String searchCondition = searchMap.get("searchCondition");
        String searchValue = searchMap.get("searchValue");

        Page<LMSNotice> noticeEntities = null;
        if(searchCondition == null || searchCondition.equals("")) {
            noticeEntities = lmsNoticeRepository.findByCategoryNoAndLectureNo(categoryNo, lectureNo, pageable);
        } else if(searchCondition.equals("title")) {
            noticeEntities = lmsNoticeRepository.findByCategoryNoAndLectureNoAndTitleContaining(categoryNo, lectureNo, searchValue, pageable);
        }

        return noticeEntities.map(Lms_Notice -> modelMapper.map(Lms_Notice, LMSNoticeBoardDTO.class));
    }

    @Override
    @Transactional
    public LMSNoticeBoardDTO findNoticeDetail(int noticeNo) {

        LMSNotice noticeDetailEntity = lmsNoticeRepository.findByBoardNo(noticeNo);
        int currentCount = noticeDetailEntity.getCount();
        int incrementCount = currentCount + 1;
        noticeDetailEntity.setCount(incrementCount);

        LMSNoticeBoardDTO noticeDetail = modelMapper.map(noticeDetailEntity, LMSNoticeBoardDTO.class);

        return noticeDetail;
    }

    @Override
    @Transactional
    public void registNotice(LMSNoticeBoardDTO notice) {

        LMSNotice noticeEntity = modelMapper.map(notice, LMSNotice.class);
        lmsNoticeRepository.save(noticeEntity);

    }

    @Override
    @Transactional
    public void modifyNotice(LMSNoticeBoardDTO notice) {

        LMSNotice noticeDetailEntity = lmsNoticeRepository.findByBoardNo(notice.getBoardNo());
        noticeDetailEntity.setContent(notice.getContent());
        noticeDetailEntity.setTitle(notice.getTitle());
        noticeDetailEntity.setModifiedDate(notice.getModifiedDate());

    }

    @Override
    public void removeNotice(int boardNo) {

        LMSNotice noticeDetailEntity = lmsNoticeRepository.findByBoardNo(boardNo);
        noticeDetailEntity.setDeleteStatus("Y");
        noticeDetailEntity.setDeletedDate(new Date(System.currentTimeMillis()));

    }


}
