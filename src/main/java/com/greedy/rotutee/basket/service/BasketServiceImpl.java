package com.greedy.rotutee.basket.service;

import com.greedy.rotutee.basket.dto.ClassBasketDTO;
import com.greedy.rotutee.basket.dto.LectureDTO;
import com.greedy.rotutee.basket.dto.MemberDTO;
import com.greedy.rotutee.basket.entity.ClassBasket;
import com.greedy.rotutee.basket.entity.Lecture;
import com.greedy.rotutee.basket.entity.Member;
import com.greedy.rotutee.basket.repository.ClassBasketLectureRepository;
import com.greedy.rotutee.basket.repository.ClassBasketMemberRepository;
import com.greedy.rotutee.basket.repository.ClassBasketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Autowired
    public BasketServiceImpl(ModelMapper modelMapper, ClassBasketRepository classBasketRepository, ClassBasketLectureRepository classBasketLectureRepository, ClassBasketMemberRepository classBasketMemberRepository) {
        this.modelMapper = modelMapper;
        this.classBasketRepository = classBasketRepository;
        this.classBasketLectureRepository = classBasketLectureRepository;
        this.classBasketMemberRepository = classBasketMemberRepository;
    }

    @Override
    public ClassBasketDTO findByLectureNoAndMemberNo(int lectureNo, int memberNo) {

        Lecture lectureEntity = classBasketLectureRepository.findByLectureNo(lectureNo);
        Member memberEntity = classBasketMemberRepository.findByNo(memberNo);

        ClassBasket basket = classBasketRepository.findByLectureAndMember(lectureEntity, memberEntity);

        if(basket != null) {
            return modelMapper.map(basket, ClassBasketDTO.class);
        }

        return null;
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
    }

    @Override
    public List<ClassBasketDTO> findByMemberNo(int memberNo) {

        List<ClassBasket> cartList = classBasketRepository.findByMemberNo(memberNo);

        return cartList.stream().map(cart -> modelMapper.map(cart, ClassBasketDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeOneBasket(int lectureNo, int memberNo) {

        Lecture lectureEntity = classBasketLectureRepository.findByLectureNo(lectureNo);
        Member memberEntity = classBasketMemberRepository.findByNo(memberNo);

        classBasketRepository.deleteByLectureAndMember(lectureEntity, memberEntity);

    }

}
