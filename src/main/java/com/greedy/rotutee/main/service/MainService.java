package com.greedy.rotutee.main.service;

import com.greedy.rotutee.main.dto.AttachedFileDTO;
import com.greedy.rotutee.main.dto.LectureDTO;

import java.util.List;

public interface MainService {
    List<LectureDTO> findRecentLectureList();

    List<LectureDTO> findPopularLectureList();

    List<AttachedFileDTO> findBannerListByMemberNo(int no);

    List<AttachedFileDTO> findRecentBannerList();
}
