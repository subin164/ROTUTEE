package com.greedy.rotutee.member.tutorRequest.service;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.member.entity.Role;
import com.greedy.rotutee.member.member.repository.MemberRepository;
import com.greedy.rotutee.member.member.repository.RoleRepository;
import com.greedy.rotutee.member.profile.dto.AttachedFileDTO;
import com.greedy.rotutee.member.profile.entity.AttachedFile;
import com.greedy.rotutee.member.profile.entity.TutorInfo;
import com.greedy.rotutee.member.profile.repository.AttachedFileRepository;
import com.greedy.rotutee.member.profile.repository.TutorInfoRepository;
import com.greedy.rotutee.member.tutorRequest.dto.CareerDTO;
import com.greedy.rotutee.member.tutorRequest.entity.Career;
import com.greedy.rotutee.member.tutorRequest.dto.QualificationDTO;
import com.greedy.rotutee.member.tutorRequest.dto.TutorApplyDTO;
import com.greedy.rotutee.member.tutorRequest.entity.Notice;
import com.greedy.rotutee.member.tutorRequest.entity.Qualification;
import com.greedy.rotutee.member.tutorRequest.entity.TutorApply;
import com.greedy.rotutee.member.tutorRequest.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.service
 * fileName : TutorRequestService
 * author : 7sang
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-22 7sang 최초 생성
 */

@Service
public class TutorRequestService {

    private final AttachedFileRepository attachedFileRepository;
    private final CareerRepository careerRepository;
    private final QualificationRepository qualificationRepository;
    private final TutorApplyRepository tutorApplyRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final NoticeRepository noticeRepository;
    private final NoticeCategoryRepository noticeCategoryRepository;
    private final TutorInfoRepository tutorInfoRepository;


    @Autowired
    public TutorRequestService(AttachedFileRepository attachedFileRepository, CareerRepository careerRepository, QualificationRepository qualificationRepository, TutorApplyRepository tutorApplyRepository, MemberRepository memberRepository, ModelMapper modelMapper, RoleRepository roleRepository, NoticeRepository noticeRepository, NoticeCategoryRepository noticeCategoryRepository, TutorInfoRepository tutorInfoRepository) {
        this.attachedFileRepository = attachedFileRepository;
        this.careerRepository = careerRepository;
        this.qualificationRepository = qualificationRepository;
        this.tutorApplyRepository = tutorApplyRepository;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.noticeRepository = noticeRepository;
        this.noticeCategoryRepository = noticeCategoryRepository;
        this.tutorInfoRepository = tutorInfoRepository;
    }

    @Transactional
    public void proofFileUpload(List<AttachedFile> attachedFiles) throws Exception {

        if(attachedFiles.isEmpty()) {
            throw new Exception();
        }

        for(AttachedFile file : attachedFiles) {
            attachedFileRepository.save(file);
        }
    }

    @Transactional
    public void tutorRequest(CustomUser loginMember, TutorApplyDTO tutorApply, List<CareerDTO> careerList, List<QualificationDTO> qualificationList) {

        Member member = memberRepository.findById(loginMember.getNo()).get();

        TutorApply saveTutorApply = modelMapper.map(tutorApply, TutorApply.class);
        saveTutorApply.setMember(member);

        //경력사항이 존재한다면
        if(careerList != null) {

            List<Career> saveCareerList = new ArrayList<>();
            //경력사항을 entity로 매핑해준뒤 List에 add
            for(CareerDTO career : careerList) {
                Career saveCareer = modelMapper.map(career, Career.class);
                saveCareer.setTutorApply(saveTutorApply);
                saveCareerList.add(saveCareer);
            }
            //경력사항List를 신청내역에 담기
            saveTutorApply.setCareerList(saveCareerList);
        }

        //자격사항이 존재한다면
        if(qualificationList != null) {

            List<Qualification> saveQualificationList = new ArrayList<>();
            //자격사항을 entity로 매행해준뒤 List에 add
            for(QualificationDTO qualification : qualificationList) {
                Qualification saveQualification = modelMapper.map(qualification, Qualification.class);
                saveQualification.setTutorApply(saveTutorApply);
                saveQualificationList.add(saveQualification);
            }
            //자격사항List를 신청내역에 담기
            saveTutorApply.setQualificationList(saveQualificationList);
        }

        tutorApplyRepository.save(saveTutorApply);
    }

    public Page<TutorApplyDTO> findTutorRequestBeforeList(String status, Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("applyHistoryNo").descending());

