package com.greedy.rotutee.member.service;

import com.greedy.rotutee.member.dto.MemberDTO;
import com.greedy.rotutee.member.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * packageName : com.greedy.rotutee.member.service
 * fileName : ProfileService
 * author : 7sang
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 7sang 최초 생성
 */

@Service
public class ProfileService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProfileService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    public MemberDTO memberProfile(int memberNo) {

        return modelMapper.map(memberRepository.findById(memberNo), MemberDTO.class);
    }




}
