package com.greedy.rotutee.study.service;

import com.greedy.rotutee.study.dto.StudyDTO;
import com.greedy.rotutee.study.entity.Study;
import com.greedy.rotutee.study.repository.StudyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudyService {

    @Autowired
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
}
