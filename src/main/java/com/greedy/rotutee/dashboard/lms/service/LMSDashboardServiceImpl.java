package com.greedy.rotutee.dashboard.lms.service;

import com.greedy.rotutee.dashboard.lms.dto.*;
import com.greedy.rotutee.dashboard.lms.entity.LMSBoard;
import com.greedy.rotutee.dashboard.lms.entity.LMSLatelyViewClass;
import com.greedy.rotutee.dashboard.lms.entity.ToDo;
import com.greedy.rotutee.dashboard.lms.repository.LMSBoardRepository;
import com.greedy.rotutee.dashboard.lms.repository.LMSLatelyViewRepository;
import com.greedy.rotutee.dashboard.lms.repository.ToDoRepository;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardMemberDTO;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardMember;
import com.greedy.rotutee.dashboard.mypage.entity.MyPageMemberLecture;
import com.greedy.rotutee.dashboard.mypage.repository.DashboardMemberRepository;
import com.greedy.rotutee.dashboard.mypage.repository.MypageMemberLectureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.service
 * fileName : LMSDashboardServiceImpl
 * author : SeoYoung
 * date : 2022-04-19
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */
@Service
public class LMSDashboardServiceImpl implements LMSDashboardService{

    private ToDoRepository toDoRepository;
    private LMSBoardRepository boardRepository;
    private DashboardMemberRepository memberRepository;
    private MypageMemberLectureRepository mypageMemberLectureRepository;
    private LMSLatelyViewRepository lmsLatelyViewRepository;
    private ModelMapper modelMapper;

    @Autowired
    public LMSDashboardServiceImpl(ToDoRepository toDoRepository, LMSBoardRepository boardRepository, DashboardMemberRepository memberRepository, MypageMemberLectureRepository mypageMemberLectureRepository, LMSLatelyViewRepository lmsLatelyViewRepository, ModelMapper modelMapper) {
        this.toDoRepository = toDoRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
        this.mypageMemberLectureRepository = mypageMemberLectureRepository;
        this.lmsLatelyViewRepository = lmsLatelyViewRepository;


        this.modelMapper = modelMapper;
    }

    /**
     * methodName : findLMSDashboard
     * author : SeoYoung Kim
     * description :
     *
     * @param todo
     * @return lms dashboard dto
     */
    @Override
    public LMSDashboardDTO findLMSDashboard(ToDoDTO todo) {

        LMSDashboardDTO dashboard = new LMSDashboardDTO();
        int lectureNo = todo.getLectureNo();
        int memberNo = todo.getMemberNo();

        /* ToDo 조회 */
        List<ToDo> todoEntities = toDoRepository.findByLectureNoOrderByTodoNoAsc(lectureNo);
        List<ToDoDTO> todos = todoEntities.stream().map(Lms_ToDo -> modelMapper.map(Lms_ToDo, ToDoDTO.class)).collect(Collectors.toList());
        /*ToDo 진행률 계산 --보류*/
        List<Integer> progress = getProgress(todoEntities);
        /* 공지사항 조회 */
        List<LMSNoticeBoardDTO> notices = getNotices(lectureNo);
        /* 일반게시판 조회 */
        List<LMSNormalBoardDTO> normals = getNormals(lectureNo);
        /*최근 시청강의 조회 */
        List<LMSLatelyViewDTO> watching = getWatching(lectureNo, memberNo);
        /* 프로필 조회 */
        DashboardMemberDTO member = getProfile(memberNo);
        /* 수강번호 조회 */
        int memberLectureNo = getMemberLectureNo(lectureNo, memberNo);

        dashboard.setTodos(todos);
        dashboard.setNoticeBoards(notices);
        dashboard.setNormalBoards(normals);
        dashboard.setWatching(watching);
        dashboard.setMember(member);
        dashboard.setMemberLectureNo(memberLectureNo);

        return dashboard;

    }

    private int getMemberLectureNo(int lectureNo, int memberNo) {

        MyPageMemberLecture memberLecture =  mypageMemberLectureRepository.findByLectureLectureNoAndMemberMemberNo(lectureNo, memberNo);
        int memberLectureNo = memberLecture.getMemberLectureNo();

        return memberLectureNo;
    }

