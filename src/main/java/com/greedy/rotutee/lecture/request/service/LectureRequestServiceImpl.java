package com.greedy.rotutee.lecture.request.service;

import com.greedy.rotutee.lecture.request.dto.*;
import com.greedy.rotutee.lecture.request.entity.*;
import com.greedy.rotutee.lecture.request.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LectureRequestServiceImpl implements LectureRequestService{

    private final ModelMapper modelMapper;
    private final RequestLectureRepository requestLectureRepository;
    private final RequestMemberRepository requestMemberRepository;
    private final RequestAttachedFileRepository requestAttachedFileRepository;
    private final RequestLectureCategoryRepository requestLectureCategoryRepository;
    private final RequestLectureRejectionReasonRepository requestLectureRejectionReasonRepository;
    private final RequestLectureRequestProcessingHistoryRepository requestLectureRequestProcessingHistoryRepository;

    @Autowired
    public LectureRequestServiceImpl(ModelMapper modelMapper, RequestLectureRepository requestLectureRepository, RequestMemberRepository requestMemberRepository, RequestAttachedFileRepository requestAttachedFileRepository, RequestLectureCategoryRepository requestLectureCategoryRepository, RequestLectureRejectionReasonRepository requestLectureRejectionReasonRepository, RequestLectureRequestProcessingHistoryRepository requestLectureRequestProcessingHistoryRepository) {
        this.modelMapper = modelMapper;
        this.requestLectureRepository = requestLectureRepository;
        this.requestMemberRepository = requestMemberRepository;
        this.requestAttachedFileRepository = requestAttachedFileRepository;
        this.requestLectureCategoryRepository = requestLectureCategoryRepository;
        this.requestLectureRejectionReasonRepository = requestLectureRejectionReasonRepository;
        this.requestLectureRequestProcessingHistoryRepository = requestLectureRequestProcessingHistoryRepository;
    }

    @Override
    public List<LectureDTO> findLectureListBytutorNo(int memberNo) {

        Member tutor = requestMemberRepository.findByNo(memberNo);

        List<Lecture> lectureList = requestLectureRepository.findByTutor(tutor);

        List<LectureDTO> lectureDTOList = lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());

        return lectureDTOList;
    }

    @Override
    @Transactional
    public void registLectureOpeningApplication(LectureDTO newLecture, int categoryNo, int memberNo) throws IOException {

        Member tutor = requestMemberRepository.findByNo(memberNo);
        MemberDTO tutorDTO = modelMapper.map(tutor, MemberDTO.class);

        LectureCategory category = requestLectureCategoryRepository.findByLectureCategoryNo(categoryNo);
        LectureCategoryDTO categoryDTO = modelMapper.map(category, LectureCategoryDTO.class);

        newLecture.setTutor(tutorDTO);
        newLecture.setCategory(categoryDTO);
        newLecture.setLectureApprovalStatus("대기");
        newLecture.setApplicationDivision("신청");
        newLecture.setApplicationDate(new Date(System.currentTimeMillis()));

        List<MultipartFile> imageList = newLecture.getFileList();

        String fileUploadDirectory  = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\sj\\image";
        String thumbnailUploadDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\thumbnail";
        String bannerUploadDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\banner";
        String videoUploadDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\video";

        File originalDirectory = new File(fileUploadDirectory);
        File thumbnailDirectory = new File(thumbnailUploadDirectory);
        File bannerDirectory = new File(bannerUploadDirectory);
        File videoDirectory = new File(videoUploadDirectory);

        List<AttachedFileDTO> imageEntityList = new ArrayList<>();

        if(imageList.size() == 1) {
            if (!originalDirectory.exists() || !thumbnailDirectory.exists()) {
                originalDirectory.mkdirs();
                thumbnailDirectory.mkdirs();
            }

            String originFileName = imageList.get(0).getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

            imageList.get(0).transferTo(new File(thumbnailUploadDirectory + "/" + savedFileName));

            AttachedFileDTO thumbnail = new AttachedFileDTO();

            thumbnail.setOriginalAttachedFileName(originFileName);
            thumbnail.setSaveAttachedFileName(savedFileName);
            thumbnail.setStorageFile(thumbnailUploadDirectory + "/" + savedFileName);
            thumbnail.setDivision("강의");
            thumbnail.setLecture(newLecture);
            thumbnail.setFileDeletionYN("N");

            imageEntityList.add(thumbnail);

            originalDirectory.setWritable(true);
            originalDirectory.setReadable(true);
            thumbnailDirectory.setWritable(true);
            thumbnailDirectory.setReadable(true);

            newLecture.setImageList(imageEntityList);

        } else if(imageList.size() == 2) {
            if (!originalDirectory.exists() || !thumbnailDirectory.exists() || !bannerDirectory.exists()) {
                originalDirectory.mkdirs();
                thumbnailDirectory.mkdirs();
                bannerDirectory.mkdirs();
            }

            String originFileName1 = imageList.get(0).getOriginalFilename();
            String ext1 = originFileName1.substring(originFileName1.lastIndexOf("."));
            String savedFileName1 = UUID.randomUUID().toString().replace("-", "") + ext1;

            imageList.get(0).transferTo(new File(thumbnailUploadDirectory + "/" + savedFileName1));

            AttachedFileDTO thumbnail = new AttachedFileDTO();
            thumbnail.setOriginalAttachedFileName(originFileName1);
            thumbnail.setSaveAttachedFileName(savedFileName1);
            thumbnail.setStorageFile(thumbnailUploadDirectory + "/" + savedFileName1);
            thumbnail.setDivision("강의");
            thumbnail.setLecture(newLecture);
            thumbnail.setFileDeletionYN("N");

            String originFileName2 = imageList.get(1).getOriginalFilename();
            String ext2 = originFileName2.substring(originFileName2.lastIndexOf("."));
            String savedFileName2 = UUID.randomUUID().toString().replace("-", "") + ext2;

            imageList.get(1).transferTo(new File(bannerUploadDirectory + "/" + savedFileName2));

            AttachedFileDTO banner = new AttachedFileDTO();
            banner.setOriginalAttachedFileName(originFileName2);
            banner.setSaveAttachedFileName(savedFileName2);
            banner.setStorageFile(bannerUploadDirectory + "/" + savedFileName2);
            banner.setDivision("배너");
            banner.setLecture(newLecture);
            banner.setFileDeletionYN("N");

            imageEntityList.add(thumbnail);
            imageEntityList.add(banner);

            newLecture.setImageList(imageEntityList);

            originalDirectory.setWritable(true);
            originalDirectory.setReadable(true);
            thumbnailDirectory.setWritable(true);
            thumbnailDirectory.setReadable(true);
            bannerDirectory.setWritable(true);
            bannerDirectory.setReadable(true);
        }

        List<ChapterDTO> chapterList = newLecture.getChapterList();
            for(ChapterDTO chapter : chapterList) {
                chapter.setLecture(newLecture);

            List<ClassDTO> classDTOList = chapter.getClassList();
            for(ClassDTO classDTO : classDTOList) {

                classDTO.setChapter(chapter);

                List<MultipartFile> classVideoList = classDTO.getFileList();
                List<AttachedFileDTO> videoList = new ArrayList<>();
                    for(MultipartFile classVideo : classVideoList) {

                        if(!videoDirectory.exists()) {
                            videoDirectory.mkdirs();
                        }

                        String originFileName = classVideoList.get(0).getOriginalFilename();
                        String ext = originFileName.substring(originFileName.lastIndexOf("."));
                        String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                        classVideo.transferTo(new File(videoUploadDirectory + "/" + savedFileName));

                        AttachedFileDTO video = new AttachedFileDTO();
                        video.setOriginalAttachedFileName(originFileName);
                        video.setSaveAttachedFileName(savedFileName);
                        video.setStorageFile(videoUploadDirectory + "/" + savedFileName);
                        video.setDivision("강의영상");
                        video.setClasses(classDTO);
                        video.setFileDeletionYN("N");

                        videoList.add(video);

                        classDTO.setVideoList(videoList);
                        classDTO.setVideoPath(savedFileName);

                        videoDirectory.setWritable(true);
                        videoDirectory.setReadable(true);
                    }

                List<QuizDTO> quizList = classDTO.getQuizList();
                    for(QuizDTO quiz : quizList) {
                        quiz.setClassEntity(classDTO);
                        quiz.setQuizType("객관식");
                    }
                }
            }

        requestLectureRepository.save(modelMapper.map(newLecture, Lecture.class));
    }

    @Override
    public Page<LectureDTO> findStatusOfLectureIsWaiting(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() -1, pageable.getPageSize(),
                Sort.by("lectureNo").descending());

        String status = "대기";
        Page<Lecture> lectureList = requestLectureRepository.findByLectureApprovalStatus(status, pageable);

        return lectureList.map(lecture -> modelMapper.map(lecture, LectureDTO.class));
    }

    @Override
    public Page<LectureDTO> findStatusOfLectureIsNotWaiting(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() -1, pageable.getPageSize(),
                Sort.by("lectureNo").descending());

        String status1 = "승인";
        String status2 = "거절";
        Page<Lecture> lectureList = requestLectureRepository.findByLectureApprovalStatusOrLectureApprovalStatus(status1, status2, pageable);

        return lectureList.map(lecture -> modelMapper.map(lecture, LectureDTO.class));
    }

    @Override
    public LectureDTO findLectureByLectureNo(int lectureNo) {

        Lecture lecture = requestLectureRepository.findByLectureNo(lectureNo);

        return modelMapper.map(lecture, LectureDTO.class);
    }

    @Override
    @Transactional
    public void modifyLectureApprovalStatus(int lectureNo) {

        Lecture lecture = requestLectureRepository.findByLectureNo(lectureNo);
        lecture.setLectureApprovalStatus("승인");
        lecture.setLectureOpeningDate(new Date(System.currentTimeMillis()));
    }

    @Override
    @Transactional
    public void rejectLecture(int lectureNo, int rejectionCategoryNo) {

        Lecture lecture = requestLectureRepository.findByLectureNo(lectureNo);
        lecture.setLectureApprovalStatus("거절");

        LectureDTO lectureDTO = modelMapper.map(lecture, LectureDTO.class);

        LectureRejectionReason reasonEntity = requestLectureRejectionReasonRepository.findByLectureRejectionReasonNo(rejectionCategoryNo);
        LectureRejectionReasonDTO reason = modelMapper.map(reasonEntity, LectureRejectionReasonDTO.class);

        LectureRequestProcessingHistoryDTO history = new LectureRequestProcessingHistoryDTO();
        history.setProcessingDate(new Date(System.currentTimeMillis()));
        history.setReason(reason);
        history.setProcessingStatus("거절");
        history.setLecture(lectureDTO);

        requestLectureRequestProcessingHistoryRepository.save(modelMapper.map(history, LectureRequestProcessingHistory.class));

    }

}
