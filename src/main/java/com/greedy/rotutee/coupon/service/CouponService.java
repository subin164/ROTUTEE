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

@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;
    private final MemberCouponBoxRepository memberCouponBoxRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CouponService(CouponRepository couponRepository, MemberRepository memberRepository, MemberCouponBoxRepository memberCouponBoxRepository, ModelMapper modelMapper) {
        this.couponRepository = couponRepository;
        this.memberRepository = memberRepository;
        this.memberCouponBoxRepository = memberCouponBoxRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void couponRegist(CouponDTO couponDTO) {

        couponRepository.save(modelMapper.map(couponDTO, Coupon.class));
    }


    public Page findCouponList(CouponDTO couponDTO, Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize());

        return couponRepository.findByCouponStatus("N ", pageable).map(coupon -> modelMapper.map(coupon, CouponDTO.class));
    }

    @Transactional
    public void findRemoveList(List<String> couponArray) {

        for (int i = 0; i < couponArray.size(); i++) {

            System.out.println(i + "번째 : " + couponArray.get(i));
            ;
            Coupon coupon = couponRepository.getById(Integer.valueOf(couponArray.get(i)));

            coupon.setCouponStatus("Y");

            couponRepository.save(coupon);
        }
    }

    @Transactional
    public void couponModify(CouponDTO couponDTO) {
        Coupon modifyCoupon = couponRepository.getById(couponDTO.getCouponNo());

        modelMapper.map(modifyCoupon, CouponDTO.class);

        modifyCoupon.setCouponName(couponDTO.getCouponName());
        modifyCoupon.setCouponContent(couponDTO.getCouponContent());
        modifyCoupon.setDiscountRate(couponDTO.getDiscountRate());
        modifyCoupon.setExpirationDate(couponDTO.getExpirationDate());

        couponRepository.save(modelMapper.map(modifyCoupon, Coupon.class));
    }

    @Transactional
    public void couponPublish(List<String> couponNoList) {

        List<Member> memberList = memberRepository.findAll();

        for (int i = 0; i < couponNoList.size(); i++) {
            Coupon coupon = couponRepository.getById(Integer.valueOf(couponNoList.get(i)));

            MemberCouponBoxDTO memberCouponBoxDTO = new MemberCouponBoxDTO();



            System.out.println("coupon : " + coupon);

            for(int j = 0; j<memberList.size(); j++){

                memberCouponBoxDTO.setExpirationDate(new Date(System.currentTimeMillis()));
                memberCouponBoxDTO.setReceivingDate(new Date(System.currentTimeMillis()));
                memberCouponBoxDTO.setCouponNo(modelMapper.map(coupon, CouponDTO.class));
                memberCouponBoxDTO.setMemberNo(modelMapper.map(memberList.get(i), MemberDTO.class));

                memberCouponBoxRepository.save(modelMapper.map(memberCouponBoxDTO, MemberCouponBox.class));
            }
        }

    }
}
