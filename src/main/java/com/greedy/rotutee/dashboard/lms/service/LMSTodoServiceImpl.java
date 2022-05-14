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
 * description : LMS Todo 비즈니스 로직
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

    /**
     * methodName : registTodo
     * author : SeoYoung Kim
     * description : Todo 등록
     *
     * @param todo 등록할 todo 정보
     */
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

    /**
     * methodName : modifyTodo
     * author : SeoYoung Kim
     * description : Todo 수정
     *
     * @param content 수정할 Todo 내용
     * @param todoNo 수정할 투두 번호
     * @return boolean
     */
    @Override
    @Transactional
    public boolean modifyTodo(String content, int todoNo) {

        ToDo todo = todoRepository.findById(todoNo).get();
        System.out.println("content = " + content);
        todo.setContent(content);
        todoRepository.save(todo);

        return true;
    }

    /**
     * methodName : removeTodo
     * author : SeoYoung Kim
     * description : Todo 삭제
     *
     * @param todoNo
     * @return boolean
     */
    @Override
    @Transactional
    public boolean removeTodo(int todoNo) {

        todoRepository.deleteById(todoNo);

        return true;
    }

    /**
     * methodName : modifyStatus
     * author : SeoYoung Kim
     * description : 투두 완료 여부 수정
     *
     * @param status 투두 현재 상태
     * @param todoNo 투두번호
     */
    @Override
    @Transactional
    public void modifyStatus(String status, int todoNo) {

        ToDo todo = todoRepository.findById(todoNo).get();
        todo.setAchievementStatus(status);
        todoRepository.save(todo);

    }


}
