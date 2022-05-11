package com.greedy.rotutee.dashboard.lms.model.service;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.dashboard.lms.dto.LMSLatelyViewDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSNormalBoardDTO;
import com.greedy.rotutee.dashboard.lms.dto.LMSNoticeBoardDTO;
import com.greedy.rotutee.dashboard.lms.entity.LMSBoard;
import com.greedy.rotutee.dashboard.lms.entity.LMSLatelyViewClass;
import com.greedy.rotutee.dashboard.lms.entity.ToDo;
import com.greedy.rotutee.dashboard.lms.repository.LMSBoardRepository;
import com.greedy.rotutee.dashboard.lms.repository.LMSLatelyViewRepository;
import com.greedy.rotutee.dashboard.lms.repository.LMSWatchingRepository;
import com.greedy.rotutee.dashboard.lms.repository.ToDoRepository;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardBoardDTO;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardLectureWatchDTO;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardLectureWatch;
import com.greedy.rotutee.dashboard.mypage.entity.MyPageMemberLecture;
import com.greedy.rotutee.dashboard.mypage.repository.MypageMemberLectureRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class LMSDashboardServiceTest {

    @Autowired
    private ToDoRepository toDoRepository;
    @Autowired
    private LMSBoardRepository boardRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LMSWatchingRepository watchingRepository;
    @Autowired
    private MypageMemberLectureRepository memberLectureRepository;
    @Autowired
    private LMSLatelyViewRepository lmsLatelyViewRepository;

    @Test
    public void init() {
        assertNotNull(toDoRepository);
        assertNotNull(boardRepository);
    }

    @Test
    public void 투두_조회_확인() {

        //given
        int lectureNo = 6;
        List<ToDo> todos = toDoRepository.findByLectureNoOrderByTodoNoAsc(lectureNo);
        todos.forEach(System.out::println);

    }
    @Test
    public void 공지사항_조회_확인() {

        //given
        int categoryNo = 10;
        int lectureNo = 36;

        //when
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

        //then
        for (LMSNoticeBoardDTO notice : notices) {
            System.out.println("notice = " + notice);
        }
//        assertEquals(notices.size(), 4);

    }

    @Test
    public void 일반게시판_조회() {

        //given
        int lectureNo = 36;
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

        //then
        for (LMSNormalBoardDTO normal : normals) {
            System.out.println("normal = " + normal);
        }
    }

//    @Test
//    public void 내가_시청한_영상_조회() {
//        //given
//        int lectureNo = 6;
//        int memberNo = 27;
//
//        //when
//        List<DashboardLectureWatch> watchingEntities = watchingRepository.findByLectureLectureNoAndMemberNoOrderByWatchNoDesc(lectureNo, memberNo);
//        int numberOfViews = 6;
//        List<DashboardLectureWatchDTO> watching = new ArrayList<>();
//        if(watchingEntities.size() > numberOfViews){
//            for(int i = 0; i < numberOfViews; i++){
//                DashboardLectureWatch watchEntity = watchingEntities.get(i);
//                DashboardLectureWatchDTO watch = modelMapper.map(watchEntity, DashboardLectureWatchDTO.class);
//                watching.add(watch);
//            }
//        } else if(watchingEntities.size() <= numberOfViews) {
//            watching = watchingEntities.stream().map(dashboardLectureWatch -> modelMapper.map(dashboardLectureWatch, DashboardLectureWatchDTO.class)).collect(Collectors.toList());
//        }
//
//        //then
//        assertNotNull(watching);
//        for (DashboardLectureWatchDTO dashboardLectureWatchDTO : watching) {
//            System.out.println("dashboardLectureWatchDTO = " + dashboardLectureWatchDTO);
//        }
//    }

    @Test
    public void 투두_진행률_계산() {
        //given
        int lectureNo = 6;
        List<ToDo> todos = toDoRepository.findByLectureNoOrderByTodoNoAsc(lectureNo);
        for (ToDo todo : todos) {
            System.out.println("todo = " + todo);
        }
        LocalDate nowDate = LocalDate.now();
        Date now = new Date();
        LocalDateTime nowTime = LocalDateTime.now();
        System.out.println("now = " + nowDate);
        System.out.println("nowTime = " + nowTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        System.out.println("오늘 날짜 = " + sdf.format(now));
        //when

    }

    @Test
    public void 최근_시청한_영상조회() {

        //given
        int lectureNo = 6;
        int memberNo = 27;

        MyPageMemberLecture memberLecture = memberLectureRepository.findByLectureLectureNoAndMemberMemberNo(lectureNo, memberNo);
        int memberLectureNo = memberLecture.getMemberLectureNo();
        System.out.println("memberLectureNo = " + memberLectureNo);
        List<LMSLatelyViewClass> latelyViewEntities = lmsLatelyViewRepository.findByMemberLectureNoOrderByTimeNoDesc(memberLectureNo);


        for (LMSLatelyViewClass latelyViewEntity : latelyViewEntities) {
            System.out.println("latelyViewEntity = " + latelyViewEntity);
        }
        //한페이지에 볼 수 있는 개수
        int numberOfViews = 6;
        List<LMSLatelyViewDTO> watching = new ArrayList<>();
        if(latelyViewEntities.size() > numberOfViews){
            for(int i = 0; i < numberOfViews; i++){
                LMSLatelyViewClass watchEntity = latelyViewEntities.get(i);
                LMSLatelyViewDTO watch = modelMapper.map(watchEntity, LMSLatelyViewDTO.class);
                watching.add(watch);
            }
        } else if(latelyViewEntities.size() <= numberOfViews) {
            watching = latelyViewEntities.stream().map(Lms_LatelyViewClass -> modelMapper.map(Lms_LatelyViewClass, LMSLatelyViewDTO.class)).collect(Collectors.toList());
        }

        for (LMSLatelyViewDTO lmsLatelyViewDTO : watching) {
            System.out.println("lmsLatelyViewDTO = " + lmsLatelyViewDTO);
        }

    }

    @Test
    public void 투두_조회() {

        //given
        int memberNo = 29;
        int lectureNo = 9;

        MyPageMemberLecture memberLecture = memberLectureRepository.findByLectureLectureNoAndMemberMemberNo(lectureNo, memberNo);

        System.out.println("memberLecture = " + memberLecture);
    }



}
