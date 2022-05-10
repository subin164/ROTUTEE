package com.greedy.rotutee.lecture.lecture.service;

import com.greedy.rotutee.lecture.lecture.dto.*;
import com.greedy.rotutee.lecture.lecture.entity.*;
import com.greedy.rotutee.lecture.lecture.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureMainServiceImpl implements  LectureMainService{

    private final LectureMainRepository lectureMainRepository;
    private final ModelMapper modelMapper;
    private final ChapterRepository chapterRepository;
    private final LectureReviewMainRepository lectureReviewMainRepository;
    private final MemberLectureMainRepository memberLectureMainRepository;
    private final LectureAttachedFileRepository lectureAttachedFileRepository;
    private final LectureMemberInterestRepository lectureMemberInterestRepository;
    private final MemberRepository memberRepository;
    private final LecturePointHistoryRepository lecturePointHistoryRepository;
    private final LecturePointAcquisitionPlaceRepository lecturePointAcquisitionPlaceRepository;
    private final LectureCategoryMainRepository lectureCategoryRepository;

    @Autowired
    public LectureMainServiceImpl(LectureMainRepository lectureMainRepository, ModelMapper modelMapper, ChapterRepository chapterRepository, LectureReviewMainRepository lectureReviewMainRepository, MemberLectureMainRepository memberLectureMainRepository, LectureAttachedFileRepository lectureAttachedFileRepository, LectureMemberInterestRepository lectureMemberInterestRepository, MemberRepository memberRepository, LecturePointHistoryRepository lecturePointHistoryRepository, LecturePointAcquisitionPlaceRepository lecturePointAcquisitionPlaceRepository, LectureCategoryMainRepository lectureCategoryRepository) {
        this.lectureMainRepository = lectureMainRepository;
        this.modelMapper = modelMapper;
        this.chapterRepository = chapterRepository;
        this.lectureReviewMainRepository = lectureReviewMainRepository;
        this.memberLectureMainRepository = memberLectureMainRepository;
        this.lectureAttachedFileRepository = lectureAttachedFileRepository;
        this.lectureMemberInterestRepository = lectureMemberInterestRepository;
        this.memberRepository = memberRepository;
        this.lecturePointHistoryRepository = lecturePointHistoryRepository;
        this.lecturePointAcquisitionPlaceRepository = lecturePointAcquisitionPlaceRepository;
        this.lectureCategoryRepository = lectureCategoryRepository;
    }

    @Override
    public List<LectureDTO> findAllLecture() {

        String lectureApprovalStatus = "승인";

        List<Lecture> lectureList = lectureMainRepository.findBylectureApprovalStatus(lectureApprovalStatus);

        List<LectureDTO> lectureDTOList = lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
        for(LectureDTO lecture : lectureDTOList) {
            lecture.setSaveFileName(lectureAttachedFileRepository.findSaveFileNameBylectureNo(lecture.getLectureNo()));
        }

        return lectureDTOList;
    }

    @Override
    public List<LectureDTO> findApproveLectureBysearchObject(int searchCondition, String searchValue) {

        List<Lecture> lectureList = null;
        List<LectureDTO> lectureDTOList = null;
        if(searchCondition == 1) {
            lectureList = lectureMainRepository.findBylectureNameContaining(searchValue);
            lectureDTOList = lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
            for(LectureDTO lecture : lectureDTOList) {
                lecture.setSaveFileName(lectureAttachedFileRepository.findSaveFileNameBylectureNo(lecture.getLectureNo()));
            }
        } else if(searchCondition == 2) {
            lectureList = lectureMainRepository.findLecturesByTutorName(searchValue);
            lectureDTOList = lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
            for(LectureDTO lecture : lectureDTOList) {
                lecture.setSaveFileName(lectureAttachedFileRepository.findSaveFileNameBylectureNo(lecture.getLectureNo()));
            }
        }

        return lectureDTOList;
    }

    @Override
    public LectureDTO findLectureByLectureNo(int lectureNo) {

        Lecture lectureEntity = lectureMainRepository.findById(lectureNo).get();
        LectureDTO lecture = modelMapper.map(lectureEntity, LectureDTO.class);

        String division = "프로필";
        String deletion = "N ";
        AttachedFile image = lectureAttachedFileRepository.findByMemberNoAndDivisionAndFileDeletionYN(lecture.getTutor().getNo(), division, deletion);
        if(image != null) {
            lecture.getTutor().setImageName(image.getSaveAttachedFileName());
        }
        lecture.setSaveFileName(lectureAttachedFileRepository.findSaveFileNameBylectureNo(lecture.getLectureNo()));

        return lecture;
    }

    @Override
    public List<ChapterDTO> findChapterListByLectureNo(int lectureNo) {

        List<Chapter> chapterList = chapterRepository.findByLectureNo(lectureNo);

        return chapterList.stream().map(chapter -> modelMapper.map(chapter, ChapterDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<LectureReviewDTO> findReviewListByLectureNo(int lectureNo) {

        List<LectureReview> lectureReviewEntityList = lectureReviewMainRepository.findLectureReviewByLectureNoAndLectureReviewRemoveYN(lectureNo);

        List<LectureReviewDTO> lectureReviewList = lectureReviewEntityList.stream().map(lectureReview -> modelMapper.map(lectureReview, LectureReviewDTO.class)).collect(Collectors.toList());
        for(LectureReviewDTO review : lectureReviewList) {
            String division = "프로필";
            String deletion = "N";

            AttachedFile image = lectureAttachedFileRepository.findByMemberNoAndDivisionAndFileDeletionYN(review.getWriter().getNo(), division, deletion);
            if(image != null) {
                review.getWriter().setImageName(image.getSaveAttachedFileName());
            }
        }

        return lectureReviewList;
    }

    @Override
    public int findMemberCountByLectureNo(int lectureNo) {

        int result = memberLectureMainRepository.countByLectureNo(lectureNo);

        return result;
    }

    @Override
    public int findGradeAverageByLectureNo(int lectureNo) {

        int result = 0;

        Integer avg = lectureReviewMainRepository.findfindGradeAverageByLectureNo(lectureNo);

        if(avg != null) {
            result = avg;
        }

        return result;
    }

    @Override
    public MemberLectureDTO findMemberInLecture(int memberNo, int lectureNo) {

        MemberLecture history = memberLectureMainRepository.findByMemberNoAndLectureNo(memberNo, lectureNo);

        if(history == null) {
            return null;
        }

        return modelMapper.map(history, MemberLectureDTO.class);
    }

    @Override
    @Transactional
    public void registLectureReviewAndPoint(int rating, String content, int lectureNo, int memberNo) {


        Member memberEntity = memberRepository.findById(memberNo).get();
        MemberDTO member = modelMapper.map(memberEntity, MemberDTO.class);

        List<LectureReview> reviewEntityList = lectureReviewMainRepository.findByWriterAndLectureNo(memberEntity, lectureNo);

        System.out.println("reviewEntityList = " + reviewEntityList);
        System.out.println("reviewEntityList.size() = " + reviewEntityList.size());

        if(reviewEntityList.size() <= 0) {

            LectureReviewDTO lectureReview = new LectureReviewDTO();
            lectureReview.setLectureGrade(rating);
            lectureReview.setLectureReviewContent(content);
            lectureReview.setLectureReviewDate(new Date(System.currentTimeMillis()));
            lectureReview.setLectureReviewRemoveYN("N");
            lectureReview.setLectureNo(lectureNo);
            lectureReview.setWriter(member);

            lectureReviewMainRepository.save(modelMapper.map(lectureReview, LectureReview.class));

            String place = "강의평";
            PointAcquisitionPlace acquisitionPlace = lecturePointAcquisitionPlaceRepository.findByPlaceName(place);

            PointAcquisitionPlaceDTO acquisitionPlaceDTO = modelMapper.map(acquisitionPlace, PointAcquisitionPlaceDTO.class);

            List<PointHistory> historyList = lecturePointHistoryRepository.findByMemberOrderByHistoryNoDesc(memberEntity);
            if(historyList.size() > 0) {
                PointHistory recentHistory = historyList.get(0);

                int finalPoint = acquisitionPlace.getPoint() + recentHistory.getFinalPoint();
                String division = "획득";

                PointHistoryDTO newHistory = new PointHistoryDTO();
                newHistory.setChangeDate(new Date(System.currentTimeMillis()));
                newHistory.setChangePoint(acquisitionPlace.getPoint());
                newHistory.setFinalPoint(finalPoint);
                newHistory.setDivision(division);
                newHistory.setMember(member);
                newHistory.setPointAcquisitionPlace(acquisitionPlaceDTO);

                lecturePointHistoryRepository.save(modelMapper.map(newHistory, PointHistory.class));
            } else {

                String division = "획득";

                PointHistoryDTO newHistory = new PointHistoryDTO();
                newHistory.setChangeDate(new Date(System.currentTimeMillis()));
                newHistory.setChangePoint(acquisitionPlace.getPoint());
                newHistory.setFinalPoint(acquisitionPlace.getPoint());
                newHistory.setDivision(division);
                newHistory.setMember(member);
                newHistory.setPointAcquisitionPlace(acquisitionPlaceDTO);

                lecturePointHistoryRepository.save(modelMapper.map(newHistory, PointHistory.class));
            }
        } else {

            LectureReviewDTO lectureReview = new LectureReviewDTO();
            lectureReview.setLectureGrade(rating);
            lectureReview.setLectureReviewContent(content);
            lectureReview.setLectureReviewDate(new Date(System.currentTimeMillis()));
            lectureReview.setLectureReviewRemoveYN("N");
            lectureReview.setLectureNo(lectureNo);
            lectureReview.setWriter(member);

            lectureReviewMainRepository.save(modelMapper.map(lectureReview, LectureReview.class));
        }
    }

    @Override
    @Transactional
    public void modifyReviewContent(int lectureReviewNo, String lectureReviewContent) {

        LectureReview foundReview = lectureReviewMainRepository.findById(lectureReviewNo).get();
        foundReview.setLectureReviewContent(lectureReviewContent);

    }

    @Override
    @Transactional
    public void removeReview(int lectureReviewNo) {

        String status = "Y";

        LectureReview foundReview = lectureReviewMainRepository.findById(lectureReviewNo).get();
        foundReview.setLectureReviewRemoveYN(status);

    }

    @Override
    @Transactional
    public void registInterestDegree(int no, LectureCategoryDTO category) {

        Member member = memberRepository.findById(no).get();

        LectureCategory categoryEntity = modelMapper.map(category, LectureCategory.class);

        MemberInterest memberInterest = lectureMemberInterestRepository.findByMemberAndCategory(member, categoryEntity);

        if(memberInterest == null) {

            int degree = 1;

            MemberInterestDTO interest = new MemberInterestDTO();
            interest.setMember(modelMapper.map(member, MemberDTO.class));
            interest.setCategory(category);
            interest.setInterestDegree(degree);

            lectureMemberInterestRepository.save(modelMapper.map(interest, MemberInterest.class));

        } else {

            int increasedDegree = memberInterest.getInterestDegree() + 1;
            memberInterest.setInterestDegree(increasedDegree);
        }

    }

    @Override
    public List<LectureDTO> findLectureByCategoryName(String categoryName) {

        LectureCategory category = lectureCategoryRepository.findByLectureCategoryName(categoryName);

        List<Lecture> lectureList = lectureMainRepository.findByLectureCategory(category);

        return lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
    }
}
