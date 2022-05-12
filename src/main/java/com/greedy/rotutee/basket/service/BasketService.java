package com.greedy.rotutee.basket.service;

import com.greedy.rotutee.basket.dto.BasketMemberCouponBoxDTO;
import com.greedy.rotutee.basket.dto.ClassBasketDTO;
import com.greedy.rotutee.basket.dto.MemberLectureDTO;

import java.util.List;

public interface BasketService {
    void registLectureToCart(int lectureNo, int memberNo);

    List<ClassBasketDTO> findByMemberNo(int memberNo);

    void removeOneBasket(int lectureNo, int memberNo);

    ClassBasketDTO findByLectureNoAndMemberNo(int lectureNo, int memberNo);

    List<BasketMemberCouponBoxDTO> selectCouponList(int memberNo);

/*
    ClassBasketDTO modifyCouponList(int lecture, int couponNo);
*/

    void removeOneCoupon(int lecture, int couponNo);

    MemberLectureDTO findByLectureNoAndMemberNoInMemberLecture(int no, int lectureNo);
}
