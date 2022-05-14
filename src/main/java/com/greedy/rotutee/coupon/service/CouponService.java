package com.greedy.rotutee.coupon.service;


import com.greedy.rotutee.coupon.dto.CouponDTO;
import com.greedy.rotutee.coupon.dto.MemberCouponBoxDTO;
import com.greedy.rotutee.coupon.entity.Coupon;
import com.greedy.rotutee.coupon.entity.MemberCouponBox;
import com.greedy.rotutee.coupon.repository.CouponRepository;
import com.greedy.rotutee.coupon.repository.MemberCouponBoxRepository;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.member.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * The type CouponService.
 */
@Service
public class CouponService {

    /**
     * The Coupon repository.
     */
    private final CouponRepository couponRepository;
    /**
     * The Member repository.
     */
    private final MemberRepository memberRepository;
    /**
     * The Member coupon box repository.
     */
    private final MemberCouponBoxRepository memberCouponBoxRepository;
    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Instantiates a new Coupon service.
     *
     * @param couponRepository          the coupon repository
     * @param memberRepository          the member repository
     * @param memberCouponBoxRepository the member coupon box repository
     * @param modelMapper               the model mapper
     */
    @Autowired
    public CouponService(CouponRepository couponRepository, MemberRepository memberRepository, MemberCouponBoxRepository memberCouponBoxRepository, ModelMapper modelMapper) {
        this.couponRepository = couponRepository;
        this.memberRepository = memberRepository;
        this.memberCouponBoxRepository = memberCouponBoxRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * methodName : couponRegist
     * author : SeoYoung Kim
     * description :
     *
     * @param coupon dto
     */
    @Transactional
    public void couponRegist(CouponDTO couponDTO) {

        couponRepository.save(modelMapper.map(couponDTO, Coupon.class));
    }


    /**
     * methodName : findCouponList
     * author : SeoYoung Kim
     * description :
     *
     * @param coupon   dto
     * @param pageable
     * @return page
     */
    public Page findCouponList(CouponDTO couponDTO, Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize());

        return couponRepository.findByPublishCouponStatus("N ", pageable).map(coupon -> modelMapper.map(coupon, CouponDTO.class));
    }

    /**
     * methodName : findRemoveList
     * author : SeoYoung Kim
     * description :
     *
     * @param coupon array
     */
    @Transactional
    public void findRemoveList(List<String> couponArray) {

        for (int i = 0; i < couponArray.size(); i++) {

            System.out.println(i + "번째 : " + couponArray.get(i));
            ;
            Coupon coupon = couponRepository.getById(Integer.valueOf(couponArray.get(i)));

            coupon.setPublishCouponStatus("Y");

            couponRepository.save(coupon);
        }
    }

    /**
     * methodName : couponModify
     * author : SeoYoung Kim
     * description :
     *
     * @param coupon dto
     */
    @Transactional
    public void couponModify(CouponDTO couponDTO) {
        Coupon modifyCoupon = couponRepository.getById(couponDTO.getPublishCouponNo());

        modelMapper.map(modifyCoupon, CouponDTO.class);

        modifyCoupon.setPublishCouponName(couponDTO.getPublishCouponName());
        modifyCoupon.setPublishCouponContent(couponDTO.getPublishCouponContent());
        modifyCoupon.setDiscountRate(couponDTO.getDiscountRate());
        modifyCoupon.setExpirationDate(couponDTO.getExpirationDate());

        couponRepository.save(modelMapper.map(modifyCoupon, Coupon.class));
    }

    /**
     * methodName : couponPublish
     * author : SeoYoung Kim
     * description :
     *
     * @param coupon no list
     */
    @Transactional
    public void couponPublishAll(List<String> couponNoList) {

        List<Member> memberList = memberRepository.findAll();

        for (int i = 0; i < couponNoList.size(); i++) {
            Coupon coupon = couponRepository.getById(Integer.valueOf(couponNoList.get(i)));

            MemberCouponBoxDTO memberCouponBoxDTO = new MemberCouponBoxDTO();

            System.out.println("coupon : " + coupon);

            for(int j = 0; j<memberList.size(); j++){

                memberCouponBoxDTO.setExpirationDate(new Date(System.currentTimeMillis()));
                memberCouponBoxDTO.setReceivingDate(new Date(System.currentTimeMillis()));
                memberCouponBoxDTO.setCoupon(modelMapper.map(coupon, CouponDTO.class));
                memberCouponBoxDTO.setMemberNo(modelMapper.map(memberList.get(i), MemberDTO.class));
                memberCouponBoxDTO.setCouponStatus("미사용");

                memberCouponBoxRepository.save(modelMapper.map(memberCouponBoxDTO, MemberCouponBox.class));
            }
        }

    }

    public void couponSelectPersonal(List<String> couponNoList, String publishToPersonalMember) {

        List<Member> memberList = memberRepository.getByNickname(publishToPersonalMember);

        for (int i = 0; i < couponNoList.size(); i++) {
            Coupon coupon = couponRepository.getById(Integer.valueOf(couponNoList.get(i)));

            MemberCouponBoxDTO memberCouponBoxDTO = new MemberCouponBoxDTO();

            System.out.println("coupon : " + coupon);

            for(int j = 0; j<memberList.size(); j++){

                memberCouponBoxDTO.setExpirationDate(new Date(System.currentTimeMillis()));
                memberCouponBoxDTO.setReceivingDate(new Date(System.currentTimeMillis()));
                memberCouponBoxDTO.setCoupon(modelMapper.map(coupon, CouponDTO.class));
                memberCouponBoxDTO.setMemberNo(modelMapper.map(memberList.get(i), MemberDTO.class));
                memberCouponBoxDTO.setCouponStatus("미사용");

                memberCouponBoxRepository.save(modelMapper.map(memberCouponBoxDTO, MemberCouponBox.class));
            }
        }
    }
}