    private List<Integer> getProgress(List<ToDo> todoEntities) {

        List<Integer> progress = new ArrayList<>();


        return progress;
    }

    private List<LMSLatelyViewDTO> getWatching(int lectureNo, int memberNo) {

        System.out.println("lectureNo = " + lectureNo);
        System.out.println("memberNo = " + memberNo);
        List<MyPageMemberLecture> memberLectures = mypageMemberLectureRepository.findAll();
        List<LMSLatelyViewClass> latelyViewEntities =  new ArrayList<>();
        MyPageMemberLecture memberLecture = mypageMemberLectureRepository.findByLectureLectureNoAndMemberMemberNo(lectureNo, memberNo);
        if(memberLecture == null) {
            latelyViewEntities = null;
        } else {
            int memberLectureNo = memberLecture.getMemberLectureNo();
            latelyViewEntities = lmsLatelyViewRepository.findByMemberLectureNoOrderByTimeNoDesc(memberLectureNo);
        }

        //한페이지에 볼 수 있는 개수
        int numberOfViews = 6;
        List<LMSLatelyViewDTO> watching = new ArrayList<>();
        if(latelyViewEntities == null){
            watching = null;
        } else if(latelyViewEntities.size() > numberOfViews) {
            for(int i = 0; i < numberOfViews; i++){
                LMSLatelyViewClass watchEntity = latelyViewEntities.get(i);
                LMSLatelyViewDTO watch = modelMapper.map(watchEntity, LMSLatelyViewDTO.class);
                watching.add(watch);
            }
        } else if(latelyViewEntities.size() <= numberOfViews) {
            watching = latelyViewEntities.stream().map(Lms_LatelyViewClass -> modelMapper.map(Lms_LatelyViewClass, LMSLatelyViewDTO.class)).collect(Collectors.toList());
        }
        return watching;
    }

    private List<LMSNoticeBoardDTO> getNotices(int lectureNo) {

        int categoryNo = 10;
        List<LMSBoard> noticeEntities = boardRepository.findByCategoryNoAndLectureNoOrderByBoardNoDesc(categoryNo, lectureNo);
        int numberOfViews = 4;
        List<LMSNoticeBoardDTO> notices = new ArrayList<>();
        if(noticeEntities.size() > numberOfViews){
            for(int i = 0; i < numberOfViews; i++){
                LMSBoard boardEntity = noticeEntities.get(i);
                LMSNoticeBoardDTO board = modelMapper.map(boardEntity, LMSNoticeBoardDTO.class);
                notices.add(board);
            }
        } else if(noticeEntities.size() <= numberOfViews) {
            if(noticeEntities == null){
                noticeEntities = null;
            } else {
                notices = noticeEntities.stream().map(Lms_Board -> modelMapper.map(Lms_Board, LMSNoticeBoardDTO.class)).collect(Collectors.toList());
            }
        }

        return notices;
    }

    private List<LMSNormalBoardDTO> getNormals(int lectureNo) {

        int categoryNo = 9;
        List<LMSBoard> NormalEntities = boardRepository.findByCategoryNoAndLectureNoOrderByBoardNoDesc(categoryNo, lectureNo);
        int numberOfViews = 4;
        List<LMSNormalBoardDTO> normals = new ArrayList<>();
        if(NormalEntities.size() > numberOfViews){
            for(int i = 0; i < numberOfViews; i++){
                LMSBoard boardEntity = NormalEntities.get(i);
                LMSNormalBoardDTO board = modelMapper.map(boardEntity, LMSNormalBoardDTO.class);
                normals.add(board);
            }
        } else if(NormalEntities.size() <= numberOfViews) {
            if(NormalEntities == null) {
                NormalEntities = null;
            }
            normals = NormalEntities.stream().map(Lms_Board -> modelMapper.map(Lms_Board, LMSNormalBoardDTO.class)).collect(Collectors.toList());
        }

        return normals;
    }

    private DashboardMemberDTO getProfile(int memberNo) {
        DashboardMember memberEntity = memberRepository.findById(memberNo).get();
        DashboardMemberDTO member = modelMapper.map(memberEntity, DashboardMemberDTO.class);

        return member;
    }


}
