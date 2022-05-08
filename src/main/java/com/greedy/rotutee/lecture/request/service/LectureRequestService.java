package com.greedy.rotutee.lecture.request.service;

import com.greedy.rotutee.lecture.request.dto.LectureDTO;

import java.io.IOException;
import java.util.List;

public interface LectureRequestService {
    List<LectureDTO> findLectureListBytutorNo(int memberNo);

    void registLectureOpeningApplication(LectureDTO newLecture, int categoryNo, int memberNo) throws IOException;

    List<LectureDTO> findStatusOfLectureIsWaiting();

    List<LectureDTO> findStatusOfLectureIsNotWaiting();

    LectureDTO findLectureByLectureNo(int lectureNo);

    void modifyLectureApprovalStatus(int lectureNo);

    void rejectLecture(int lectureNo, int rejectionCategoryNo);

}
