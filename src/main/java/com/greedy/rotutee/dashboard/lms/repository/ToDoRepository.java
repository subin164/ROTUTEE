package com.greedy.rotutee.dashboard.lms.repository;

import com.greedy.rotutee.dashboard.lms.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.repository
 * fileName : ToDoRepository
 * author : SeoYoung
 * date : 2022-04-25
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-25 SeoYoung 최초 생성
 */
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    List<ToDo> findByLectureNoOrderByTodoNoDesc(int lectureNo);

    List<ToDo> findByLectureNoOrderByTodoNoAsc(int lectureNo);
}
