package com.greedy.rotutee.basket.service;

import com.greedy.rotutee.basket.dto.ClassBasketDTO;
import com.greedy.rotutee.basket.dto.LectureDTO;
import com.greedy.rotutee.basket.dto.MemberDTO;
import com.greedy.rotutee.basket.dto.MemberInterestDTO;
import com.greedy.rotutee.basket.entity.ClassBasket;
import com.greedy.rotutee.basket.entity.Lecture;
import com.greedy.rotutee.basket.entity.Member;
import com.greedy.rotutee.basket.repository.ClassBasketLectureRepository;
import com.greedy.rotutee.basket.repository.ClassBasketMemberInterestRepository;
import com.greedy.rotutee.basket.repository.ClassBasketMemberRepository;
import com.greedy.rotutee.basket.repository.ClassBasketRepository;
import com.greedy.rotutee.basket.entity.LectureCategory;
import com.greedy.rotutee.basket.entity.MemberInterest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    public BasketServiceImpl(ModelMapper modelMapper, ClassBasketRepository classBasketRepository, ClassBasketLectureRepository classBasketLectureRepository, ClassBasketMemberRepository classBasketMemberRepository, ClassBasketMemberInterestRepository classBasketMemberInterestRepository) {
        this.modelMapper = modelMapper;
        this.classBasketRepository = classBasketRepository;
        this.classBasketLectureRepository = classBasketLectureRepository;
        this.classBasketMemberRepository = classBasketMemberRepository;
        this.classBasketMemberInterestRepository = classBasketMemberInterestRepository;
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
        System.out.println("member = " + member);

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
