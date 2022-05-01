package com.greedy.rotutee.study.repository;

import com.greedy.rotutee.study.entity.StudyReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyReplyRepository extends JpaRepository <StudyReply, Integer>{


    List<StudyReply> getByStudyNo(int no);
}
