package com.greedy.rotutee.member.member.service;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.member.member.dto.LectureCategoryDTO;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.entity.LectureCategory;
import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.member.entity.MemberRole;
import com.greedy.rotutee.member.member.repository.LectureCategoryRepository;
import com.greedy.rotutee.member.member.repository.MemberRepository;
import com.greedy.rotutee.member.member.repository.MemberRoleRepository;
import com.greedy.rotutee.member.member.repository.RoleRepository;
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

    /* 사용자번호로 사용자 정보 조회용 메서드 */
    public MemberDTO findMember(int memberNo) {

        return modelMapper.map(memberRepository.findById(memberNo).get(), MemberDTO.class);
    }

    /* 모든 강의 카테고리 정보 조회용 메서드 */
    public List<LectureCategoryDTO> findLectureCategoryList() {

        List<LectureCategory> lectureCategoryList =  lectureCategoryRepository.findAll();

        return lectureCategoryList.stream().map(lectureCategory ->
                modelMapper.map(lectureCategory, LectureCategoryDTO.class)).collect(Collectors.toList());
    }

    /* 사용자 등록용 메서드 */
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

    /* 사용자 등록시 권한등록용 메서드 */
    private void setMemberRole(MemberDTO member) {

        MemberRole memberRole = new MemberRole();
        memberRole.setMember(memberRepository.findMemberByEmail(member.getEmail()));
        memberRole.setRole(roleRepository.findRoleByNo(3));
        memberRoleRepository.save(memberRole);
    }

    /* 이메일 중복 확인용 메서드 */
    public boolean duplicateEmail(String checkEmail) {

        return memberRepository.findMemberByEmail(checkEmail) == null ? true : false;
    }

    /* 비밀번호 재등록용 메서드 */
    @Transactional
    public void findMemberPwd(MemberDTO member) {

        String encodePwd = passwordEncoder.encode(member.getPwd());

        Member findMember = memberRepository.findMemberByEmail(member.getEmail());
        findMember.setPwd(encodePwd);
    }

    /* 비밀번호 수정용 메서드 */
    @Transactional
    public void modifyPassword(CustomUser loginMember, String modifyPassword) {

        String encodePwd = passwordEncoder.encode(modifyPassword);

        Member findMember = memberRepository.findById(loginMember.getNo()).get();

        findMember.setPwd(encodePwd);
        System.out.println("encodePwd = " + encodePwd);
        System.out.println("findMember = " + findMember);
    }


}
