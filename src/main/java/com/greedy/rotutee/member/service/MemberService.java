package com.greedy.rotutee.member.service;

import com.greedy.rotutee.member.dto.LectureCategoryDTO;
import com.greedy.rotutee.member.dto.MemberDTO;
import com.greedy.rotutee.member.entity.LectureCategory;
import com.greedy.rotutee.member.entity.Member;
import com.greedy.rotutee.member.entity.MemberRole;
import com.greedy.rotutee.member.repository.LectureCategoryRepository;
import com.greedy.rotutee.member.repository.MemberRepository;
import com.greedy.rotutee.member.repository.MemberRoleRepository;
import com.greedy.rotutee.member.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final LectureCategoryRepository lectureCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberService(LectureCategoryRepository lectureCategoryRepository, PasswordEncoder passwordEncoder, MemberRepository memberRepository, MemberRoleRepository memberRoleRepository, RoleRepository roleRepository, ModelMapper modelMapper) {

        this.lectureCategoryRepository = lectureCategoryRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        this.memberRoleRepository = memberRoleRepository;
        this.roleRepository = roleRepository;
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

    @Transactional
    public void registMember(MemberDTO member) {

        String encodePwd = passwordEncoder.encode(member.getPwd());
        member.setPwd(encodePwd);
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        member.setRegistrationDate(date);
        member.setLeaveStatusYn("N");

        memberRepository.save(modelMapper.map(member, Member.class));

        setMemberRole(member);
    }

    private void setMemberRole(MemberDTO member) {

        MemberRole memberRole = new MemberRole();
        memberRole.setMember(memberRepository.findMemberByEmail(member.getEmail()));
        memberRole.setRole(roleRepository.findRoleByNo(3));
        memberRoleRepository.save(memberRole);
    }

    public boolean duplicateEmail(String checkEmail) {

        System.out.println("memberRepository.findMemberByEmail(checkEmail) = " + memberRepository.findMemberByEmail(checkEmail));
        
        return memberRepository.findMemberByEmail(checkEmail) == null ? true : false;
    }
}
