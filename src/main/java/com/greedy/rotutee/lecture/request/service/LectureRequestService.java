package com.greedy.rotutee.lecture.request.service;

import com.greedy.rotutee.lecture.request.dto.LectureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface LectureRequestService {
    List<LectureDTO> findLectureListBytutorNo(int memberNo);

    void registLectureOpeningApplication(LectureDTO newLecture, int categoryNo, int memberNo) throws IOException;

    Page<LectureDTO> findStatusOfLectureIsWaiting(Pageable pageable);

    Page<LectureDTO> findStatusOfLectureIsNotWaiting(Pageable pageable);

    LectureDTO findLectureByLectureNo(int lectureNo);

    void modifyLectureApprovalStatus(int lectureNo);

    void rejectLecture(int lectureNo, int rejectionCategoryNo);

}
