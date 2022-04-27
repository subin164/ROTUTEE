package com.greedy.rotutee.member.repository;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.member.repository.MemberRepository;
import com.greedy.rotutee.member.profile.dto.TutorInfoDTO;
import com.greedy.rotutee.member.profile.entity.TutorInfo;
import com.greedy.rotutee.member.profile.repository.TutorInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * packageName : com.greedy.rotutee.member.repository
 * fileName : MemberReositoryTests
 * author : 7sang
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-22 7sang 최초 생성
 */

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class MemberRepositoryTests {

    private final MemberRepository memberRepository;
    private final TutorInfoRepository tutorInfoRepository;
    private final PasswordEncoder passwordEncoder;


    public MemberRepositoryTests(MemberRepository memberRepository, TutorInfoRepository tutorInfoRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.tutorInfoRepository = tutorInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Test
    public void init() {
        assertNotNull(memberRepository);
        assertNotNull(tutorInfoRepository);
    }

    @Test
    public void 사용자_정보_조회_테스트_메서드() {

        //given
        int memberNo = 14;

        //when
        Member findMember = memberRepository.findById(memberNo).get();

        //then
        assertNotNull(findMember);
        System.out.println("findMember = " + findMember);
    }

    @Test
    public void 사용자_프로필_수정_테스트_메서드() {

        //given
        int loginMemberNo = 22;
        String loginMemberRole = "ROLE_TUTOR22";

        MemberDTO member = new MemberDTO();
        member.setIntroduction("테스트입니다용 하이용");
        member.setNickname("테스트중입니둥");
        member.setName("테스트이름");

        TutorInfoDTO tutorInfo = new TutorInfoDTO();
        tutorInfo.setAddress("테스트 주소이랑게");
        tutorInfo.setAccountNumber("1234-4567");
        tutorInfo.setBankName("농심");

        //when
        Member foundMember = memberRepository.findById(loginMemberNo).get();
        foundMember.setIntroduction(member.getIntroduction());
        foundMember.setNickname(member.getNickname());
        foundMember.setName(member.getName());

        if(loginMemberRole.equals("ROLE_TUTOR")) {
            TutorInfo foundTutorInfo = tutorInfoRepository.findById(loginMemberNo).get();
            foundTutorInfo.setAddress(tutorInfo.getAddress());
            foundTutorInfo.setAccountNumber(tutorInfo.getAccountNumber());
            foundTutorInfo.setBankName(tutorInfo.getBankName());
        }

        //then
        assertEquals(foundMember.getIntroduction(), member.getIntroduction());
        assertEquals(foundMember.getNickname(), member.getNickname());
        assertEquals(foundMember.getName(), member.getName());
    }

    @Test
    public void 전체_회원_조회_테스트_메서드() {

        //given

        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        assertNotNull(memberList);
        memberList.forEach(System.out::println);

    }

//    /* 모든 강의 카테고리 정보 조회용 메서드 */
//    public List<LectureCategoryDTO> findLectureCategoryList() {
//
//        List<LectureCategory> lectureCategoryList =  lectureCategoryRepository.findAll();
//
//        return lectureCategoryList.stream().map(lectureCategory ->
//                modelMapper.map(lectureCategory, LectureCategoryDTO.class)).collect(Collectors.toList());
//    }

//    /* 사용자 등록용 메서드 */
//    @Transactional
//    public void registMember(MemberDTO member) {
//
//        String encodePwd = passwordEncoder.encode(member.getPwd());
//        member.setPwd(encodePwd);
//        long miliseconds = System.currentTimeMillis();
//        Date date = new Date(miliseconds);
//        member.setRegistrationDate(date);
//        member.setLeaveStatusYn("N");
//
//        memberRepository.save(modelMapper.map(member, Member.class));
//
//        setMemberRole(member);
//    }
//
//    /* 사용자 등록시 권한등록용 메서드 */
//    private void setMemberRole(MemberDTO member) {
//
//        MemberRole memberRole = new MemberRole();
//        memberRole.setMember(memberRepository.findMemberByEmail(member.getEmail()));
//        memberRole.setRole(roleRepository.findRoleByNo(3));
//        memberRoleRepository.save(memberRole);
//    }

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
