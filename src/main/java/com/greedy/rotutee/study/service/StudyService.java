package com.greedy.rotutee.study.service;

import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.entity.Study;
import com.greedy.rotutee.study.repository.StudyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudyService {

    private final StudyRepository studyRepository;
    private final ModelMapper modelMapper;

    public StudyService(StudyRepository studyRepository, ModelMapper modelMapper) {
        this.studyRepository = studyRepository;
        this.modelMapper = modelMapper;
    }

    //    study 모집글 전체 조회
    public Page<StudyDTO> findStudyList(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize());

        return studyRepository.findAll(pageable).map(study -> modelMapper.map(study, StudyDTO.class));
    }

    //    study 모집글 작성
    @Transactional
    public void studyRegist(StudyDTO studyDTO) {

        studyRepository.save(modelMapper.map(studyDTO, Study.class));
    }


    //    모집글 상세페이지 조회
    public StudyDTO findDetailByStudyNo(int studyNo) {
        Study study = studyRepository.findById(studyNo).get();

        return modelMapper.map(study, StudyDTO.class);

    }
}
