package com.greedy.rotutee.Authentication.service;

import com.greedy.rotutee.Authentication.dto.CustomUser;

import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.dto.MemberRoleDTO;
import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.member.entity.MemberStatusHistory;
import com.greedy.rotutee.member.member.entity.RoleMenuUrl;
import com.greedy.rotutee.member.member.repository.MemberStatusHistoryRepositoryQuery;
import com.greedy.rotutee.member.member.repository.RoleMenuUrlRepository;
import com.greedy.rotutee.member.member.repository.MemberRepository;
import com.greedy.rotutee.member.member.repository.MemberRoleRepository;
import com.greedy.rotutee.member.member.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final RoleMenuUrlRepository roleMenuUrlRepositoryl;
    private final ModelMapper modelMapper;
    private final MemberStatusHistoryRepositoryQuery memberStatusHistoryRepositoryQuery;


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AuthenticationServiceImpl(MemberRepository memberRepository, MemberRoleRepository memberRoleRepository, RoleMenuUrlRepository roleMenuUrlRepositoryl, ModelMapper modelMapper, MemberStatusHistoryRepositoryQuery memberStatusHistoryRepositoryQuery) {
        this.memberRepository = memberRepository;
        this.memberRoleRepository = memberRoleRepository;
        this.roleMenuUrlRepositoryl = roleMenuUrlRepositoryl;
        this.modelMapper = modelMapper;
        this.memberStatusHistoryRepositoryQuery = memberStatusHistoryRepositoryQuery;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username = " + username);
        
        Member member = memberRepository.findMemberByEmail(username);

        System.out.println("member = " + member);
        
        if(member == null) {
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }

        MemberStatusHistory memberStatusHistory = memberStatusHistoryRepositoryQuery.findMemberStatus(entityManager, member.getNo());

        System.out.println("memberStatusHistory.getStatus() = " + memberStatusHistory.getStatus());

        MemberDTO loginMember = modelMapper.map(member, MemberDTO.class);

        List<MemberRoleDTO> memberRoleList = loginMember.getMemberRoleList();

        List<GrantedAuthority> authorities = new ArrayList<>();
        memberRoleList.forEach(memberRole -> authorities.add(new SimpleGrantedAuthority(memberRole.getRole().getName())));

        System.out.println("authorities = " + authorities);

        return new CustomUser(loginMember, authorities);
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

        List<RoleMenuUrl> adminRoleList = roleMenuUrlRepositoryl.findRoleMenuUrlByRoleNo(1);
        List<RoleMenuUrl> subAdminRoleList = roleMenuUrlRepositoryl.findRoleMenuUrlByRoleNo(2);
        List<RoleMenuUrl> tuteeRoleList = roleMenuUrlRepositoryl.findRoleMenuUrlByRoleNo(3);
        List<RoleMenuUrl> tutorRoleList = roleMenuUrlRepositoryl.findRoleMenuUrlByRoleNo(4);
        List<RoleMenuUrl> memberRoleList = roleMenuUrlRepositoryl.findRoleMenuUrlByRoleNo(5);
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
