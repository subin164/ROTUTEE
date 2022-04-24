package com.greedy.rotutee.lecture.request.service;

import com.greedy.rotutee.lecture.request.dto.AttachedFileDTO;
import com.greedy.rotutee.lecture.request.dto.LectureDTO;
import com.greedy.rotutee.lecture.request.entity.AttachedFile;
import com.greedy.rotutee.lecture.request.entity.Lecture;
import com.greedy.rotutee.lecture.request.entity.Member;
import com.greedy.rotutee.lecture.request.repository.RequestAttachedFileRepository;
import com.greedy.rotutee.lecture.request.repository.RequestLectureRepository;
import com.greedy.rotutee.lecture.request.repository.RequestMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureRequestServiceImpl implements LectureRequestService{

    private final ModelMapper modelMapper;
    private final RequestLectureRepository requestLectureRepository;
    private final RequestMemberRepository requestMemberRepository;
    private final RequestAttachedFileRepository requestAttachedFileRepository;

    @Autowired
    public LectureRequestServiceImpl(ModelMapper modelMapper, RequestLectureRepository requestLectureRepository, RequestMemberRepository requestMemberRepository, RequestAttachedFileRepository requestAttachedFileRepository) {
        this.modelMapper = modelMapper;
        this.requestLectureRepository = requestLectureRepository;
        this.requestMemberRepository = requestMemberRepository;
        this.requestAttachedFileRepository = requestAttachedFileRepository;
    }

    @Override
    public List<LectureDTO> findLectureListBytutorNo(int memberNo) {

        Member tutor = requestMemberRepository.findByNo(memberNo);

        List<Lecture> lectureList = requestLectureRepository.findByTutor(tutor);

        List<LectureDTO> lectureDTOList = lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
        System.out.println("lectureDTOList = " + lectureDTOList);
        for(LectureDTO lecture : lectureDTOList) {
            AttachedFile file = requestAttachedFileRepository.findLectureThumbnail(lecture.getLectureNo());

            if(file != null) {
            AttachedFileDTO fileDTO = modelMapper.map(file, AttachedFileDTO.class);
            lecture.setThumbnailPath(fileDTO.getThumbnailFilePath());
            }
        }

        return lectureDTOList;
    }
}
