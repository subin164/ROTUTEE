package com.greedy.rotutee.Authentication.service;

import com.greedy.rotutee.Authentication.dto.CustomUser;

import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.dto.MemberRoleDTO;
import com.greedy.rotutee.member.member.entity.LoginHistory;
import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.member.entity.MemberStatusHistory;
import com.greedy.rotutee.member.member.entity.RoleMenuUrl;
import com.greedy.rotutee.member.member.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final RoleMenuUrlRepository roleMenuUrlRepository;
    private final ModelMapper modelMapper;
    private final MemberStatusHistoryRepositoryQuery memberStatusHistoryRepositoryQuery;
    private final LoginHistoryRepositoryQuery loginHistoryRepositoryQuery;
    private final LoginHistoryRepository loginHistoryRepository;
    private final MemberStatusHistoryRepository memberStatusHistoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AuthenticationServiceImpl(MemberRepository memberRepository, MemberRoleRepository memberRoleRepository, RoleMenuUrlRepository roleMenuUrlRepository, ModelMapper modelMapper, MemberStatusHistoryRepositoryQuery memberStatusHistoryRepositoryQuery, LoginHistoryRepositoryQuery loginHistoryRepositoryQuery, LoginHistoryRepository loginHistoryRepository, MemberStatusHistoryRepository memberStatusHistoryRepository) {
        this.memberRepository = memberRepository;
        this.memberRoleRepository = memberRoleRepository;
        this.roleMenuUrlRepository = roleMenuUrlRepository;
        this.modelMapper = modelMapper;
        this.memberStatusHistoryRepositoryQuery = memberStatusHistoryRepositoryQuery;
        this.loginHistoryRepositoryQuery = loginHistoryRepositoryQuery;
        this.loginHistoryRepository = loginHistoryRepository;
        this.memberStatusHistoryRepository = memberStatusHistoryRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findMemberByEmail(username);

        if(member == null) {
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }

        setLogin(member);
        
        MemberStatusHistory memberStatusHistory = memberStatusHistoryRepositoryQuery.findMemberStatus(entityManager, member.getNo());

        System.out.println("memberStatusHistory.getStatus() = " + memberStatusHistory.getStatus());

        if(memberStatusHistory.getStatus().equals("정지")) {
            checkStatus(member, memberStatusHistory);
        } else if(memberStatusHistory.getStatus().equals("탈퇴")) {
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }

        MemberDTO loginMember = modelMapper.map(member, MemberDTO.class);

        List<MemberRoleDTO> memberRoleList = loginMember.getMemberRoleList();

        List<GrantedAuthority> authorities = new ArrayList<>();
        memberRoleList.forEach(memberRole -> authorities.add(new SimpleGrantedAuthority(memberRole.getRole().getName())));

        System.out.println("authorities = " + authorities);

        return new CustomUser(loginMember, authorities);
    }

    @Transactional
    public void checkStatus(Member member, MemberStatusHistory memberStatusHistory) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        String endDate = simpleDateFormat.format(memberStatusHistory.getSuspensionHitory().getEndDate());
        String today = todayDate.replace("-", "");
        String endDay = endDate.replace("-", "");
        if(Integer.parseInt(today) >= Integer.parseInt(endDay)) {
            MemberStatusHistory playMemberStatusHistory = new MemberStatusHistory();
            playMemberStatusHistory.setMember(member);
            playMemberStatusHistory.setStatus("활동");
            playMemberStatusHistory.setHistoryDate(new Date(System.currentTimeMillis()));

            memberStatusHistoryRepository.save(playMemberStatusHistory);
        } else {
            throw new LockedException("정지된 회원입니다. 해지일은 " + endDate + " 입니다.\n해지일 이후로 로그인을 시도하시면 해지됩니다.");
        }
    }

    
    @Transactional
    public void setLogin(Member member) {

        LoginHistory newLogin = new LoginHistory();
        Date today = new Date(System.currentTimeMillis());
        newLogin.setLoginDate(today);
        newLogin.setMember(member);
        newLogin.setLoginIp("123");

        loginHistoryRepository.save(newLogin);

        LoginHistory loginHistory = loginHistoryRepositoryQuery.findMemberLoginHistory(entityManager, member.getNo());

        boolean isLoginCheck = true;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        String todayDate = simpleDateFormat.format(today);
        String loginDate = simpleDateFormat.format(loginHistory.getLoginDate());

        if(todayDate.equals(loginDate) || todayDate == loginDate) {
            isLoginCheck = false;
        }
        if(isLoginCheck) {
            member.setRouletteChance(member.getRouletteChance() + 1);
            System.out.println(member.getRouletteChance());

            memberRepository.save(member);
        }
    }

    @Override
    @Transactional
    public Map<String, List<String>> getPermitListMap() {

        Map<String, List<String>> permitListMap = new HashMap<>();
        List<String> adminPermitList = new ArrayList<>();
        List<String> subAdminPermitList = new ArrayList<>();
        List<String> tuteePermitList = new ArrayList<>();
        List<String> tutorPermitList = new ArrayList<>();
        List<String> memberPermitList = new ArrayList<>();

        List<RoleMenuUrl> adminRoleList = roleMenuUrlRepository.findRoleMenuUrlByRoleNo(1);
        List<RoleMenuUrl> subAdminRoleList = roleMenuUrlRepository.findRoleMenuUrlByRoleNo(2);
        List<RoleMenuUrl> tuteeRoleList = roleMenuUrlRepository.findRoleMenuUrlByRoleNo(3);
        List<RoleMenuUrl> tutorRoleList = roleMenuUrlRepository.findRoleMenuUrlByRoleNo(4);
        List<RoleMenuUrl> memberRoleList = roleMenuUrlRepository.findRoleMenuUrlByRoleNo(5);

        for(int i = 0; i < adminRoleList.size(); i++) {
            adminPermitList.add("/" + adminRoleList.get(i).getMenuUrl().getName() + "/" + adminRoleList.get(i).getMenuDetail().getName());
        }

        for(int i = 0; i < subAdminRoleList.size(); i++) {
            subAdminPermitList.add("/" + subAdminRoleList.get(i).getMenuUrl().getName() + "/" + subAdminRoleList.get(i).getMenuDetail().getName());
        }

        for(int i = 0; i < tuteeRoleList.size(); i++) {
            tuteePermitList.add("/" + tuteeRoleList.get(i).getMenuUrl().getName() + "/" + tuteeRoleList.get(i).getMenuDetail().getName());
        }

        for(int i = 0; i < tutorRoleList.size(); i++) {
            tutorPermitList.add("/" + tutorRoleList.get(i).getMenuUrl().getName() + "/" + tutorRoleList.get(i).getMenuDetail().getName());
        }

        for(int i = 0; i < memberRoleList.size(); i++) {
            memberPermitList.add("/" + memberRoleList.get(i).getMenuUrl().getName() + "/" + memberRoleList.get(i).getMenuDetail().getName());
        }

/*        System.out.println("adminRoleList = " + adminRoleList);
        System.out.println("subAdminRoleList = " + subAdminRoleList);
        System.out.println("tuteeRoleList = " + tuteeRoleList);
        System.out.println("tutorRoleList = " + tutorRoleList);
        System.out.println("memberRoleList = " + memberRoleList);*/

        permitListMap.put("adminPermitList", adminPermitList);
        permitListMap.put("subAdminPermitList", subAdminPermitList);
        permitListMap.put("tuteePermitList", tuteePermitList);
        permitListMap.put("tutorPermitList", tutorPermitList);
        permitListMap.put("memberPermitList", memberPermitList);

        return permitListMap;
    }
}
