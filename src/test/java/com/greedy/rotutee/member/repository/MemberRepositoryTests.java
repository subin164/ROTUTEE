package com.greedy.rotutee.member.repository;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.entity.*;
import com.greedy.rotutee.member.member.repository.*;
import com.greedy.rotutee.member.profile.dto.TutorInfoDTO;
import com.greedy.rotutee.member.profile.entity.TutorInfo;
import com.greedy.rotutee.member.profile.repository.TutorInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
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

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TutorInfoRepository tutorInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MemberStatusHistoryRepository memberStatusHistoryRepository;

    @Autowired
    private MemberAchievementRepository memberAchievementRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private MemberAchievementHistoryRepository memberAchievementHistoryRepository;

    @Autowired
    private MemberStatusHistoryRepositoryQuery memberStatusHistoryRepositoryQuery;

    @PersistenceContext
    private EntityManager entityManager;
    
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
        Role role = roleRepository.findRoleByNo(3);

        //when
        List<Member> memberList = memberRepository.findByMemberRoleListRoleNo(3);

        //then
        assertNotNull(memberList);
        memberList.forEach(System.out::println);

    }

    @Test
    public void 회원_가입_상태_설정_테스트_메서드() {

        //given
        Member member = memberRepository.findById(14).get();
        MemberStatusHistory memberStatusHistory = new MemberStatusHistory();
        memberStatusHistory.setMember(member);
        memberStatusHistory.setStatus("활동");
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        memberStatusHistory.setHistoryDate(date);

        //when
        memberStatusHistoryRepository.save(memberStatusHistory);

        //then
    }

    @Test
    public void 회원_상태_조회_테스트_메서드() {

        //given
        int memberNo = 14;

        //when
        String memberStatus = memberStatusHistoryRepositoryQuery.findMemberStatus(entityManager, memberNo);
        
        //then
        assertNotNull(memberStatus);
        System.out.println("memberStatus = " + memberStatus);
    }

    @Test
    public void 회원_가입_칭호_설정_테스트_메서드() {

        Member foundMember = memberRepository.findById(16).get();
        MemberAchievement memberAchievement = new MemberAchievement();
        memberAchievement.setAchievement(achievementRepository.findById(1).get());
        memberAchievement.setMember(foundMember);
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        memberAchievement.setGetDat(date);
        memberAchievementRepository.save(memberAchievement);

        MemberAchievementHistory memberAchievementHistory = new MemberAchievementHistory();
        memberAchievementHistory.setMemberAchievement(memberAchievementRepository.findByMemberAndAchievement(foundMember, achievementRepository.findById(1).get()));
        memberAchievementHistory.setMember(foundMember);
        memberAchievementHistory.setChangeDate(date);

        memberAchievementHistoryRepository.save(memberAchievementHistory);

    }

    @Test
    @Transactional
    public void 회원_칭호_획득_테스트_메서드() {

        //given

        //when
        MemberAchievement memberAchievement = new MemberAchievement();
        memberAchievement.setAchievement(achievementRepository.findById(99).get());
        memberAchievement.setMember(memberRepository.findById(14).get());
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        memberAchievement.setGetDat(date);

        System.out.println("memberAchievement = " + memberAchievement);

        //then
        memberAchievementRepository.save(memberAchievement);
    }

    @Test
    @Transactional
    public void 회원_칭호_변경_테스트_메서드() {

        //given
        MemberAchievementHistory memberAchievementHistory = new MemberAchievementHistory();

        //when
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
