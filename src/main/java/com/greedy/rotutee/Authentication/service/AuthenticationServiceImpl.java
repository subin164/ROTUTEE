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

        System.out.println("?????? ?????? 1");

        Member member = memberRepository.findMemberByEmail(username);

        if(member == null) {
            throw new UsernameNotFoundException("??????????????? ???????????? ????????????.");
        }

        checkLogin(member);
        setLogin(member);

        MemberStatusHistory memberStatusHistory = memberStatusHistoryRepositoryQuery.findMemberStatus(entityManager, member.getNo());

        System.out.println("memberStatusHistory.getStatus() = " + memberStatusHistory.getStatus());

        if(memberStatusHistory.getStatus().equals("??????")) {
            checkStatus(member, memberStatusHistory);
        } else if(memberStatusHistory.getStatus().equals("??????")) {
            throw new LockedException("??????????????? ???????????? ????????????.");
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
            playMemberStatusHistory.setStatus("??????");
            playMemberStatusHistory.setHistoryDate(new Date(System.currentTimeMillis()));

            memberStatusHistoryRepository.save(playMemberStatusHistory);
        } else {
            throw new LockedException("????????? ???????????????. ???????????? " + endDate + " ?????????.\n????????? ????????? ???????????? ??????????????? ???????????????.");
        }
    }

    
    @Transactional
    public void checkLogin(Member member) {

        Date today = new Date(System.currentTimeMillis());
        List<LoginHistory> loginHistory = loginHistoryRepositoryQuery.findMemberLoginHistory(entityManager, member.getNo());

        if(loginHistory.size() == 0) {
            member.setRouletteChance(member.getRouletteChance() + 1);
            memberRepository.save(member);

            return;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy??? MM??? dd???");
        String todayDate = simpleDateFormat.format(today);
        String loginDate = simpleDateFormat.format(loginHistory.get(0).getLoginDate());

        System.out.println("todayDate = " + todayDate);
        System.out.println("loginDate = " + loginDate);

        if(todayDate.equals(loginDate) || todayDate == loginDate) {
            System.out.println("??????1");
        } else {
            member.setRouletteChance(member.getRouletteChance() + 1);
            System.out.println(member.getRouletteChance());
            System.out.println("??????2");

            memberRepository.save(member);
        }
    }

    @Transactional
    public void setLogin(Member member) {

        LoginHistory newLogin = new LoginHistory();
        newLogin.setLoginDate(new Date(System.currentTimeMillis()));
        newLogin.setMember(member);
        newLogin.setLoginIp("123");

        loginHistoryRepository.save(newLogin);
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
//
        List<RoleMenuUrl> adminRoleList = roleMenuUrlRepository.findByRoleNo(1);
        List<RoleMenuUrl> subAdminRoleList = roleMenuUrlRepository.findByRoleNo(2);
        List<RoleMenuUrl> tuteeRoleList = roleMenuUrlRepository.findByRoleNo(3);
        List<RoleMenuUrl> tutorRoleList = roleMenuUrlRepository.findByRoleNo(4);
        List<RoleMenuUrl> memberRoleList = roleMenuUrlRepository.findByRoleNo(5);

        System.out.println(" ?????????1 ");
        for(int i = 0; i < adminRoleList.size(); i++) {

            adminPermitList.add(adminRoleList.get(i).getMenu().getUrl());
        }
        System.out.println(" ?????????2 ");
        for(int i = 0; i < subAdminRoleList.size(); i++) {

            subAdminPermitList.add(subAdminRoleList.get(i).getMenu().getUrl());
        }
        System.out.println("??????");
        for(int i = 0; i < tuteeRoleList.size(); i++) {
            tuteePermitList.add(tuteeRoleList.get(i).getMenu().getUrl());
        }
        System.out.println("??????");
        for(int i = 0; i < tutorRoleList.size(); i++) {
            tutorPermitList.add(tutorRoleList.get(i).getMenu().getUrl());
        }
        System.out.println("??????");
        for(int i = 0; i < memberRoleList.size(); i++) {
            memberPermitList.add(memberRoleList.get(i).getMenu().getUrl());
        }

        permitListMap.put("adminPermitList", adminPermitList);
        permitListMap.put("subAdminPermitList", subAdminPermitList);
        permitListMap.put("tuteePermitList", tuteePermitList);
        permitListMap.put("tutorPermitList", tutorPermitList);
        permitListMap.put("memberPermitList", memberPermitList);

        return permitListMap;
    }
}
