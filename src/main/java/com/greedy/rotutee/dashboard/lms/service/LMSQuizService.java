package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.LMSQuizDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSQuizStatusDTO;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.service
 * fileName : LMSQuizService
 * author : SeoYoung
 * date : 2022-04-29
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-29 SeoYoung 최초 생성
 */
public interface LMSQuizService {
    LMSQuizDTO findQuizDetail(int quizNo);

    boolean gradingQuiz(int answer, int memberNo, int quizNo, int lectureNo);

    boolean confirmQuiz(int quizNo);

    LMSQuizStatusDTO findQuizStatus(int memberNo, int lectureNo);
}
