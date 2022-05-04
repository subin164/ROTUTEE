package com.greedy.rotutee.point.service;

import com.greedy.rotutee.point.dto.CouponDTO;
import com.greedy.rotutee.point.dto.MemberDTO;
import com.greedy.rotutee.point.dto.PointHistoryDTO;
import com.greedy.rotutee.point.dto.PointProductDTO;
import com.greedy.rotutee.point.entity.*;
import com.greedy.rotutee.point.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : com.greedy.rotutee.point.service
 * fileName : PointService
 * author : 7sang
 * date : 2022-05-02
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-02 7sang 최초 생성
 */

@Service
public class PointService {

    private final PointAcquisitionPlaceRepository pointAcquisitionPlaceRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final PointHistoryRepositoryQuery pointHistoryRepositoryQuery;
    private final PointProductRepository pointProductRepository;
    private final MemberCouponBoxRepository memberCouponBoxRepository;
    private final CouponRepository couponRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PointService(PointAcquisitionPlaceRepository pointAcquisitionPlaceRepository, PointHistoryRepository pointHistoryRepository, PointHistoryRepositoryQuery pointHistoryRepositoryQuery, PointProductRepository pointProductRepository, MemberCouponBoxRepository memberCouponBoxRepository, CouponRepository couponRepository, ModelMapper modelMapper, MemberRepository memberRepository) {
        this.pointAcquisitionPlaceRepository = pointAcquisitionPlaceRepository;
        this.pointHistoryRepository = pointHistoryRepository;
        this.pointHistoryRepositoryQuery = pointHistoryRepositoryQuery;
        this.pointProductRepository = pointProductRepository;
        this.memberCouponBoxRepository = memberCouponBoxRepository;
        this.couponRepository = couponRepository;
        this.modelMapper = modelMapper;
        this.memberRepository = memberRepository;

    }

    public int findMemberPoint(int memberNo) {

        return pointHistoryRepositoryQuery.findMemberPoint(entityManager, memberNo);
    }

    public MemberDTO findMember(int memberNo) {

        return modelMapper.map(memberRepository.findById(memberNo).get(), MemberDTO.class);
    }

    @Transactional
    public void playRoulette(int memberNo, int randPoint) {

        Member member = memberRepository.findById(memberNo).get();
        PointHistory pointHistory = new PointHistory();
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        pointHistory.setChangePoint(randPoint);
        pointHistory.setChangeDate(date);
        pointHistory.setDivision("획득");
        pointHistory.setMember(member);
        pointHistory.setPointAcquisitionPlace(pointAcquisitionPlaceRepository.findById(4).get());
        pointHistory.setFinalPoint(findMemberPoint(memberNo) + randPoint);

        pointHistoryRepository.save(pointHistory);

        member.setRouletteChance(member.getRouletteChance() - 1);
    }

    public Page<PointHistoryDTO> findUsePointHistory(int memberNo, Pageable pageable) {

        String division = "사용";
        Member member = memberRepository.findById(memberNo).get();

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("historyNo").descending());

        return pointHistoryRepository.findByMemberAndDivision(member, division, pageable).map(pointHistory -> modelMapper.map(pointHistory, PointHistoryDTO.class));
    }

    public Page<PointHistoryDTO> findAchievePointHistory(int memberNo, Pageable pageable) {

        String division = "획득";
        Member member = memberRepository.findById(memberNo).get();

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("historyNo").descending());

        return pointHistoryRepository.findByMemberAndDivision(member, division, pageable).map(pointHistory -> modelMapper.map(pointHistory, PointHistoryDTO.class));
    }

    public List<PointProductDTO> findAllPointProductList() {

        List<PointProduct> productList = pointProductRepository.findByProductStatus("Y ");

        return productList.stream().map(pointProduct -> modelMapper.map(pointProduct, PointProductDTO.class)).collect(Collectors.toList());
    }

    /* 포인트 상품 교환 메서드 */
    @Transactional
    public void productExchange(int memberNo, int productNo) {

        PointProduct pointProduct = pointProductRepository.findById(productNo).get();
        pointProduct.setRemainingNumber(pointProduct.getRemainingNumber() - 1);

        Member member = memberRepository.findById(memberNo).get();
        Coupon coupon = pointProduct.getCoupon();

        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        Date expirationDate = new Date(miliseconds + (long)( ( 1000 * 60 * 60 * 24 ) * coupon.getExpirationDate() ));

        MemberCouponBox memberCouponBox = new MemberCouponBox();
        memberCouponBox.setMember(member);
        memberCouponBox.setCoupon(coupon);
        memberCouponBox.setReceivingDate(date);
        memberCouponBox.setExpirationDate(expirationDate);
        memberCouponBox.setStatus("미사용");
        memberCouponBoxRepository.save(memberCouponBox);

        PointHistory pointHistory = new PointHistory();
        pointHistory.setChangePoint(pointProduct.getProductPrice());
        pointHistory.setChangeDate(date);
        pointHistory.setDivision("사용");
        pointHistory.setMember(member);
        pointHistory.setPointProduct(pointProduct);
        pointHistory.setFinalPoint(findMemberPoint(memberNo) - pointProduct.getProductPrice());

        pointHistoryRepository.save(pointHistory);
    }

    public List<CouponDTO> findAllCouponList() {

        List<Coupon> couponList = couponRepository.findAll();

        return couponList.stream().map(coupon -> modelMapper.map(coupon, CouponDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public void registPointProduct(PointProductDTO pointProduct, int couponNo) {

        PointProduct newPointProduct = modelMapper.map(pointProduct, PointProduct.class);
        newPointProduct.setCoupon(couponRepository.findById(couponNo).get());

        pointProductRepository.save(newPointProduct);
    }

    @Transactional
    public void removePointProduct(int productNo) {

        PointProduct pointProduct = pointProductRepository.findById(productNo).get();
        pointProduct.setProductStatus("N");
    }

    @Transactional
    public void modifyPointProduct(PointProductDTO pointProduct) {

        PointProduct foundPointProduct = pointProductRepository.findById(pointProduct.getProductNo()).get();

        foundPointProduct.setProductPrice(pointProduct.getProductPrice());
        foundPointProduct.setProductName(pointProduct.getProductName());
        foundPointProduct.setRemainingNumber(pointProduct.getRemainingNumber());
        foundPointProduct.setTotalSalesCount(pointProduct.getTotalSalesCount());
    }
}
