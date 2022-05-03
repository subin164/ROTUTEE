/*
 * 작성자 : 김형경
 * 작성일 : 2022/04/18
 * 작성내용 : Study Service 처리
 *
 * */

package com.greedy.rotutee.study.service;

import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.member.repository.MemberRepository;
import com.greedy.rotutee.study.dto.StudyByTagDTO;
import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.dto.StudyReplyDTO;
import com.greedy.rotutee.study.dto.TagDTO;
import com.greedy.rotutee.study.entity.Study;
import com.greedy.rotutee.study.entity.StudyByTag;
import com.greedy.rotutee.study.entity.StudyReply;
import com.greedy.rotutee.study.entity.StudyTag;
import com.greedy.rotutee.study.repository.StudyByTagRepository;
import com.greedy.rotutee.study.repository.StudyReplyRepository;
import com.greedy.rotutee.study.repository.StudyRepository;
import com.greedy.rotutee.study.repository.StudyTagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyService {

    private final StudyRepository studyRepository;
    private final StudyTagRepository studyTagRepository;
    private final StudyByTagRepository studyByTagRepository;
    private final StudyReplyRepository studyReplyRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudyService(StudyRepository studyRepository, StudyTagRepository studyTagRepository, StudyByTagRepository studyByTagRepository, StudyReplyRepository studyReplyRepository, MemberRepository memberRepository, ModelMapper modelMapper) {
        this.studyRepository = studyRepository;
        this.studyTagRepository = studyTagRepository;
        this.studyByTagRepository = studyByTagRepository;
        this.studyReplyRepository = studyReplyRepository;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    /*
     * writer : 김형경
     * writerDate : 22/04/19 ~ 22/04/27
     * title : 모집글 조회
     * content : 스터디 모집 조회 서비스 처리 하여 DB에서 조회 결과를 반환
     * */

    public Page<StudyDTO> findByStudyList(String searchCondition, String searchTag, Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize());

        Page<Study> studyList = null;

        if (searchCondition != null && !"".equals(searchCondition) &&
                (searchTag == null || "".equals(searchTag))) {
            studyList = studyRepository.findByTitleContainingAndPostStatus(searchCondition, "N", pageable);

        } else {
            studyList = studyRepository.findByPostStatus("N", pageable);
        }

        return studyList.map(study -> modelMapper.map(study, StudyDTO.class));
    }


    public List<StudyByTagDTO> finByStudyTagList() {
        List<StudyByTag> studyByTag = studyByTagRepository.findAll();

        return studyByTag.stream().map(studyByTag1 -> modelMapper.map(studyByTag1, StudyByTagDTO.class)).collect(Collectors.toList());
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

                    studyByTagDTO.setTag(duplicatedTag);

                }
            }
            if (duplicated == false) {

                tagDTO.setTagName(tagList.get(i));
                StudyTag studyTag = studyTagRepository.save(modelMapper.map(tagDTO, StudyTag.class));

                studyByTagDTO.setTag(modelMapper.map(studyTag, TagDTO.class));

            }

            studyByTagDTO.setStudy(modelMapper.map(study, StudyDTO.class));

            studyByTagRepository.save(modelMapper.map(studyByTagDTO, StudyByTag.class));
        }

    }

    /*
     * writer : 김형경
     * writerDate : 22/04/28 ~ 22/04/28
     * title : 모집글 상세페이지 조회
     * content : 스터디 모집글 상세페이지에서 받은 요청을 처리하여 DB조회 결과 반환
     * */
    public StudyDTO findStudyDetail(int studyNo) {

        Study study = studyRepository.findById(studyNo).get();
        System.out.println("선택한 스터디조회" + study);

        return modelMapper.map(study, StudyDTO.class);
    }

    public List<StudyByTagDTO> modifyStudyDetailTagList(int studyNo) {

        List<StudyByTag> studyByTag = studyByTagRepository.findByStudyStudyNo(studyNo);

        System.out.println("studyByTag : " + studyByTag);

        return studyByTag.stream().map(studyByTag1 -> modelMapper.map(studyByTag1, StudyByTagDTO.class)).collect(Collectors.toList());
    }

    public List<StudyReplyDTO> findStudyReply(int studyNo) {

        List<StudyReply> studyReply = studyReplyRepository.findByStudyNoAndReplyStatus(studyNo,"N ");
        System.out.println("글번호 :: "+ studyNo);
        System.out.println("댓글 리스트 " + studyReply);
        return studyReply.stream().map(studyReply1 -> modelMapper.map(studyReply1, StudyReplyDTO.class)).collect(Collectors.toList());
    }

    /*
     * writer : 김형경
     * writerDate : 22/04/28 ~ 22/04/28
     * title : 모집글 상세페이지 삭제
     * content : 글번호로 조회하여 삭제 상태를 수정
     * */
    @Transactional
    public void removeStudy(int no) {
        Study study = studyRepository.findById(no).get();

        study.setPostStatus("Y");
    }

    /*
     * writer : 김형경
     * writerDate : 22/04/29 ~ 22/05/01
     * title : 작성한 모집글 수정
     * content :
     * */
    @Transactional
    public void studyModify(StudyDTO studyDTO) {
        Study study = studyRepository.findById(studyDTO.getStudyNo()).get();

        study.setTitle(studyDTO.getTitle());
        study.setContent(studyDTO.getContent());
        study.setEndDate(studyDTO.getEndDate());
        study.setLimited(studyDTO.getLimited());
        study.setLinked(studyDTO.getLinked());
        study.setRecruitStatus(studyDTO.getRecruitStatus());
    }
    /*
     * writer : 김형경
     * writerDate : 22/05/01 ~ 22/05/01
     * title : 모집글 댓글 작성
     * content :
     * */
    public StudyReplyDTO studyReplyRegist(StudyReplyDTO replyDTO) {

        Member member = memberRepository.getById(replyDTO.getWriter().getNo());

        replyDTO.setWriter(modelMapper.map(member, MemberDTO.class));

        StudyReply studyReply = studyReplyRepository.save(modelMapper.map(replyDTO, StudyReply.class));

        return modelMapper.map(studyReply, StudyReplyDTO.class);


    }

    @Transactional
    public void studyReplyRemove(StudyReplyDTO replyDTO) {
        StudyReply studyReply = studyReplyRepository.getById(replyDTO.getReplyNo());

        studyReply.setReplyStatus("Y");

        modelMapper.map(studyReply, StudyReplyDTO.class);

        studyReplyRepository.save(modelMapper.map(studyReply, StudyReply.class));
    }
}