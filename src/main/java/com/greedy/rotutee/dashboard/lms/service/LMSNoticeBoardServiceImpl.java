package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.LMSNormalBoardDTO;
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
    public LMSNoticeBoardServiceImpl(LMSNoticeRepository lmsNoticeRepository) {
        this.lmsNoticeRepository = lmsNoticeRepository;
    }

    @Override
    public Page<LMSNoticeBoardDTO> findNoticeList(Pageable pageable, Map<String, String> searchMap) {

        System.out.println("pageable = " + pageable);
        int categoryNo = 10;

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() -1, pageable.getPageSize(),
                Sort.by("boardNo").descending());

        String searchCondition = searchMap.get("searchCondition");
        String searchValue = searchMap.get("searchValue");

        Page<LMSNotice> noticeEntities = null;
        if(searchCondition == null || searchCondition.equals("")) {
            noticeEntities = lmsNoticeRepository.findByCategoryNo(categoryNo, pageable);
        } else if(searchCondition.equals("title")) {
            noticeEntities = lmsNoticeRepository.findByCategoryNoAndTitleContaining(categoryNo, searchValue, pageable);
        }

        return noticeEntities.map(Lms_Notice -> modelMapper.map(Lms_Notice, LMSNoticeBoardDTO.class));
    }
}
