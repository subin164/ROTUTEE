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
 * description : LMS 공지 게시판 서비스 로직
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

    /**
     * methodName : findNoticeList
     * author : SeoYoung Kim
     * description : 공지사항 조회
     *
     * @param pageable 페이징을 위한 parameter
     * @param searchMap 검색했을 시 넘어오는 값(검색카테고리, 검색어)
     * @param lectureNo 강의번호
     * @return page
     */
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

    /**
     * methodName : findNoticeDetail
     * author : SeoYoung Kim
     * description : 공지사항 상세조회
     *
     * @param noticeNo 공지 게시판 번호
     * @return lmsNoticeBoardDTO 공지사항 상세조회 정보
     */
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

    /**
     * methodName : registNotice
     * author : SeoYoung Kim
     * description : 공지사항 등록
     *
     * @param notice 등록할 공지사항 정보
     */
    @Override
    @Transactional
    public void registNotice(LMSNoticeBoardDTO notice) {

        LMSNotice noticeEntity = modelMapper.map(notice, LMSNotice.class);
        lmsNoticeRepository.save(noticeEntity);

    }

    /**
     * methodName : modifyNotice
     * author : SeoYoung Kim
     * description : 공지사항 수정
     *
     * @param notice 수정할 공지사항 정보
     */
    @Override
    @Transactional
    public void modifyNotice(LMSNoticeBoardDTO notice) {

        LMSNotice noticeDetailEntity = lmsNoticeRepository.findByBoardNo(notice.getBoardNo());
        noticeDetailEntity.setContent(notice.getContent());
        noticeDetailEntity.setTitle(notice.getTitle());
        noticeDetailEntity.setModifiedDate(notice.getModifiedDate());

    }

    /**
     * methodName : removeNotice
     * author : SeoYoung Kim
     * description : 공지사항 삭제
     *
     * @param boardNo 삭제할 공지사항 번호
     */
    @Override
    public void removeNotice(int boardNo) {

        LMSNotice noticeDetailEntity = lmsNoticeRepository.findByBoardNo(boardNo);
        noticeDetailEntity.setDeleteStatus("Y");
        noticeDetailEntity.setDeletedDate(new Date(System.currentTimeMillis()));

    }


}
