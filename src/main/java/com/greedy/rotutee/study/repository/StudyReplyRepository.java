package com.greedy.rotutee.study.repository;

import com.greedy.rotutee.study.dto.StudyReplyDTO;
import com.greedy.rotutee.study.entity.StudyReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyReplyRepository extends JpaRepository <StudyReply, Integer>{


    StudyReply getByStudyNoAndReplyNo(int studyNo, int replyNo);

    List<StudyReply> findByStudyNoAndReplyStatus(int no, String n);
}
