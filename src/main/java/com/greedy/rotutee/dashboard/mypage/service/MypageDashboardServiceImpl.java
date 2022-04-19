package com.greedy.rotutee.dashboard.mypage.service;

import com.greedy.rotutee.dashboard.mypage.dto.DashboardBasketDTO;
import com.greedy.rotutee.dashboard.mypage.dto.DashboardBoardDTO;
import com.greedy.rotutee.dashboard.mypage.dto.DashboardMemberDTO;
import com.greedy.rotutee.dashboard.mypage.dto.MypageDashboardDTO;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardBasket;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardBoard;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardMember;
import com.greedy.rotutee.dashboard.mypage.repository.*;
import com.greedy.rotutee.member.dto.MemberDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ModelMapper modelMapper;

    @Autowired
    public MypageDashboardServiceImpl(DashboardMemberRepository memberRepository, DashboardBasketRepository basketRepository, DashboardBoardRepository boardRepository, DashboardLectureRepository lectureRepository, DashboardNoticeRepository noticeRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.basketRepository = basketRepository;
        this.boardRepository = boardRepository;
        this.lectureRepository = lectureRepository;
        this.noticeRepository = noticeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MypageDashboardDTO findTuteeDashboard(int memberNo) {

        MypageDashboardDTO dashboard = new MypageDashboardDTO();

        DashboardMember memberEntity = memberRepository.findById(memberNo).get();
        DashboardMemberDTO member = modelMapper.map(memberEntity, DashboardMemberDTO.class);

        List<DashboardBoard> entityBoardList = boardRepository.findBymemberNo(memberNo);
        List<DashboardBoardDTO> boardList = entityBoardList.stream().map(dashboardBoard -> modelMapper.map(dashboardBoard, DashboardBoardDTO.class)).collect(Collectors.toList());

        List<DashboardBasket> entityBasketList = basketRepository.findBymemberNo(memberNo);
        List<DashboardBasketDTO> basketList = entityBasketList.stream().map(dashboardBasket -> modelMapper.map(dashboardBasket, DashboardBasketDTO.class)).collect(Collectors.toList());


        return dashboard;

    }
}
