package com.greedy.rotutee.dashboard.lms.model.service;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.dashboard.lms.entity.LMSNotice;
import com.greedy.rotutee.dashboard.lms.repository.LMSNoticeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.model.service
 * fileName : LMSNoticeServiceTest
 * author : SeoYoung
 * date : 2022-05-09
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-09 SeoYoung 최초 생성
 */
@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class LMSNoticeServiceTest {

    @Autowired
    private LMSNoticeRepository lmsNoticeRepository;

    @Test
    public void 공지사항_조회_확인() {

        //given
        int categoryNo = 10;
        int lectureNo = 6;
        //when
        List<LMSNotice> noticeEntities = lmsNoticeRepository.findByCategoryNo(categoryNo);

        //then
        for (LMSNotice noticeEntity : noticeEntities) {
            System.out.println("noticeEntity = " + noticeEntity);
        }

    }
}
