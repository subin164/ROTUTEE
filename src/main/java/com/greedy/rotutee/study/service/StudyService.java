package com.greedy.rotutee.study.service;

import com.greedy.rotutee.member.entity.Member;
import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.entity.Study;
import com.greedy.rotutee.study.repository.StudyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyService {

    private final StudyRepository studyRepository;
    private final ModelMapper modelMapper;

    public StudyService(StudyRepository studyRepository, ModelMapper modelMapper) {
        this.studyRepository = studyRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void studyRegist(StudyDTO study) {

        studyRepository.save(modelMapper.map(study, Study.class));

    }

    public List<StudyDTO> findStudyList() {
        List<Study> studyList = studyRepository.findAll();

        return studyList.stream().map(Study -> modelMapper.map(Study, StudyDTO.class)).collect(Collectors.toList());

    }
}
