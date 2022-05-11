package com.greedy.rotutee.lecture.request.service;

import com.greedy.rotutee.lecture.request.dto.LectureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LectureRequestService {
    List<LectureDTO> findLectureListBytutorNo(int memberNo);

    void registLectureOpeningApplication(LectureDTO newLecture, int categoryNo, int memberNo) throws IOException;

    LectureDTO findLectureByLectureNo(int lectureNo);

    void modifyLectureApprovalStatus(int lectureNo);

    void rejectLecture(int lectureNo, int rejectionCategoryNo);

    Page<LectureDTO> findAllLecture(Pageable pageable, Map<String, String> searchMap);
}
