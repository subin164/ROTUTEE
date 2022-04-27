/*
 * 작성자 : 김형경
 * 작성일 : 2022/04/18
 * 작성내용 : Study Service 처리
 *
 * */

package com.greedy.rotutee.study.service;

import com.greedy.rotutee.member.member.repository.MemberRepository;
import com.greedy.rotutee.study.dto.StudyByTagDTO;
import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.dto.TagDTO;
import com.greedy.rotutee.study.entity.Study;
import com.greedy.rotutee.study.entity.StudyByTag;
import com.greedy.rotutee.study.entity.StudyTag;
import com.greedy.rotutee.study.repository.StudyByTagRepository;
import com.greedy.rotutee.study.repository.StudyRepository;
import com.greedy.rotutee.study.repository.StudyTagRepository;
import com.sun.xml.bind.v2.runtime.unmarshaller.TagName;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class StudyService {

    private final StudyRepository studyRepository;
    private final StudyTagRepository studyTagRepository;
    private final StudyByTagRepository studyByTagRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudyService(StudyRepository studyRepository, StudyTagRepository studyTagRepository, StudyByTagRepository studyByTagRepository, MemberRepository memberRepository, ModelMapper modelMapper) {
        this.studyRepository = studyRepository;
        this.studyTagRepository = studyTagRepository;
        this.studyByTagRepository = studyByTagRepository;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    /*
     * writer : 김형경
     * writerDate : 22/04/19 ~ 22/04/26
     * title : 모집글 조회
     * content : 스터디 모집 조회 서비스 처리 하여 DB에서 조회 결과를 반환
     * */

    public Page<StudyDTO> findByStudyList(String searchCondition, String searchTag, Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize());

        Page<Study> studyList = null;

        if (searchCondition != null && !"".equals(searchCondition)) {

            studyList = studyRepository.findByTitleContainingAndStatus(searchCondition, "Y", pageable);
        } else {
            studyList = studyRepository.findByStatus("Y", pageable);
        }

        List<StudyByTag> studyTagList = studyByTagRepository.findAll();

        System.out.println("StudyByTagLi : " + studyTagList);

        return studyList.map(study -> modelMapper.map(study, StudyDTO.class));
    }

    /*
     * writer : 김형경
     * writerDate : 22/04/18 ~ 22/04/26
     * title : 모집글 작성
     * content : 스터디 모집글 작성 서비스 처리 하여 DB에 저장
     * */
    @Transactional
    public void studyRegist(StudyDTO studyDTO, List<String> tagList) {

        Study study = studyRepository.save(modelMapper.map(studyDTO, Study.class));

        TagDTO tagDTO = new TagDTO();

        StudyByTagDTO studyByTagDTO = new StudyByTagDTO();


        for (int i = 0; i < tagList.size(); i++) {

            List<StudyTag> equalTagName = studyTagRepository.findAll();

            boolean duplicated = false;

            for (int j = 0; j < equalTagName.size(); j++) {

                TagDTO duplicatedTag = modelMapper.map(equalTagName.get(j), TagDTO.class);

                if (duplicatedTag.getTagName().equals(tagList.get(i))) {
                    tagDTO.setTagName(tagList.get(i));

                    duplicated = true;
                }
            }
            if (duplicated == false) {
                tagDTO.setTagName(tagList.get(i));
                StudyTag studyTag = studyTagRepository.save(modelMapper.map(tagDTO, StudyTag.class));
            }
//
//            studyByTagDTO.setStudyNo(modelMapper.map(study,StudyDTO.class));
//            studyByTagDTO.setTagNo(modelMapper.map(studyTag));
//
//            studyByTagRepository.save(modelMapper.map(studyByTagDTO, StudyByTag.class));
        }

    }
}


//        for (int i = 0; i < tagList.size(); i++) {
//            if (!equalTagList.isEmpty()) {
//
//                System.out.println("값있음");
//
//
//            }else{
//                System.out.println("값없음");
//                tagDTO.setTagName(tagList.get(i));
//
//                StudyTag newStudyTag = studyTagRepository.save(modelMapper.map(tagDTO, StudyTag.class));
//            }
//        }


//    //    스터디 모집글 전체 조회
//    public Page<StudyDTO> findStudyList(String searchCondition, String searchTag, Pageable pageable) {
//
//        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
//                pageable.getPageSize(), Sort.by("studyNo").descending());
//
//        Page<Study> studyList = null;
//
//
//        if (searchCondition != null && !"".equals(searchCondition)) {
//            searchCondition = "%" + searchCondition + "%";
//            studyList = studyRepository.findByTitleLike(searchCondition, pageable);
//
//        }else if (searchTag != null && !"".equals(searchTag)) {
//            studyList = studyRepository.findByTagName(searchTag, pageable);
//
//        } else {
//            studyList = studyRepository.findAll(pageable);
//        }
//
//        return studyList.map(study -> modelMapper.map(study, StudyDTO.class));
//    }

//    스터디 태그 리스트 조회
//    public List<TagDTO> findStudyTagList() {

//        System.out.println("===============rlahWL============================");
//        System.out.println(studyTagRepository.findAll());
//        System.out.println("=================================================");
//
//        studyTagRepository.findAll().stream().map(studyTag -> modelMapper.map(studyTag, TagDTO.class)).collect(Collectors.toList());
//        List<StudyByTag> studyTag = studyTagRepository.findAll();
//
//        System.out.println("studyTag = " + studyTag);
//        System.out.println("=======================반환후===================");
//        System.out.println(studyTag.stream().map(studyTag1 -> modelMapper.map(studyTag1, TagDTO.class)).collect(Collectors.toList()));
//
//        return studyTag.stream().map(studyTag1 -> modelMapper.map(studyTag1, TagDTO.class)).collect(Collectors.toList());
//    }

//    study 모집글 작성
//    @Transactional
//    public void studyRegist(StudyDTO studyDTO, List<String> addTagList, MemberDTO memberDTO) {

//        Member member = memberRepository.findById(memberDTO.getNo()).get();
//
//        System.out.println("맴버번호 조회결과  = " + member);
//
//        studyDTO.setWriter(modelMapper.map(member, memberDTO.getClass()));
//
//        System.out.println("스터디 디티오 작성자 조회 = " + studyDTO);
//
//        Study study = studyRepository.save(modelMapper.map(studyDTO, Study.class));
//
//        System.out.println("스터디 엔티티 결과 = " + study);
//
//        for (int i = 1; i < addTagList.size(); i++) {
//            TagDTO tagDTO = new TagDTO();
//            StudyByTag studyTag = modelMapper.map(tagDTO, StudyByTag.class);
//
//            studyTag.setStudyNo(study);
//            studyTag.setTagName(addTagList.get(i));
//            studyTagRepository.save(studyTag);
//        }
//
//    }
//
//
//    모집글 상세페이지 조회
//    public StudyDTO findDetailByStudyNo(int studyNo) {
//        Study study = studyRepository.findById(studyNo).get();
//
//        return modelMapper.map(study, StudyDTO.class);
//
//    }
//
//    // 모집글 삭제
//    public void studyRemove(int studyNo) {
//        Study study = studyRepository.findById((studyNo)).get();
//        study.setStatus("N");
//    }
