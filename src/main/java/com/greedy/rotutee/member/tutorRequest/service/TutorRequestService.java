package com.greedy.rotutee.member.tutorRequest.service;

import com.greedy.rotutee.Authentication.dto.CustomUser;
import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.member.repository.MemberRepository;
import com.greedy.rotutee.member.profile.dto.AchievementDTO;
import com.greedy.rotutee.member.profile.entity.AttachedFile;
import com.greedy.rotutee.member.profile.repository.AttachedFileRepository;
import com.greedy.rotutee.member.tutorRequest.dto.CareerDTO;
import com.greedy.rotutee.member.tutorRequest.entity.Career;
import com.greedy.rotutee.member.tutorRequest.dto.QualificationDTO;
import com.greedy.rotutee.member.tutorRequest.dto.TutorApplyDTO;
import com.greedy.rotutee.member.tutorRequest.entity.Qualification;
import com.greedy.rotutee.member.tutorRequest.entity.TutorApply;
import com.greedy.rotutee.member.tutorRequest.repository.CareerRepository;
import com.greedy.rotutee.member.tutorRequest.repository.QualificationRepository;
import com.greedy.rotutee.member.tutorRequest.repository.TutorApplyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    @Autowired
    public TutorRequestService(AttachedFileRepository attachedFileRepository, CareerRepository careerRepository, QualificationRepository qualificationRepository, TutorApplyRepository tutorApplyRepository, MemberRepository memberRepository, ModelMapper modelMapper) {
        this.attachedFileRepository = attachedFileRepository;
        this.careerRepository = careerRepository;
        this.qualificationRepository = qualificationRepository;
        this.tutorApplyRepository = tutorApplyRepository;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

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

        System.out.println("careerList = " + careerList);
        System.out.println("qualificationList = " + qualificationList);

        if(careerList != null) {

            List<Career> saveCareerList = new ArrayList<>();

            for(CareerDTO career : careerList) {
                Career saveCareer = modelMapper.map(career, Career.class);
                saveCareer.setTutorApply(saveTutorApply);
                saveCareerList.add(saveCareer);
            }

            saveTutorApply.setCareerList(saveCareerList);
        }

        if(qualificationList != null) {

            List<Qualification> saveQualificationList = new ArrayList<>();

            for(QualificationDTO qualification : qualificationList) {
                Qualification saveQualification = modelMapper.map(qualification, Qualification.class);
                saveQualification.setTutorApply(saveTutorApply);
                saveQualificationList.add(saveQualification);
            }

            saveTutorApply.setQualificationList(saveQualificationList);
        }

        tutorApplyRepository.save(saveTutorApply);
    }

    public List<TutorApplyDTO> findTutorRequestList() {

        List<TutorApply> tutorApplyList = tutorApplyRepository.findAll();

        return tutorApplyList.stream().map(tutorApply -> modelMapper.map(tutorApply, TutorApplyDTO.class)).collect(Collectors.toList());
    }

    public TutorApplyDTO findTutorRequestDetail(int historyNo) {

        return modelMapper.map(tutorApplyRepository.findById(historyNo).get(), TutorApplyDTO.class);
    }
}
