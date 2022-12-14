package com.greedy.rotutee.dashboard.mypage.model.service;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.*;
import com.greedy.rotutee.dashboard.mypage.entity.*;
import com.greedy.rotutee.dashboard.mypage.repository.*;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class MypageDashboardServiceTest {

    @Autowired
    private DashboardMemberRepository memberRepository;
    @Autowired
    private DashboardBoardRepository boardRepository;
    @Autowired
    private DashboardBasketRepository basketRepository;
    @Autowired
    private DashboardNoticeRepository noticeRepository;
    @Autowired
    private DashboardLectureRepository lectureRepository;
    @Autowired
    private DashboardLectureWatchRepository lectureWatchRepository;
    @Autowired
    private DashboardCompletedLectureRepository completedLectureRepository;
    @Autowired
    private MypageMemberLectureRepository memberLectureRepository;
    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void init() {
        assertNotNull(memberRepository);
        assertNotNull(modelMapper);
        assertNotNull(boardRepository);
        assertNotNull(basketRepository);
        assertNotNull(entityManager);
        assertNotNull(memberLectureRepository);

    }

    @Test
    public void ????????????_??????_??????_??????() {
        //given
        int memberNo = 27;

        //when
        DashboardMember memberEntity = memberRepository.findById(memberNo).get();
        DashboardMemberDTO member = modelMapper.map(memberEntity, DashboardMemberDTO.class);

        //then
        assertNotNull(member);
        System.out.println("???????????? : " + member);
    }

    @Test
    public void ????????????_???????????????_??????_??????() {
        //given
        int memberNo = 27;

        //when
        String boardJpql = "SELECT board " +
                "FROM dashboardBoard as board " +
                "WHERE board.memberNo = :memberNo " +
                "ORDER BY board.createdDate DESC ";
        Query boardQuery = entityManager.createQuery(boardJpql, DashboardBoard.class)
                .setParameter("memberNo", memberNo);
        List<DashboardBoard> entityBoardList = boardQuery.getResultList();
//        List<DashboardBoard> entityBoardList = boardRepository.findBymemberNo(memberNo);
        List<DashboardBoardDTO> boardList = new ArrayList<>();
        if(entityBoardList.size() > 4){
            for(int i = 0; i < 4; i++){
                DashboardBoard boardEntity = entityBoardList.get(i);
                DashboardBoardDTO board = modelMapper.map(boardEntity, DashboardBoardDTO.class);
                boardList.add(board);
            }
        } else if(entityBoardList.size() <= 4){
            boardList = entityBoardList.stream().map(dashboardBoard -> modelMapper.map(dashboardBoard, DashboardBoardDTO.class)).collect(Collectors.toList());
        }

        //then
        assertNotNull(boardList);
        boardList.forEach(System.out::println);
    }

    @Test
    public void ????????????_???????????????_??????_??????() {
        //given
        int memberNo = 27;

        //when
        String basketJpql = "SELECT basket " +
                "FROM dashboardBasket as basket " +
                "WHERE basket.member.memberNo = :memberNo ";
        Query basketQuery = entityManager.createQuery(basketJpql, DashboardBasket.class)
                .setParameter("memberNo", memberNo);
        List<DashboardBasket> entityBasketList = basketQuery.getResultList();
//        DashboardBasket basket = new DashboardBasket();
//        basket.setMember(memberEntity);
        List<DashboardBasketDTO> basketList = new ArrayList<>();
        if(entityBasketList.size() > 4){
            for(int i = 0; i < 4; i++){
                DashboardBasket basketEntity = entityBasketList.get(i);
                DashboardBasketDTO basket = modelMapper.map(basketEntity, DashboardBasketDTO.class);
                basketList.add(basket);
            }
        } else if(entityBasketList.size() <= 4){
            basketList = entityBasketList.stream().map(dashboardBasket -> modelMapper.map(dashboardBasket, DashboardBasketDTO.class)).collect(Collectors.toList());
        }
        //then
        assertNotNull(basketList);
        basketList.forEach(System.out::println);
    }

    @Test
    public void ????????????_??????_??????_??????() {
        //given
        int memberNo = 27;

        //when
        /* ?????? ????????? */
        String noticeJpql = "SELECT notice " +
                "FROM dashboardNotice as notice " +
                "WHERE notice.memberNo = :memberNo " +
                "ORDER BY notice.noticedTime DESC";
        Query noticeQuery = entityManager.createQuery(noticeJpql, DashboardNotice.class)
                .setParameter("memberNo", memberNo);
        List<DashboardNotice> entityNoticeList = noticeQuery.getResultList();
//        DashboardBasket basket = new DashboardBasket();
//        basket.setMember(memberEntity);
        List<DashboardNoticeDTO> noticeList = new ArrayList<>();
        if(entityNoticeList.size() > 4){
            for(int i = 0; i < 4; i++){
                DashboardNotice noticeEntity = entityNoticeList.get(i);
                DashboardNoticeDTO notice = modelMapper.map(noticeEntity, DashboardNoticeDTO.class);
                noticeList.add(notice);
            }
        } else if(entityNoticeList.size() <= 4){
            noticeList = entityNoticeList.stream().map(dashboardNotice -> modelMapper.map(dashboardNotice, DashboardNoticeDTO.class)).collect(Collectors.toList());
        }

        //then
        assertNotNull(noticeList);
        noticeList.forEach(System.out::println);
    }

    @Test
    public void ????????????_??????_????????????_??????_??????_??????() {
        //given
        int memberNo = 27;

        //when
        String jpql = "SELECT watch " +
                "FROM dashboardLectureWatch as watch " +
                "WHERE watch.watchedDate in (SELECT Max(w.watchedDate) FROM dashboardLectureWatch as w " +
                "WHERE w.memberNo = :memberNo " +
                "GROUP BY w.lecture)";

        Query query = entityManager.createQuery(jpql, DashboardLectureWatch.class)
                .setParameter("memberNo", memberNo);
        List<DashboardLectureWatch> watchEntityList = query.getResultList();
        List<DashboardLectureWatchDTO> watchList = watchEntityList.stream().map(dashboardLectureWatch -> modelMapper.map(dashboardLectureWatch, DashboardLectureWatchDTO.class)).collect(Collectors.toList());

        //then
        assertNotNull(watchList);
        watchEntityList.forEach(System.out::println);

    }

    @Test
    public void ????????????_??????????????????_??????_??????() {
        //given
        int memberNo = 27;

        //when
        /* ????????? ?????????????????? ???????????? ?????? */
        String jpql1 = "SELECT memberlecture FROM Mypage_MemberLecture memberlecture " +
                "WHERE memberlecture.member.memberNo = :memberNo ";
        Query query1 = entityManager.createQuery(jpql1, MyPageMemberLecture.class)
                .setParameter("memberNo", memberNo);

        List<MyPageMemberLecture> memberLectureEntityList = query1.getResultList();

        System.out.println("memberLectureEntityList = " + memberLectureEntityList);
        /* ????????? ???????????? ?????? ???????????? ???????????? ??????*/
        List<Integer> memberLectureNoList = new ArrayList<>();
        for(int i = 0; i < memberLectureEntityList.size(); i++){

            int memberLectureNo = memberLectureEntityList.get(i).getMemberLectureNo();
            memberLectureNoList.add(memberLectureNo);
        }

        /* ??????????????? ???????????? ??????????????? ????????? ???????????? ????????? ?????? ????????? ?????? ????????? */
        String jpql2 = "SELECT complete FROM Dashboard_Completedlecture  as complete " +
                "WHERE complete.memberLecture.memberLectureNo = :memberLectureNo ";

        /* ????????? ??????????????? ?????? ????????????????????? ???????????? ?????????*/
        List<List> completedLectureCategoryEntityList = new ArrayList<>();
        for(int i = 0; i < memberLectureNoList.size(); i++){
            int memberLectureNo = memberLectureNoList.get(i);
            List<DashboardCompletedLecture> completedLectureEntityList = entityManager.createQuery(jpql2, DashboardCompletedLecture.class)
                    .setParameter("memberLectureNo", memberLectureNo)
                    .getResultList();
            completedLectureCategoryEntityList.add(completedLectureEntityList);
        }

        /* ??? ????????? ???????????? ????????? ????????? ????????? */
        List<DashboardLectureDTO> completedLectureInfoList = new ArrayList<>();
        for(int i = 0; i <completedLectureCategoryEntityList.size(); i++){
            /* ??? ????????? ????????? ?????????*/
            List<DashboardCompletedLecture> completedLectureList = completedLectureCategoryEntityList.get(i);
            /* ??? ????????? ??? ????????? ??? */
            int totalClassCountOfLecture = completedLectureList.size();

            /* ??? ????????? ????????? ????????? ??????(completedStatus : Y??? ??? ??????) */
            int CompletedClassCount = 0;
            for(int j = 0; j < completedLectureList.size(); j++){
                if(completedLectureList.get(j).getWatchedStatus().equals("Y ")){
                    CompletedClassCount += 1;
                }
                /* ???????????? ????????? ????????? ???????????? ????????? ????????? ??? ?????? ??????, ???????????? ????????? view??? return */
                if(totalClassCountOfLecture == CompletedClassCount){
                    DashboardLecture lectureInfoEntity = lectureRepository.findById(completedLectureList.get(j).getMemberLecture().getLecture().getLectureNo()).get();
                    DashboardLectureDTO lectureInfo = modelMapper.map(lectureInfoEntity, DashboardLectureDTO.class);
                    completedLectureInfoList.add(lectureInfo);
                }
            }

        }
        System.out.println("completedLectureInfoList = " + completedLectureInfoList);


        //then

    }

    @Test
    public void ??????_????????????_??????_??????() {
        //given
        int memberNo = 27;

        //when
        MypageDashboardDTO dashboard = new MypageDashboardDTO();

        /* ????????? ????????? */
        DashboardMember memberEntity = memberRepository.findById(memberNo).get();
        DashboardMemberDTO member = modelMapper.map(memberEntity, DashboardMemberDTO.class);

        /* ??????????????? ????????? */
        String boardJpql = "SELECT board " +
                "FROM dashboardBoard as board " +
                "WHERE board.memberNo = :memberNo " +
                "ORDER BY board.createdDate DESC ";
        Query boardQuery = entityManager.createQuery(boardJpql, DashboardBoard.class)
                .setParameter("memberNo", memberNo);
        List<DashboardBoard> entityBoardList = boardQuery.getResultList();
//        List<DashboardBoard> entityBoardList = boardRepository.findBymemberNo(memberNo);
        List<DashboardBoardDTO> boardList = new ArrayList<>();
        if(entityBoardList.size() > 4){
            for(int i = 0; i < 4; i++){
                DashboardBoard boardEntity = entityBoardList.get(i);
                DashboardBoardDTO board = modelMapper.map(boardEntity, DashboardBoardDTO.class);
                boardList.add(board);
            }
        } else if(entityBoardList.size() <= 4){
            boardList = entityBoardList.stream().map(dashboardBoard -> modelMapper.map(dashboardBoard, DashboardBoardDTO.class)).collect(Collectors.toList());
        }

        /* ??????????????? ????????? */
        String basketJpql = "SELECT basket " +
                "FROM dashboardBasket as basket " +
                "WHERE basket.member.memberNo = :memberNo ";
        Query basketQuery = entityManager.createQuery(basketJpql, DashboardBasket.class)
                .setParameter("memberNo", memberNo);
        List<DashboardBasket> entityBasketList = basketQuery.getResultList();
//        DashboardBasket basket = new DashboardBasket();
//        basket.setMember(memberEntity);
        List<DashboardBasketDTO> basketList = new ArrayList<>();
        if(entityBasketList.size() > 4){
            for(int i = 0; i < 4; i++){
                DashboardBasket basketEntity = entityBasketList.get(i);
                DashboardBasketDTO basket = modelMapper.map(basketEntity, DashboardBasketDTO.class);
                basketList.add(basket);
            }
        } else if(entityBasketList.size() <= 4){
            basketList = entityBasketList.stream().map(dashboardBasket -> modelMapper.map(dashboardBasket, DashboardBasketDTO.class)).collect(Collectors.toList());
        }

        /* ?????? ????????? */
        String noticeJpql = "SELECT notice " +
                "FROM dashboardNotice as notice " +
                "WHERE notice.memberNo = :memberNo " +
                "ORDER BY notice.noticedTime DESC";
        Query noticeQuery = entityManager.createQuery(noticeJpql, DashboardNotice.class)
                .setParameter("memberNo", memberNo);
        List<DashboardNotice> entityNoticeList = noticeQuery.getResultList();
//        DashboardBasket basket = new DashboardBasket();
//        basket.setMember(memberEntity);
        List<DashboardNoticeDTO> noticeList = new ArrayList<>();
        if(entityNoticeList.size() > 4){
            for(int i = 0; i < 4; i++){
                DashboardNotice noticeEntity = entityNoticeList.get(i);
                DashboardNoticeDTO notice = modelMapper.map(noticeEntity, DashboardNoticeDTO.class);
                noticeList.add(notice);
            }
        } else if(entityNoticeList.size() <= 4){
            noticeList = entityNoticeList.stream().map(dashboardNotice -> modelMapper.map(dashboardNotice, DashboardNoticeDTO.class)).collect(Collectors.toList());
        }

        /* ?????? ???????????? ????????? */
        String watchJpql = "SELECT watch " +
                "FROM dashboardLectureWatch as watch " +
                "WHERE watch.watchedDate in (SELECT Max(w.watchedDate) FROM dashboardLectureWatch as w " +
                "WHERE w.memberNo = :memberNo " +
                "GROUP BY w.lecture)";

        Query query = entityManager.createQuery(watchJpql, DashboardLectureWatch.class)
                .setParameter("memberNo", memberNo);
        List<DashboardLectureWatch> watchEntityList = query.getResultList();
        List<DashboardLectureWatchDTO> watchList = watchEntityList.stream().map(dashboardLectureWatch -> modelMapper.map(dashboardLectureWatch, DashboardLectureWatchDTO.class)).collect(Collectors.toList());

        /* ?????? ???????????? ????????? */
        /* ????????? ?????????????????? ???????????? ?????? */
        String memberLectureJpql = "SELECT memberlecture FROM Mypage_MemberLecture memberlecture " +
                "WHERE memberlecture.member.memberNo = :memberNo ";
        Query query1 = entityManager.createQuery(memberLectureJpql, MyPageMemberLecture.class)
                .setParameter("memberNo", memberNo);

        List<MyPageMemberLecture> memberLectureEntityList = query1.getResultList();

        System.out.println("memberLectureEntityList = " + memberLectureEntityList);
        /* ????????? ???????????? ?????? ???????????? ???????????? ?????? */
        List<Integer> memberLectureNoList = new ArrayList<>();
        for(int i = 0; i < memberLectureEntityList.size(); i++){

            int memberLectureNo = memberLectureEntityList.get(i).getMemberLectureNo();
            memberLectureNoList.add(memberLectureNo);
        }

        /* ??????????????? ???????????? ??????????????? ????????? ???????????? ????????? ?????? ????????? ?????? ????????? */
        String jpql2 = "SELECT complete FROM Dashboard_Completedlecture  as complete " +
                "WHERE complete.memberLecture.memberLectureNo = :memberLectureNo ";

        /* ????????? ??????????????? ?????? ????????????????????? ???????????? ?????????*/
        List<List> completedLectureCategoryEntityList = new ArrayList<>();
        for(int i = 0; i < memberLectureNoList.size(); i++){
            int memberLectureNo = memberLectureNoList.get(i);
            List<DashboardCompletedLecture> completedLectureEntityList = entityManager.createQuery(jpql2, DashboardCompletedLecture.class)
                    .setParameter("memberLectureNo", memberLectureNo)
                    .getResultList();
            completedLectureCategoryEntityList.add(completedLectureEntityList);
        }

        /* ??? ????????? ???????????? ????????? ????????? ????????? */
        List<DashboardLectureDTO> completedLectureInfoList = new ArrayList<>();
        for(int i = 0; i <completedLectureCategoryEntityList.size(); i++){
            /* ??? ????????? ????????? ????????? */
            List<DashboardCompletedLecture> completedLectureList = completedLectureCategoryEntityList.get(i);
            /* ??? ????????? ??? ????????? ??? */
            int totalClassCountOfLecture = completedLectureList.size();

            /* ??? ????????? ????????? ????????? ??????(completedStatus : Y??? ??? ??????) */
            int CompletedClassCount = 0;
            for(int j = 0; j < completedLectureList.size(); j++){
                if(completedLectureList.get(j).getWatchedStatus().equals("Y ")){
                    CompletedClassCount += 1;
                }
                /* ???????????? ????????? ????????? ???????????? ????????? ????????? ??? ?????? ??????, ???????????? ????????? view??? return */
                if(totalClassCountOfLecture == CompletedClassCount){
                    DashboardLecture lectureInfoEntity = lectureRepository.findById(completedLectureList.get(j).getMemberLecture().getLecture().getLectureNo()).get();
                    DashboardLectureDTO lectureInfo = modelMapper.map(lectureInfoEntity, DashboardLectureDTO.class);
                    completedLectureInfoList.add(lectureInfo);
                }
            }
            dashboard.setMember(member);
            dashboard.setBoardList(boardList);
            dashboard.setBasketList(basketList);
            dashboard.setNoticeList(noticeList);
            dashboard.setWatchList(watchList);
            dashboard.setCompletedLectureInfoList(completedLectureInfoList);
        }
        //then
        assertNotNull(dashboard);
        dashboard.getMember();
        dashboard.getBoardList().forEach(System.out::println);
        dashboard.getBasketList().forEach(System.out::println);
        dashboard.getNoticeList().forEach(System.out::println);
        dashboard.getWatchList().forEach(System.out::println);
        dashboard.getCompletedLectureInfoList().forEach(System.out::println);
    }

    @Test
    public void ????????????_??????_??????_??????() {
        //given
        int memberNo = 29;
        //when
        String myLectureJpql = "SELECT lecture FROM dashboardLecture lecture " +
                "WHERE lecture.memberNo = :memberNo " +
                "AND lecture.approvalStatus = '??????' ";
        Query myLecturequery = entityManager.createQuery(myLectureJpql, DashboardLecture.class)
                .setParameter("memberNo", memberNo);
        List<DashboardLecture> lectureEntityList = myLecturequery.getResultList();

        List<DashboardLectureDTO> lectureList = new ArrayList<>();
        if(lectureEntityList.size() > 4){
            for(int i = 0; i < 4; i++){
                DashboardLecture lectureEntity = lectureEntityList.get(i);
                DashboardLectureDTO lecture = modelMapper.map(lectureEntity, DashboardLectureDTO.class);
                lectureList.add(lecture);
            }
        } else if(lectureEntityList.size() <= 4){
            lectureList = lectureEntityList.stream().map(dashboardLecture -> modelMapper.map(dashboardLecture, DashboardLectureDTO.class)).collect(Collectors.toList());
        }

        //then
        assertNotNull(lectureList);
        lectureList.forEach(System.out::println);

    }


}
