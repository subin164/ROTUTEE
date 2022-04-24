package com.greedy.rotutee.lecture.lecture.model.repository;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.lecture.lecture.entity.Chapter;
import com.greedy.rotutee.lecture.lecture.repository.ChapterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, BeanConfiguration.class, JPAConfiguration.class})
public class ChapterRepositoryTest {

    @Autowired
    private ChapterRepository chapterRepository;

    @Test
    public void 레포지토리_의존성_테스트() {

        assertNotNull(chapterRepository);
        System.out.println("chapterRepository = " + chapterRepository);
    }

    @Test
    public void 강의의_챕터목록_조회_테스트() {
        //given
        int lectureNo = 2;
        //when
        List<Chapter> chapterList = chapterRepository.findByLectureNo(lectureNo);
        //then
        assertNotNull(chapterList);
        chapterList.forEach(System.out::println);
    }
}
