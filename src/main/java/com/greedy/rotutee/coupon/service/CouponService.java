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
import java.util.stream.Collectors;

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


}
