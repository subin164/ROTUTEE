package com.greedy.rotutee.basket.service;

import com.greedy.rotutee.basket.dto.*;
import com.greedy.rotutee.basket.entity.*;
import com.greedy.rotutee.basket.repository.*;
import com.greedy.rotutee.dashboard.lms.dto.LMSSubmissionQuizDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * packageName      : com.greedy.rotutee.basket.service
 * fileName         : BasketServiceImpl
 * author           : SEOK
 * date             : 2022-05-03
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-03      SEOK         최초 생성
 */
@Service
public class BasketServiceImpl implements BasketService{

    private final ModelMapper modelMapper;
    private final ClassBasketRepository classBasketRepository;
    private final ClassBasketLectureRepository classBasketLectureRepository;
    private final ClassBasketMemberRepository classBasketMemberRepository;
    private final ClassBasketMemberInterestRepository classBasketMemberInterestRepository;
    private final ClassBasketMemberCouponBoxRepository classBasketMemberCouponBoxRepository;

    @Autowired
    public BasketServiceImpl(ModelMapper modelMapper, ClassBasketRepository classBasketRepository, ClassBasketLectureRepository classBasketLectureRepository, ClassBasketMemberRepository classBasketMemberRepository, ClassBasketMemberInterestRepository classBasketMemberInterestRepository, ClassBasketMemberCouponBoxRepository classBasketMemberCouponBoxRepository) {
        this.modelMapper = modelMapper;
        this.classBasketRepository = classBasketRepository;
        this.classBasketLectureRepository = classBasketLectureRepository;
        this.classBasketMemberRepository = classBasketMemberRepository;
        this.classBasketMemberInterestRepository = classBasketMemberInterestRepository;
        this.classBasketMemberCouponBoxRepository = classBasketMemberCouponBoxRepository;
    }

    @Override
    public ClassBasketDTO findByLectureNoAndMemberNo(int lectureNo, int memberNo) {

        Lecture lectureEntity = classBasketLectureRepository.findByLectureNo(lectureNo);
        Member memberEntity = classBasketMemberRepository.findByNo(memberNo);

        ClassBasket basket = classBasketRepository.findByLectureAndMember(lectureEntity, memberEntity);

        if(basket != null) {

            ClassBasketDTO basketDTO = new ClassBasketDTO();
            basketDTO.setClassBasketNo(basket.getClassBasketNo());
            basketDTO.setMember(modelMapper.map(basket.getMember(), MemberDTO.class));
            basketDTO.setLecture(modelMapper.map(basketDTO.getLecture(), LectureDTO.class));

            return basketDTO;
        }

        return null;
    }

    @Override
    public List<BasketMemberCouponBoxDTO> selectCouponList(int memberNo) {

        List<BasketMemberCouponBox> basketMemberCouponBox = classBasketMemberCouponBoxRepository.findByMemberNoAndCouponStatus(memberNo, "미사용");

        List<BasketMemberCouponBoxDTO> basketMemberCouponBoxDTO = new ArrayList<>();
        for(BasketMemberCouponBox basketMemberCouponBox1 :basketMemberCouponBox) {
            MemberDTO member = modelMapper.map(basketMemberCouponBox1.getMember(), MemberDTO.class);
            BasketCouponDTO basketCouponDTO = modelMapper.map(basketMemberCouponBox1.getBasketCoupon(), BasketCouponDTO.class);

            BasketMemberCouponBoxDTO basketMemberCouponBoxDTO1 = new BasketMemberCouponBoxDTO();
            basketMemberCouponBoxDTO1.setCouponBoxNo(basketMemberCouponBox1.getCouponBoxNo());
            basketMemberCouponBoxDTO1.setCouponExpirationDate(basketMemberCouponBox1.getCouponExpirationDate());
            basketMemberCouponBoxDTO1.setCouponRecevingDate(basketMemberCouponBox1.getCouponRecevingDate());
            basketMemberCouponBoxDTO1.setCoupon(basketCouponDTO);
            basketMemberCouponBoxDTO1.setMember(member);
            basketMemberCouponBoxDTO1.setCouponStatus(basketMemberCouponBox1.getCouponStatus());

            basketMemberCouponBoxDTO.add(basketMemberCouponBoxDTO1);
        }

        return basketMemberCouponBoxDTO;
    }

