package com.greedy.rotutee.dashboard.mypage.model.repository;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.dashboard.mypage.dto.DashboardBasketDTO;
import com.greedy.rotutee.dashboard.mypage.dto.DashboardBoardDTO;
import com.greedy.rotutee.dashboard.mypage.dto.DashboardMemberDTO;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardBasket;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardBoard;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardMember;
import com.greedy.rotutee.dashboard.mypage.repository.DashboardBasketRepository;
import com.greedy.rotutee.dashboard.mypage.repository.DashboardBoardRepository;
import com.greedy.rotutee.dashboard.mypage.repository.DashboardMemberRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class MyPageDashboardRepositoryTest {

    @Autowired
    private DashboardMemberRepository memberRepository;
    @Autowired
    private DashboardBoardRepository boardRepository;
    @Autowired
    private DashboardBasketRepository basketRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void init() {
        assertNotNull(memberRepository);
        assertNotNull(modelMapper);
        assertNotNull(boardRepository);
        assertNotNull(basketRepository);
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
        List<DashboardBoard> entityBoardList = boardRepository.findBymemberNo(memberNo);
        List<DashboardBoardDTO> boardList = entityBoardList.stream().map(dashboardBoard -> modelMapper.map(dashboardBoard, DashboardBoardDTO.class)).collect(Collectors.toList());

        //then
        assertNotNull(entityBoardList);
        entityBoardList.forEach(System.out::println);
        boardList.forEach(System.out::println);
    }

    @Test
    public void 대시보드_수강바구니_조회_확인() {
        //given
        int memberNo = 27;

        //when
        List<DashboardBasket> entityBasketList = basketRepository.findBymemberNo(memberNo);
        List<DashboardBasketDTO> basketList = entityBasketList.stream().map(dashboardBasket -> modelMapper.map(dashboardBasket, DashboardBasketDTO.class)).collect(Collectors.toList());

        //then
        assertNotNull(entityBasketList);
        entityBasketList.forEach(System.out::println);
        basketList.forEach(System.out::println);
    }
}
