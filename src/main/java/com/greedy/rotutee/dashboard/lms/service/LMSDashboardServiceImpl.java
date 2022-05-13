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
     * description : Dashboard 관련 정보 조회 메소드
     *
     * @param todo
     * @return LMSDashboardDTO : Todo, 공지사항, 최근 시청강의, 프로필, 수강번호 조회
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

    /**
     * Gets member lecture no.
     * author : SeoYoung Kim
     * description : 수강번호를 return해주는 메소드
     *
     * @param lectureNo lms 대시보드 조회를 원하는 강의번호
     * @param memberNo  로그인한 회원 번호
     * @return memberLectureNo : 회원번호 별 강의번호를 담은 수강번호
     */
    private int getMemberLectureNo(int lectureNo, int memberNo) {

        MyPageMemberLecture memberLecture =  mypageMemberLectureRepository.findByLectureLectureNoAndMemberMemberNo(lectureNo, memberNo);
        int memberLectureNo = memberLecture.getMemberLectureNo();

        return memberLectureNo;
    }

    /**
     * Gets progress.
     * author : SeoYoung Kim
     * description : Todo 진행률을 계산하여 return 하는 메소드
     *
     * @param todoEntities the todo entities
     * @return the progress
     */
    private List<Integer> getProgress(List<ToDo> todoEntities) {

        List<Integer> progress = new ArrayList<>();


        return progress;
    }

    /**
     * Gets watching.
     * author : SeoYoung Kim
     * description : 최근 시청강의 4개의 정보를 return 해주는 메소드
     *
     * @param lectureNo lms 대시보드 조회를 원하는 강의번호
     * @param memberNo  로그인하고 있는 회원번호
     * @return watching 최근 시청강의 정보
     */
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

    /**
     * Gets notices.
     * author : SeoYoung Kim
     * description : 최근 게시된 공지사항 4개를 보여주는 메소드
     *
     * @param lectureNo 강의번호
     * @return notices : 최근 4개의 공지사항 정보
     */
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

    /**
     * Gets normals.
     * author : SeoYoung Kim
     * description : 최근 게시된 일반게시판 정보를 return 해주는 메소드
     *
     * @param lectureNo 강의번호
     * @return normals 최근 4개의 일반게시판 정보들
     */
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
