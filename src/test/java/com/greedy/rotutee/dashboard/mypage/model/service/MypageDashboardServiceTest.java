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
    public void 대시보드_멤버_조회_확인() {
        //given
        int memberNo = 27;

        //when
        DashboardMember memberEntity = memberRepository.findById(memberNo).get();
        DashboardMemberDTO member = modelMapper.map(memberEntity, DashboardMemberDTO.class);

        //then
        assertNotNull(member);
        System.out.println("멤버확인 : " + member);
    }

    @Test
    public void 대시보드_보드리스트_조회_확인() {
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
    public void 대시보드_수강바구니_조회_확인() {
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
    public void 대시보드_알림_조회_확인() {
        //given
        int memberNo = 27;

        //when
        /* 알림 조회용 */
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
    public void 대시보드_최근_학습중인_강의_조회_확인() {
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
    public void 대시보드_시청완료강의_로직_확인() {
        //given
        int memberNo = 27;

        //when
        /* 회원별 수강하고있는 강의항목 조회 */
        String jpql1 = "SELECT memberlecture FROM Mypage_MemberLecture memberlecture " +
                "WHERE memberlecture.member.memberNo = :memberNo ";
        Query query1 = entityManager.createQuery(jpql1, MyPageMemberLecture.class)
                .setParameter("memberNo", memberNo);

        List<MyPageMemberLecture> memberLectureEntityList = query1.getResultList();

        System.out.println("memberLectureEntityList = " + memberLectureEntityList);
        /* 회원별 수강하고 있는 수강번호 리스트에 담음*/
        List<Integer> memberLectureNoList = new ArrayList<>();
        for(int i = 0; i < memberLectureEntityList.size(); i++){

            int memberLectureNo = memberLectureEntityList.get(i).getMemberLectureNo();
            memberLectureNoList.add(memberLectureNo);
        }

        /* 수강번호로 누적시간 테이블에서 회원이 듣고있는 강의의 모든 클래스 상태 읽어옴 */
        String jpql2 = "SELECT complete FROM Dashboard_Completedlecture  as complete " +
                "WHERE complete.memberLecture.memberLectureNo = :memberLectureNo ";

        /* 회원별 수강번호에 따른 클래스리스트가 담겨있는 리스트*/
        List<List> completedLectureCategoryEntityList = new ArrayList<>();
        for(int i = 0; i < memberLectureNoList.size(); i++){
            int memberLectureNo = memberLectureNoList.get(i);
            List<DashboardCompletedLecture> completedLectureEntityList = entityManager.createQuery(jpql2, DashboardCompletedLecture.class)
                    .setParameter("memberLectureNo", memberLectureNo)
                    .getResultList();
            completedLectureCategoryEntityList.add(completedLectureEntityList);
        }

        /* 한 멤버가 듣고있는 강의의 수만큼 반복문 */
        List<DashboardLectureDTO> completedLectureInfoList = new ArrayList<>();
        for(int i = 0; i <completedLectureCategoryEntityList.size(); i++){
            /* 한 강의의 클래스 리스트*/
            List<DashboardCompletedLecture> completedLectureList = completedLectureCategoryEntityList.get(i);
            /* 한 강의당 총 클래스 수 */
            int totalClassCountOfLecture = completedLectureList.size();

            /* 한 강의당 완료된 클래스 개수(completedStatus : Y의 총 개수) */
            int CompletedClassCount = 0;
            for(int j = 0; j < completedLectureList.size(); j++){
                if(completedLectureList.get(j).getWatchedStatus().equals("Y ")){
                    CompletedClassCount += 1;
                }
                /* 클래스의 개수와 완료된 클래스의 개수가 동일할 시 시청 완료, 리스트에 넣어서 view로 return */
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
    public void 전체_대시보드_조회_확인() {
        //given
        int memberNo = 27;

        //when
        MypageDashboardDTO dashboard = new MypageDashboardDTO();

        /* 프로필 조회용 */
        DashboardMember memberEntity = memberRepository.findById(memberNo).get();
        DashboardMemberDTO member = modelMapper.map(memberEntity, DashboardMemberDTO.class);

        /* 작성게시물 조회용 */
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

        /* 수강바구니 조회용 */
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

        /* 알림 조회용 */
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

        /* 최근 시청강의 조회용 */
        String watchJpql = "SELECT watch " +
                "FROM dashboardLectureWatch as watch " +
                "WHERE watch.watchedDate in (SELECT Max(w.watchedDate) FROM dashboardLectureWatch as w " +
                "WHERE w.memberNo = :memberNo " +
                "GROUP BY w.lecture)";

        Query query = entityManager.createQuery(watchJpql, DashboardLectureWatch.class)
                .setParameter("memberNo", memberNo);
        List<DashboardLectureWatch> watchEntityList = query.getResultList();
        List<DashboardLectureWatchDTO> watchList = watchEntityList.stream().map(dashboardLectureWatch -> modelMapper.map(dashboardLectureWatch, DashboardLectureWatchDTO.class)).collect(Collectors.toList());

        /* 시청 완료강의 조회용 */
        /* 회원별 수강하고있는 강의항목 조회 */
        String memberLectureJpql = "SELECT memberlecture FROM Mypage_MemberLecture memberlecture " +
                "WHERE memberlecture.member.memberNo = :memberNo ";
        Query query1 = entityManager.createQuery(memberLectureJpql, MyPageMemberLecture.class)
                .setParameter("memberNo", memberNo);

        List<MyPageMemberLecture> memberLectureEntityList = query1.getResultList();

        System.out.println("memberLectureEntityList = " + memberLectureEntityList);
        /* 회원별 수강하고 있는 수강번호 리스트에 담음 */
        List<Integer> memberLectureNoList = new ArrayList<>();
        for(int i = 0; i < memberLectureEntityList.size(); i++){

            int memberLectureNo = memberLectureEntityList.get(i).getMemberLectureNo();
            memberLectureNoList.add(memberLectureNo);
        }

        /* 수강번호로 누적시간 테이블에서 회원이 듣고있는 강의의 모든 클래스 상태 읽어옴 */
        String jpql2 = "SELECT complete FROM Dashboard_Completedlecture  as complete " +
                "WHERE complete.memberLecture.memberLectureNo = :memberLectureNo ";

        /* 회원별 수강번호에 따른 클래스리스트가 담겨있는 리스트*/
        List<List> completedLectureCategoryEntityList = new ArrayList<>();
        for(int i = 0; i < memberLectureNoList.size(); i++){
            int memberLectureNo = memberLectureNoList.get(i);
            List<DashboardCompletedLecture> completedLectureEntityList = entityManager.createQuery(jpql2, DashboardCompletedLecture.class)
                    .setParameter("memberLectureNo", memberLectureNo)
                    .getResultList();
            completedLectureCategoryEntityList.add(completedLectureEntityList);
        }

        /* 한 멤버가 듣고있는 강의의 수만큼 반복문 */
        List<DashboardLectureDTO> completedLectureInfoList = new ArrayList<>();
        for(int i = 0; i <completedLectureCategoryEntityList.size(); i++){
            /* 한 강의의 클래스 리스트 */
            List<DashboardCompletedLecture> completedLectureList = completedLectureCategoryEntityList.get(i);
            /* 한 강의당 총 클래스 수 */
            int totalClassCountOfLecture = completedLectureList.size();

            /* 한 강의당 완료된 클래스 개수(completedStatus : Y의 총 개수) */
            int CompletedClassCount = 0;
            for(int j = 0; j < completedLectureList.size(); j++){
                if(completedLectureList.get(j).getWatchedStatus().equals("Y ")){
                    CompletedClassCount += 1;
                }
                /* 클래스의 개수와 완료된 클래스의 개수가 동일할 시 시청 완료, 리스트에 넣어서 view로 return */
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
    public void 진행중인_강의_조회_확인() {
        //given
        int memberNo = 29;
        //when
        String myLectureJpql = "SELECT lecture FROM dashboardLecture lecture " +
                "WHERE lecture.memberNo = :memberNo " +
                "AND lecture.approvalStatus = '승인' ";
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

    @Test
    public void 수강테이블_조회_확인() {

        //given
        int memberNo = 27;
        DashboardMember member = new DashboardMember();
        member.setMemberNo(memberNo);
        String query = "SELECT\n" +
                "       A.LECTURE_NO\n" +
                "     , A.LECTURE_NAME\n" +
                "     , A.LECTURE_PRICE\n" +
                "     , A.LECTURE_LEVEL\n" +
                "     , A.LECTURE_SUMMARY\n" +
                "     , A.LECTURE_DETAILS\n" +
                "     , A.REVISION_HISTORY\n" +
                "     , A.LECTURE_APPROVAL_STATUS\n" +
                "     , A.LECTURE_OPENING_DATE\n" +
                "     , A.MEMBER_NO\n" +
                "     , A.APPLICATION_DATE\n" +
                "     , A.APPLICATION_DIVISION\n" +
                "     , A.LECTURE_CATEGORY_NO\n" +
                "  FROM TBL_LECTURE A\n" +
                " WHERE A.LECTURE_NO IN (SELECT\n" +
                "                               B.LECTURE_NO\n" +
                "                          FROM TBL_MEMBER_LECTURE B\n" +
                "                          JOIN TBL_LECTURE C ON (C.LECTURE_NO = B.LECTURE_NO)\n" +
                "                         WHERE B.MEMBER_NO = 27\n" +
                "                       )";
        Query nativeQuery = entityManager.createNativeQuery(query, "mypageMemberLectureMapping");
        //when
        List<DashboardLecture> learningEntities = nativeQuery.getResultList();
        learningEntities.forEach(System.out::println);
    }
}
