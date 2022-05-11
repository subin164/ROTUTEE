package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.ToDoDTO;
import com.greedy.rotutee.dashboard.lms.entity.ToDo;
import com.greedy.rotutee.dashboard.lms.repository.ToDoRepository;
import com.greedy.rotutee.dashboard.mypage.entity.MyPageMemberLecture;
import com.greedy.rotutee.dashboard.mypage.repository.MypageMemberLectureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.service
 * fileName : LMSTodoServiceImpl
 * author : SeoYoung
 * date : 2022-05-11
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-11 SeoYoung 최초 생성
 */
@Service
public class LMSTodoServiceImpl implements LMSTodoService{


    private ToDoRepository todoRepository;
    private MypageMemberLectureRepository memberLectureRepository;
    private ModelMapper modelMapper;

    @Autowired
    public LMSTodoServiceImpl(ToDoRepository todoRepository, MypageMemberLectureRepository memberLectureRepository, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.memberLectureRepository = memberLectureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registTodo(ToDoDTO todo) {
        int memberNo = todo.getMemberNo();
        int lectureNo = todo.getLectureNo();

        System.out.println("memberNo = " + memberNo);
        System.out.println("lectureNo = " + lectureNo);
        MyPageMemberLecture memberLectureEntity = memberLectureRepository.findByLectureLectureNoAndMemberMemberNo(lectureNo, memberNo);
        int memberLectureNo = memberLectureEntity.getMemberLectureNo();
        todo.setMemberLectureNo(memberLectureNo);
        ToDo todoEntity = modelMapper.map(todo, ToDo.class);
        todoRepository.save(todoEntity);
    }

    @Override
    @Transactional
    public boolean modifyTodo(String content, int todoNo) {

        ToDo todo = todoRepository.findById(todoNo).get();
        System.out.println("content = " + content);
        todo.setContent(content);
        todoRepository.save(todo);

        return true;
    }

    @Override
    @Transactional
    public boolean removeTodo(int todoNo) {

        todoRepository.deleteById(todoNo);

        return true;
    }


}
