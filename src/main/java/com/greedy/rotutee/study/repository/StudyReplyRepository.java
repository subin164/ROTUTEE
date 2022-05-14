package com.greedy.rotutee.study.repository;

import com.greedy.rotutee.study.dto.StudyReplyDTO;
import com.greedy.rotutee.study.entity.StudyReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface StudyReplyRepository.
 */
public interface StudyReplyRepository extends JpaRepository <StudyReply, Integer>{


    /**
     * Gets by study no and reply no.
     * author : SeoYoung Kim
     * description :
     *
     * @param studyNo the study no
     * @param replyNo the reply no
     * @return the by study no and reply no
     */
    StudyReply getByStudyNoAndReplyNo(int studyNo, int replyNo);

    /**
     * methodName : findByStudyNoAndReplyStatus
     * author : SeoYoung Kim
     * description :
     *
     * @param no
     * @param n
     * @return list
     */
    List<StudyReply> findByStudyNoAndReplyStatus(int no, String n);
}
