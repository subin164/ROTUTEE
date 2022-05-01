package com.greedy.rotutee.member.admin.service;


import com.greedy.rotutee.member.admin.dto.MemberDTO;
import com.greedy.rotutee.member.admin.repository.MemberRepository;
import com.greedy.rotutee.member.admin.repository.RoleRepository;
import com.greedy.rotutee.member.admin.entity.Member;
import com.greedy.rotutee.member.admin.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName : com.greedy.rotutee.member.admin.service
 * fileName : AdminService
 * author : 7sang
 * date : 2022-05-01
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-01 7sang 최초 생성
 */

@Service
public class AdminService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminService(MemberRepository memberRepository, ModelMapper modelMapper, RoleRepository roleRepository) {

        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    /* 전체 관리자 조회용 메서드 */
    public Page<MemberDTO> findAllAdmin(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("no").descending());

        return memberRepository.findByMemberRoleListRoleNo(2, pageable).map(member -> modelMapper.map(member, MemberDTO.class));
    }

    /* 관리자 권한 등록용 메서드 */
    @Transactional
    public void registAdmin(String adminEmail) {

        Member member = memberRepository.findMemberByEmail(adminEmail);
        Role adminRole = roleRepository.findById(2).get();

        member.getMemberRoleList().get(0).setRole(adminRole);
    }

    /* 관리자 권한 수정용 메서드 */
    @Transactional
    public void removeAdmin(String adminEmail) {

        Member member = memberRepository.findMemberByEmail(adminEmail);
        Role adminRole = roleRepository.findById(3).get();

        member.getMemberRoleList().get(0).setRole(adminRole);
    }


    /* 회원 검색 조건 조회용 메서드 */
    public MemberDTO findSearchMember(String searchValue) {

        Member saerchMember = memberRepository.findMemberByEmail(searchValue);

        if(saerchMember == null) {
            saerchMember = new Member();
        }

        return modelMapper.map(saerchMember, MemberDTO.class);
    }
}
