package com.greedy.rotutee.member.service;

import com.greedy.rotutee.member.dto.LectureCategoryDTO;
import com.greedy.rotutee.member.dto.MemberDTO;
import com.greedy.rotutee.member.entity.LectureCategory;
import com.greedy.rotutee.member.entity.Member;
import com.greedy.rotutee.member.repository.LectureCategoryRepository;
import com.greedy.rotutee.member.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final LectureCategoryRepository lectureCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberService(LectureCategoryRepository lectureCategoryRepository, PasswordEncoder passwordEncoder, MemberRepository memberRepository, ModelMapper modelMapper) {

        this.lectureCategoryRepository = lectureCategoryRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    public List<LectureCategoryDTO> findLectureCategoryList() {

        List<LectureCategory> lectureCategoryList =  lectureCategoryRepository.findAll();

        for(LectureCategory category : lectureCategoryList) {
            System.out.println("category = " + category);
        }

        return lectureCategoryList.stream().map(lectureCategory ->
                modelMapper.map(lectureCategory, LectureCategoryDTO.class)).collect(Collectors.toList());
    }

    public void registMember(MemberDTO member) {

        String encodePwd = passwordEncoder.encode(member.getPwd());
        member.setPwd(encodePwd);

        memberRepository.save(modelMapper.map(member, Member.class));
    }
}
