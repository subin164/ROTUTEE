package com.greedy.rotutee.dashboard.mypage.service;

import com.greedy.rotutee.dashboard.mypage.dto.tutee.*;
import com.greedy.rotutee.dashboard.mypage.dto.tutor.MypageTutorDTO;
import com.greedy.rotutee.dashboard.mypage.entity.*;
import com.greedy.rotutee.dashboard.mypage.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : com.greedy.rotutee.dashboard.mypage.service
 * fileName : MypageDashboardServiceImpl
 * author : SeoYoung
 * date : 2022-04-19
 * description : 마이페이지 대시보드 비지니스 로직 처리구현부
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-19 SeoYoung 최초 생성
 */
@Service
public class MypageDashboardServiceImpl implements MypageDashboardService{

    private DashboardMemberRepository memberRepository;
    private DashboardBasketRepository basketRepository;
    private DashboardBoardRepository boardRepository;
    private DashboardLectureRepository lectureRepository;
    private DashboardNoticeRepository noticeRepository;
    private DashboardLectureWatchRepository lectureWatchRepository;
    private ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public MypageDashboardServiceImpl(DashboardMemberRepository memberRepository, DashboardBasketRepository basketRepository, DashboardBoardRepository boardRepository, DashboardLectureRepository lectureRepository, DashboardNoticeRepository noticeRepository, DashboardLectureWatchRepository lectureWatchRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.basketRepository = basketRepository;
        this.boardRepository = boardRepository;
        this.lectureRepository = lectureRepository;
        this.noticeRepository = noticeRepository;
        this.lectureWatchRepository = lectureWatchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MypageDashboardDTO findTuteeDashboard(int memberNo) {

        MypageDashboardDTO dashboard = new MypageDashboardDTO();

        DashboardMemberDTO member = getProfile(memberNo);

        List<DashboardBoardDTO> boards = getBoards(memberNo);

        List<DashboardBasketDTO> baskets = getBaskets(memberNo);

        List<DashboardNoticeDTO> notices = getNotices(memberNo);

        List<DashboardLectureWatchDTO> watchList = getWatchList(memberNo);

        List<DashboardLectureDTO> completedLectures = getcompletedLectures(memberNo);


        dashboard.setMember(member);
        dashboard.setBoardList(boards);
        dashboard.setBasketList(baskets);
        dashboard.setNoticeList(notices);
        dashboard.setWatchList(watchList);
        dashboard.setCompletedLectureInfoList(completedLectures);
        return dashboard;
    }

    /**
     * Gets profile.
     * author : SeoYoung Kim
     * description :
     *
     * @param memberNo the member no
     * @return the profile
     */
    private DashboardMemberDTO getProfile(int memberNo) {
        DashboardMember memberEntity = memberRepository.findById(memberNo).get();
        DashboardMemberDTO member = modelMapper.map(memberEntity, DashboardMemberDTO.class);

        return member;
    }

    /**
     * Gets boards.
     * author : SeoYoung Kim
     * description :
     *
     * @param memberNo the member no
     * @return the boards
     */
    /* 작성게시물 조회용 */
    private List<DashboardBoardDTO> getBoards(int memberNo) {

        String jpql = "SELECT board " +
                "FROM dashboardBoard as board " +
                "WHERE board.memberNo = :memberNo " +
                "ORDER BY board.createdDate DESC ";
        Query query = entityManager.createQuery(jpql, DashboardBoard.class)
                .setParameter("memberNo", memberNo);

        List<DashboardBoard> entityBoards = query.getResultList();

        List<DashboardBoardDTO> boards = new ArrayList<>();
        int latelyBoardsCount = 4;
        if(entityBoards.size() > latelyBoardsCount){
            for(int i = 0; i < latelyBoardsCount; i++){
                DashboardBoard boardEntity = entityBoards.get(i);
                DashboardBoardDTO board = modelMapper.map(boardEntity, DashboardBoardDTO.class);
                boards.add(board);
            }
        } else if(entityBoards.size() <= latelyBoardsCount){
            boards = entityBoards.stream().map(dashboardBoard ->
                            modelMapper.map(dashboardBoard, DashboardBoardDTO.class))
                    .collect(Collectors.toList());
        }
        return boards;
    }

    /**
     * Gets baskets.
     * author : SeoYoung Kim
     * description :
     *
     * @param memberNo the member no
     * @return the baskets
     */
    /* 수강바구니 조회용 */
    private List<DashboardBasketDTO> getBaskets(int memberNo) {

        String jpql = "SELECT basket " +
                "FROM dashboardBasket as basket " +
                "WHERE basket.member.memberNo = :memberNo ";
        Query query = entityManager.createQuery(jpql, DashboardBasket.class)
                .setParameter("memberNo", memberNo);
        List<DashboardBasket> entityBaskets = query.getResultList();
        List<DashboardBasketDTO> baskets = new ArrayList<>();
        if(entityBaskets.size() > 4){
            for(int i = 0; i < 4; i++){
                DashboardBasket basketEntity = entityBaskets.get(i);
                DashboardBasketDTO basket = modelMapper.map(basketEntity, DashboardBasketDTO.class);
                baskets.add(basket);
            }
        } else if(entityBaskets.size() <= 4){
            baskets = entityBaskets.stream().map(dashboardBasket ->
                            modelMapper.map(dashboardBasket, DashboardBasketDTO.class))
                    .collect(Collectors.toList());
        }
        return baskets;
    }

    /**
     * Gets notices.
     * author : SeoYoung Kim
     * description :
     *
     * @param memberNo the member no
     * @return the notices
     */
    /* 알림 조회용 */
    private List<DashboardNoticeDTO> getNotices(int memberNo) {

        String jpql = "SELECT notice " +
                "FROM dashboardNotice as notice " +
                "WHERE notice.memberNo = :memberNo " +
                "ORDER BY notice.noticedTime DESC";
        Query query = entityManager.createQuery(jpql, DashboardNotice.class)
                .setParameter("memberNo", memberNo);
        List<DashboardNotice> entityNotices = query.getResultList();
        List<DashboardNoticeDTO> notices = new ArrayList<>();
        if(entityNotices.size() > 4){
            for(int i = 0; i < 4; i++){
                DashboardNotice noticeEntity = entityNotices.get(i);
                DashboardNoticeDTO notice = modelMapper.map(noticeEntity, DashboardNoticeDTO.class);
                notices.add(notice);
            }
        } else if(entityNotices.size() <= 4){
            notices = entityNotices.stream().map(dashboardNotice ->
                            modelMapper.map(dashboardNotice, DashboardNoticeDTO.class))
                    .collect(Collectors.toList());
        }

        return notices;
    }

    /**
     * Gets watch list.
     * author : SeoYoung Kim
     * description :
     *
     * @param memberNo the member no
     * @return the watch list
     */
    /* 최근 시청강의 조회용 */
    private List<DashboardLectureWatchDTO> getWatchList(int memberNo) {

        String jpql = "SELECT watch " +
                "FROM dashboardLectureWatch as watch " +
                "WHERE watch.watchedDate in (SELECT Max(w.watchedDate) FROM dashboardLectureWatch as w " +
                "WHERE w.memberNo = :memberNo " +
                "GROUP BY w.lecture)";

        Query query = entityManager.createQuery(jpql, DashboardLectureWatch.class)
                .setParameter("memberNo", memberNo);
        List<DashboardLectureWatch> watchEntityList = query.getResultList();
        List<DashboardLectureWatchDTO> watchList = watchEntityList.stream().map(dashboardLectureWatch ->
                        modelMapper.map(dashboardLectureWatch, DashboardLectureWatchDTO.class))
                .collect(Collectors.toList());

        return watchList;
    }

    /**
     * Gets lectures.
     * author : SeoYoung Kim
     * description :
     *
     * @param memberNo the member no
     * @return the lectures
     */
    /* 시청 완료강의 조회용 */
    private List<DashboardLectureDTO> getcompletedLectures(int memberNo) {

        /* 회원별 수강하고있는 강의항목 조회 */
        String jpql = "SELECT memberlecture FROM Mypage_MemberLecture memberlecture " +
                "WHERE memberlecture.member.memberNo = :memberNo ";
        Query query = entityManager.createQuery(jpql, MyPageMemberLecture.class)
                .setParameter("memberNo", memberNo);

        List<MyPageMemberLecture> memberLectureEntities = query.getResultList();

        System.out.println("memberLectureEntityList = " + memberLectureEntities);
        /* 회원별 수강하고 있는 수강번호 리스트에 담음*/
        List<Integer> memberLectureNoList = new ArrayList<>();
        for(int i = 0; i < memberLectureEntities.size(); i++){

            int memberLectureNo = memberLectureEntities.get(i).getMemberLectureNo();
            memberLectureNoList.add(memberLectureNo);
        }

        /* 수강번호로 누적시간 테이블에서 회원이 듣고있는 강의의 모든 클래스 상태 읽어옴 */
        String jpql2 = "SELECT complete FROM Dashboard_Completedlecture  as complete " +
                "WHERE complete.memberLecture.memberLectureNo = :memberLectureNo ";

        /* 회원별 수강번호에 따른 클래스리스트가 담겨있는 리스트*/
        List<List> completedLectureCategoryEntities = new ArrayList<>();
        for(int i = 0; i < memberLectureNoList.size(); i++){
            int memberLectureNo = memberLectureNoList.get(i);
            List<DashboardCompletedLecture> completedLectureEntityList =
                    entityManager.createQuery(jpql2, DashboardCompletedLecture.class)
                            .setParameter("memberLectureNo", memberLectureNo)
                            .getResultList();
            completedLectureCategoryEntities.add(completedLectureEntityList);
        }

        /* 한 멤버가 듣고있는 강의의 수만큼 반복문 */
        List<DashboardLectureDTO> completedLectures = new ArrayList<>();
        for(int i = 0; i <completedLectureCategoryEntities.size(); i++){
            /* 한 강의의 클래스 리스트*/
            List<DashboardCompletedLecture> completedLectureEntities = completedLectureCategoryEntities.get(i);
            /* 한 강의당 총 클래스 수 */
            int totalClassCountOfLecture = completedLectureEntities.size();

            /* 한 강의당 완료된 클래스 개수(completedStatus : Y의 총 개수) */
            int CompletedClassCount = 0;
            for(int j = 0; j < completedLectureEntities.size(); j++){
                if(completedLectureEntities.get(j).getWatchedStatus().equals("Y ")){
                    CompletedClassCount += 1;
                }
                /* 클래스의 개수와 완료된 클래스의 개수가 동일할 시 시청 완료, 리스트에 넣어서 view로 return */
                if(totalClassCountOfLecture == CompletedClassCount){
                    DashboardLecture lectureInfoEntity = lectureRepository.findById(completedLectureEntities
                                    .get(j)
                                    .getMemberLecture()
                                    .getLecture()
                                    .getLectureNo())
                            .get();
                    DashboardLectureDTO lectureInfo =
                            modelMapper.map(lectureInfoEntity, DashboardLectureDTO.class);
                    completedLectures.add(lectureInfo);
                }
            }

        }
        return completedLectures;
    }

    /**
     * methodName : findTutorDashboard
     * author : SeoYoung Kim
     * description :
     *
     * @param memberNo
     * @return mypage tutor dto
     */
    @Override
    public MypageTutorDTO findTutorDashboard(int memberNo) {

        MypageTutorDTO dashboard = new MypageTutorDTO();

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
            boardList = entityBoardList.stream()
                    .map(dashboardBoard ->
                            modelMapper.map(dashboardBoard, DashboardBoardDTO.class))
                    .collect(Collectors.toList());
        }

        /* 진행중인 강의 조회용 */
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

        dashboard.setMember(member);
        dashboard.setBoardList(boardList);
        dashboard.setLectureList(lectureList);
        dashboard.setNoticeList(noticeList);

        return dashboard;
    }



}
