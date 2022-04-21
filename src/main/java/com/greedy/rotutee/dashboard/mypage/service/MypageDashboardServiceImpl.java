package com.greedy.rotutee.dashboard.mypage.service;

import com.greedy.rotutee.dashboard.mypage.dto.*;
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

        /* 프로필 조회용 */
        DashboardMember memberEntity = memberRepository.findById(memberNo).get();
        DashboardMemberDTO member = modelMapper.map(memberEntity, DashboardMemberDTO.class);

        /* 작성게시물 조회용 */
        List<DashboardBoard> entityBoardList = boardRepository.findBymemberNo(memberNo);
        List<DashboardBoardDTO> boardList = entityBoardList.stream().map(dashboardBoard -> modelMapper.map(dashboardBoard, DashboardBoardDTO.class)).collect(Collectors.toList());


        /* 수강바구니 조회용 */
        DashboardBasket basket = new DashboardBasket();
        basket.setMember(memberEntity);
        List<DashboardBasket> entityBasketList = basketRepository.findBymember(memberEntity);
        List<DashboardBasketDTO> basketList = entityBasketList.stream().map(dashboardBasket -> modelMapper.map(dashboardBasket, DashboardBasketDTO.class)).collect(Collectors.toList());

        /* 알림 조회용 */
        List<DashboardNotice> noticeEntityList = noticeRepository.findBymemberNo(memberNo);
        List<DashboardNoticeDTO> noticeList = noticeEntityList.stream().map(dashboardNotice -> modelMapper.map(dashboardNotice, DashboardNoticeDTO.class)).collect(Collectors.toList());

        /* 최근 시청강의 조회용 */
        String jpql = "SELECT watch " +
                "FROM dashboardLectureWatch as watch " +
                "WHERE watch.watchedDate in (SELECT Max(w.watchedDate) FROM dashboardLectureWatch as w " +
                "WHERE w.memberNo = :memberNo " +
                "GROUP BY w.lecture)";

        Query query = entityManager.createQuery(jpql, DashboardLectureWatch.class)
                .setParameter("memberNo", memberNo);
        List<DashboardLectureWatch> watchEntityList = query.getResultList();
        List<DashboardLectureWatchDTO> watchList = watchEntityList.stream().map(dashboardLectureWatch -> modelMapper.map(dashboardLectureWatch, DashboardLectureWatchDTO.class)).collect(Collectors.toList());

        /* 시청 완료강의 조회용 */
        /* 회원별 수강하고있는 강의항목 조회 */
        String jpql1 = "SELECT memberlecture FROM Dashboard_MemberLecture memberlecture " +
                "WHERE memberlecture.member.memberNo = :memberNo ";
        Query query1 = entityManager.createQuery(jpql1, DashboardMemberLecture.class)
                .setParameter("memberNo", memberNo);

        List<DashboardMemberLecture> memberLectureEntityList = query1.getResultList();

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
                    DashboardLecture lectureInfoEntity = lectureRepository.findById(completedLectureList.get(j).getMemberLecture().getLectureNo()).get();
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
        return dashboard;
    }
}
