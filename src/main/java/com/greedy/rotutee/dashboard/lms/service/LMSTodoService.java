package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.ToDoDTO;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.service
 * fileName : LMSTodoService
 * author : SeoYoung
 * date : 2022-05-11
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-11 SeoYoung 최초 생성
 */
public interface LMSTodoService {
    void registTodo(ToDoDTO todo);

    boolean modifyTodo(String content, int todoNo);

    boolean removeTodo(int todoNo);
}