        return tutorApplyRepository.findByApplyYn(status, pageable).map(tutorApply -> modelMapper.map(tutorApply, TutorApplyDTO.class));
    }

    public Page<TutorApplyDTO> findAllRequestList(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("applyHistoryNo").descending());

        return tutorApplyRepository.findAll(pageable).map(tutorApply -> modelMapper.map(tutorApply, TutorApplyDTO.class));
    }

    public Page<TutorApplyDTO> findSearchRequestList(Pageable pageable, String searchCondition, String searchValue) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("applyHistoryNo").descending());

        Page<TutorApply> searchList = null;

        //검색 조건별 신청 내역 목록 조회
        if("name".equals(searchCondition)) {
            //신청자 이름으로 조회
            searchList = tutorApplyRepository.findByMemberNameContaining(searchValue, pageable);
        } else if("email".equals(searchCondition)) {
            //신청자 이메일로 조회
            searchList = tutorApplyRepository.findByMemberEmailContaining(searchValue, pageable);
        } else if("status".equals(searchCondition)) {
            //승인상태로 조회(대기, 승인, 거절)
            searchList = tutorApplyRepository.findByApplyYnContaining(searchValue, pageable);
        }

        return searchList.map(tutorApply -> modelMapper.map(tutorApply, TutorApplyDTO.class));
    }

    public Page<TutorApplyDTO> findAllMyRequestList(Pageable pageable, int memberNo) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("applyHistoryNo").descending());

        return tutorApplyRepository.findByMemberNo(memberNo, pageable).map(tutorApply -> modelMapper.map(tutorApply, TutorApplyDTO.class));
    }

    public TutorApplyDTO findTutorRequestDetail(int historyNo) {

        return modelMapper.map(tutorApplyRepository.findById(historyNo).get(), TutorApplyDTO.class);
    }


    @Transactional
    public void rejectTutorApply(int historyNo) {

        TutorApply tutorApply = tutorApplyRepository.findById(historyNo).get();
        tutorApply.setApplyYn("거절");

        //거절시 해당 회원에게 거절 알림 발송
        Notice notice = new Notice();
        notice.setTime(new Date(System.currentTimeMillis()));
        notice.setNoticeCategory(noticeCategoryRepository.findById(2).get());
        notice.setContent("튜터신청이 거절되었습니다.");
        notice.setMember(memberRepository.findById(tutorApply.getMember().getNo()).get());

        noticeRepository.save(notice);
    }

    @Transactional
    public void approvedTutorApply(int historyNo) {

        TutorApply tutorApply = tutorApplyRepository.findById(historyNo).get();
        //승인시 해당 회원 튜터 권한 등록
        Member member = memberRepository.findById(tutorApply.getMember().getNo()).get();
        Role role = roleRepository.findById(4).get();

        member.getMemberRoleList().get(0).setRole(role);
        tutorApply.setApplyYn("승인");

        setNotice(member);
    }

    @Transactional
    public void setNotice(Member member) {
        //승인시 해당 회원에게 승인 알림 발송
        Notice notice = new Notice();
        notice.setTime(new Date(System.currentTimeMillis()));
        notice.setNoticeCategory(noticeCategoryRepository.findById(2).get());
        notice.setContent("튜터신청이 승인되었습니다.");
        notice.setMember(member);

        noticeRepository.save(notice);
    }

//    @Transactional
//    public void setTutorInfo(int  historyNo) {
//
//        TutorApply tutorApply = tutorApplyRepository.findById(historyNo).get();
//        Member member = memberRepository.findById(tutorApply.getMember().getNo()).get();
//
//        //승인시 튜터정보 초기화
//        TutorInfo tutorInfo = new TutorInfo();
//        tutorInfo.setAddress("미입력&미입력&미입력");
//        tutorInfo.setBankName("미입력");
//        tutorInfo.setAccountNumber("미입력");
//        tutorInfo.setMember(member);
//        tutorInfo.setMemberNo(member.getNo());
//        tutorInfoRepository.save(tutorInfo);
//    }


    public Page<AttachedFileDTO> findAllAttachedFileList(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("attachedFileNo").descending());

        String division = "서류";
        Page<AttachedFile> attachedFilesList = attachedFileRepository.findByDivisionAndFileDeletionYn(division, "N ", pageable);

        return attachedFilesList.map(attachedFile -> modelMapper.map(attachedFile, AttachedFileDTO.class));
    }
}