 /*   @Override
    @Transactional
    public ClassBasketDTO modifyCouponList(int lectureNo, int couponNo) {

        ClassBasket classBasket = ClassBasketRepository.
        Member memberEntity = classBasketMemberRepository.findByNo(memberNo);

        ClassBasket basket = classBasketRepository.findByLectureAndMember(lectureEntity, memberEntity);

        if(basket != null) {

            ClassBasketDTO basketDTO = new ClassBasketDTO();
            basketDTO.setClassBasketNo(basket.getClassBasketNo());
            basketDTO.setMember(modelMapper.map(basket.getMember(), MemberDTO.class));
            basketDTO.setLecture(modelMapper.map(basketDTO.getLecture(), LectureDTO.class));

            return basketDTO;
        }

        return null;
    }*/

    @Override
    public void removeOneCoupon(int basketNo, int couponNo) {

    }

    @Override
    @Transactional
    public void registLectureToCart(int lectureNo, int memberNo) {

        Lecture lectureEntity = classBasketLectureRepository.findByLectureNo(lectureNo);
        Member memberEntity = classBasketMemberRepository.findByNo(memberNo);

        LectureDTO lecture = modelMapper.map(lectureEntity, LectureDTO.class);
        MemberDTO member = modelMapper.map(memberEntity, MemberDTO.class);

        ClassBasketDTO cart = new ClassBasketDTO();
        cart.setLecture(lecture);
        cart.setMember(member);

        classBasketRepository.save(modelMapper.map(cart, ClassBasket.class));

        LectureCategory categoryEntity = lectureEntity.getLectureCategory();

        MemberInterest memberInterest = classBasketMemberInterestRepository.findByMemberAndCategory(memberEntity, categoryEntity);
        if(memberInterest == null) {

            int degree = 1;

            MemberInterestDTO interest = new MemberInterestDTO();
            interest.setMember(member);
            interest.setCategory(lecture.getCategory());
            interest.setInterestDegree(degree);

            classBasketMemberInterestRepository.save(modelMapper.map(interest, MemberInterest.class));
        } else {

            int increasedDegree = memberInterest.getInterestDegree() + 2;
            memberInterest.setInterestDegree(increasedDegree);
        }
    }

    @Override
    public List<ClassBasketDTO> findByMemberNo(int memberNo) {

        Member member = classBasketMemberRepository.findByNo(memberNo);

        List<ClassBasket> basketList = classBasketRepository.findByMember(member);
        List<ClassBasketDTO> basketDTOList = new ArrayList<>();

        for(ClassBasket basket : basketList) {

            MemberDTO memberDTO = modelMapper.map(basket.getMember(), MemberDTO.class);
            LectureDTO lectureDTO = modelMapper.map(basket.getLecture(), LectureDTO.class);

            ClassBasketDTO basketDTO = new ClassBasketDTO();
            basketDTO.setClassBasketNo(basket.getClassBasketNo());
            basketDTO.setMember(memberDTO);
            basketDTO.setLecture(lectureDTO);

            basketDTOList.add(basketDTO);
        }

        return basketDTOList;
    }

    @Override
    @Transactional
    public void removeOneBasket(int lectureNo, int memberNo) {

        Lecture lectureEntity = classBasketLectureRepository.findByLectureNo(lectureNo);
        Member memberEntity = classBasketMemberRepository.findByNo(memberNo);

        classBasketRepository.deleteByLectureAndMember(lectureEntity, memberEntity);

    }

}
