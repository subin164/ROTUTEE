package com.greedy.rotutee.study.service;

import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.member.repository.MemberRepository;
import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.dto.TagDTO;
import com.greedy.rotutee.study.entity.Study;
import com.greedy.rotutee.study.entity.StudyTag;
import com.greedy.rotutee.study.repository.StudyRepository;
import com.greedy.rotutee.study.repository.StudyTagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyService {

    private final StudyRepository studyRepository;
    private final StudyTagRepository studyTagRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudyService(StudyRepository studyRepository, StudyTagRepository studyTagRepository, MemberRepository memberRepository, ModelMapper modelMapper) {
        this.studyRepository = studyRepository;
        this.studyTagRepository = studyTagRepository;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    //    스터디 모집글 전체 조회
    public Page<StudyDTO> findStudyList(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize());

        Page<Study> study = studyRepository.findAll(pageable);

        return study.map(study1 -> modelMapper.map(study1, StudyDTO.class));
    }

//    스터디 태그 리스트 조회
    public List<TagDTO> findStudyTagList() {

        System.out.println("===============rlahWL============================");
        System.out.println(studyTagRepository.findAll());
        System.out.println("=================================================");

        studyTagRepository.findAll().stream().map(studyTag -> modelMapper.map(studyTag, TagDTO.class)).collect(Collectors.toList());
        List<StudyTag> studyTag = studyTagRepository.findAll();

        System.out.println("studyTag = " + studyTag);
        System.out.println("=======================반환후===================");
        System.out.println(studyTag.stream().map(studyTag1 -> modelMapper.map(studyTag1, TagDTO.class)).collect(Collectors.toList()));

        return studyTag.stream().map(studyTag1 -> modelMapper.map(studyTag1, TagDTO.class)).collect(Collectors.toList());
    }

    //    study 모집글 작성
    @Transactional
    public void studyRegist(StudyDTO studyDTO, List<String> addTagList, MemberDTO memberDTO) {

        Member member = memberRepository.findById(memberDTO.getNo()).get();

        System.out.println("맴버번호 조회결과  = " + member);

        studyDTO.setWriter(modelMapper.map(member, memberDTO.getClass()));

        System.out.println("스터디 디티오 작성자 조회 = " + studyDTO);

        Study study = studyRepository.save(modelMapper.map(studyDTO, Study.class));

        System.out.println("스터디 엔티티 결과 = " + study);

        for (int i = 1; i < addTagList.size(); i++) {
            TagDTO tagDTO = new TagDTO();
            StudyTag studyTag = modelMapper.map(tagDTO, StudyTag.class);

            studyTag.setStudyNo(study);
            studyTag.setTagName(addTagList.get(i));
            studyTagRepository.save(studyTag);


        }

    }


    //    모집글 상세페이지 조회
    public StudyDTO findDetailByStudyNo(int studyNo) {
        Study study = studyRepository.findById(studyNo).get();

        return modelMapper.map(study, StudyDTO.class);

    }

    // 모집글 삭제
    public void studyRemove(int studyNo) {
        Study study = studyRepository.findById((studyNo)).get();
        study.setStatus("N");
    }
}
