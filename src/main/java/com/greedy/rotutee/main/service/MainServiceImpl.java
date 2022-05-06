package com.greedy.rotutee.main.service;

import com.greedy.rotutee.main.dto.AttachedFileDTO;
import com.greedy.rotutee.main.dto.LectureDTO;
import com.greedy.rotutee.main.entity.*;
import com.greedy.rotutee.main.repository.MainAttachedFileRepository;
import com.greedy.rotutee.main.repository.MainLectureRepository;
import com.greedy.rotutee.main.repository.MainMemberInterestRepository;
import com.greedy.rotutee.main.repository.MainMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName      : com.greedy.rotutee.main.model.service
 * fileName         : MainServiceImpl
 * author           : SEOK
 * date             : 2022-05-04
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-04      SEOK         최초 생성
 */
@Service
public class MainServiceImpl implements MainService{

    private final ModelMapper modelMapper;
    private final MainLectureRepository mainLectureRepository;
    private final MainMemberInterestRepository mainMemberInterestRepository;
    private final MainMemberRepository mainMemberRepository;
    private final MainAttachedFileRepository mainAttachedFileRepository;

    @Autowired
    public MainServiceImpl(ModelMapper modelMapper, MainLectureRepository mainLectureRepository, MainMemberInterestRepository mainMemberInterestRepository, MainMemberRepository mainMemberRepository, MainAttachedFileRepository mainAttachedFileRepository) {
        this.modelMapper = modelMapper;
        this.mainLectureRepository = mainLectureRepository;
        this.mainMemberInterestRepository = mainMemberInterestRepository;
        this.mainMemberRepository = mainMemberRepository;
        this.mainAttachedFileRepository = mainAttachedFileRepository;
    }

    @Override
    public List<LectureDTO> findRecentLectureList() {

        List<Lecture> recentLectureList = mainLectureRepository.findRecentLectureList();

        return recentLectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<LectureDTO> findPopularLectureList() {

        List<Lecture> popularLectureList = mainLectureRepository.findPopularLectureList();

        return popularLectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<AttachedFileDTO> findBannerListByMemberNo(int no) {

        Member member = mainMemberRepository.findById(no).get();

        List<MemberInterest> memberInterestList = mainMemberInterestRepository.findByMemberOrderByInterestDegreeDesc(member);
        if(memberInterestList != null && memberInterestList.size() > 0) {
            LectureCategory interestCategory = memberInterestList.get(0).getCategory();

            List<AttachedFile> fileList = mainAttachedFileRepository.findByCategoryNo(interestCategory.getLectureCategoryNo());

            if (fileList != null) {
                return fileList.stream().map(file -> modelMapper.map(file, AttachedFileDTO.class)).collect(Collectors.toList());
            }
        } else {
            return null;
        }
        return null;
    }
}
