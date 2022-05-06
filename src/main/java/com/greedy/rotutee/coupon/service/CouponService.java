package com.greedy.rotutee.coupon.service;

import com.greedy.rotutee.coupon.dto.CouponDTO;
import com.greedy.rotutee.coupon.entity.Coupon;
import com.greedy.rotutee.coupon.repository.CouponRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CouponService(CouponRepository couponRepository, ModelMapper modelMapper) {
        this.couponRepository = couponRepository;
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

        for(int i = 0; i<couponArray.size(); i++){

            System.out.println(i +"번째 : " + couponArray.get(i) );;
            Coupon coupon = couponRepository.getById(Integer.valueOf(couponArray.get(i)));

            coupon.setCouponStatus("Y");

            couponRepository.save(coupon);
        }
    }

    @Transactional
    public void couponModify(CouponDTO couponDTO) {
        Coupon modifyCoupon  = couponRepository.getById(couponDTO.getCouponNo());

        modelMapper.map(modifyCoupon, CouponDTO.class);

        modifyCoupon.setCouponName(couponDTO.getCouponName());
        modifyCoupon.setCouponContent(couponDTO.getCouponContent());
        modifyCoupon.setDiscountRate(couponDTO.getDiscountRate());
        modifyCoupon.setExpirationDate(couponDTO.getExpirationDate());

        couponRepository.save(modelMapper.map(modifyCoupon, Coupon.class));
    }
}
