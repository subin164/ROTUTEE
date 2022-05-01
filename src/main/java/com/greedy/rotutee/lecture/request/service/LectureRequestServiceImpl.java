package com.greedy.rotutee.lecture.request.service;

import com.greedy.rotutee.lecture.request.dto.*;
import com.greedy.rotutee.lecture.request.entity.AttachedFile;
import com.greedy.rotutee.lecture.request.entity.Lecture;
import com.greedy.rotutee.lecture.request.entity.LectureCategory;
import com.greedy.rotutee.lecture.request.entity.Member;
import com.greedy.rotutee.lecture.request.repository.RequestAttachedFileRepository;
import com.greedy.rotutee.lecture.request.repository.RequestLectureCategoryRepository;
import com.greedy.rotutee.lecture.request.repository.RequestLectureRepository;
import com.greedy.rotutee.lecture.request.repository.RequestMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public LectureRequestServiceImpl(ModelMapper modelMapper, RequestLectureRepository requestLectureRepository, RequestMemberRepository requestMemberRepository, RequestAttachedFileRepository requestAttachedFileRepository, RequestLectureCategoryRepository requestLectureCategoryRepository) {
        this.modelMapper = modelMapper;
        this.requestLectureRepository = requestLectureRepository;
        this.requestMemberRepository = requestMemberRepository;
        this.requestAttachedFileRepository = requestAttachedFileRepository;
        this.requestLectureCategoryRepository = requestLectureCategoryRepository;
    }

    @Override
    public List<LectureDTO> findLectureListBytutorNo(int memberNo) {

        Member tutor = requestMemberRepository.findByNo(memberNo);

        List<Lecture> lectureList = requestLectureRepository.findByTutor(tutor);

        List<LectureDTO> lectureDTOList = lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
        for(LectureDTO lecture : lectureDTOList) {
            String deletion = "N";
            String division = "강의";
            Lecture lectureEntity = modelMapper.map(lecture, Lecture.class);
            AttachedFile file = requestAttachedFileRepository.findByLectureAndFileDeletionYNAndDivision(lectureEntity, deletion, division);

            if(file != null) {
            AttachedFileDTO fileDTO = modelMapper.map(file, AttachedFileDTO.class);
            }
        }

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
        String thumbnailUploadDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\sj\\thumbnail";
        String bannerUploadDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\sj\\banner";
        String videoUploadDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\sj\\video";

        File originalDirectory = new File(fileUploadDirectory);
        File thumbnailDirectory = new File(thumbnailUploadDirectory);
        File bannerDirectory = new File(bannerUploadDirectory);
        File videoDirectory = new File(videoUploadDirectory);

        List<AttachedFile> imageEntityList = new ArrayList<>();

        if(imageList.size() == 1) {
            if (!originalDirectory.exists() || !thumbnailDirectory.exists()) {
                originalDirectory.mkdirs();
                thumbnailDirectory.mkdirs();
            }

            String originFileName = imageList.get(0).getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;
            String thumbnailFileName = UUID.randomUUID().toString().replace("-", "") + ext;

            System.out.println("originFileName = " + originFileName);
            System.out.println("thumbnailFileName = " + thumbnailFileName);

            imageList.get(0).transferTo(new File(thumbnailUploadDirectory + "/" + savedFileName));

            AttachedFileDTO thumbnail = new AttachedFileDTO();

            thumbnail.setOriginalAttachedFileName(originFileName);
            thumbnail.setSaveAttachedFileName(savedFileName);
            thumbnail.setThumbnailFileName(thumbnailFileName);
            thumbnail.setThumbnailFilePath(thumbnailUploadDirectory);
            thumbnail.setStorageFile(fileUploadDirectory);
            thumbnail.setDivision("강의");
            thumbnail.setLecture(newLecture);
            thumbnail.setFileDeletionYN("N");

            AttachedFile newThumbnail = modelMapper.map(thumbnail, AttachedFile.class);

            imageEntityList.add(newThumbnail);

            originalDirectory.setWritable(true);
            originalDirectory.setReadable(true);
            thumbnailDirectory.setWritable(true);
            thumbnailDirectory.setReadable(true);

        } else if(imageList.size() == 2) {
            if (!originalDirectory.exists() || !thumbnailDirectory.exists() || !bannerDirectory.exists()) {
                originalDirectory.mkdirs();
                thumbnailDirectory.mkdirs();
                bannerDirectory.mkdirs();
            }

            String originFileName1 = imageList.get(0).getOriginalFilename();
            String ext1 = originFileName1.substring(originFileName1.lastIndexOf("."));
            String savedFileName1 = UUID.randomUUID().toString().replace("-", "") + ext1;
            String thumbnailFileName1 = UUID.randomUUID().toString().replace("-", "") + ext1;

            imageList.get(0).transferTo(new File(thumbnailUploadDirectory + "/" + savedFileName1));

            AttachedFileDTO thumbnail = new AttachedFileDTO();
            thumbnail.setOriginalAttachedFileName(originFileName1);
            thumbnail.setSaveAttachedFileName(savedFileName1);
            thumbnail.setThumbnailFileName(thumbnailFileName1);
            thumbnail.setThumbnailFilePath(thumbnailUploadDirectory);
            thumbnail.setStorageFile(fileUploadDirectory);
            thumbnail.setDivision("강의");
            thumbnail.setLecture(newLecture);
            thumbnail.setFileDeletionYN("N");

            AttachedFile newThumbnail = modelMapper.map(thumbnail, AttachedFile.class);

            String originFileName2 = imageList.get(1).getOriginalFilename();
            String ext2 = originFileName2.substring(originFileName2.lastIndexOf("."));
            String savedFileName2 = UUID.randomUUID().toString().replace("-", "") + ext2;
            String thumbnailFileName2 = UUID.randomUUID().toString().replace("-", "") + ext2;

            imageList.get(1).transferTo(new File(bannerUploadDirectory + "/" + savedFileName2));

            AttachedFileDTO banner = new AttachedFileDTO();
            banner.setOriginalAttachedFileName(originFileName2);
            banner.setSaveAttachedFileName(savedFileName2);
            banner.setThumbnailFileName(thumbnailFileName2);
            banner.setThumbnailFilePath(bannerUploadDirectory);
            banner.setStorageFile(fileUploadDirectory);
            banner.setDivision("배너");
            banner.setLecture(newLecture);
            banner.setFileDeletionYN("N");

            AttachedFile newBanner = modelMapper.map(banner, AttachedFile.class);

            imageEntityList.add(newThumbnail);
            imageEntityList.add(newBanner);

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
//                    classDTO.setVideoPath("www.naver.com");
                    classDTO.setChapter(chapter);

                List<MultipartFile> classVideoList = classDTO.getFileList();
                List<AttachedFile> videoList = new ArrayList<>();
                    for(MultipartFile classVideo : classVideoList) {

                        if(!videoDirectory.exists()) {
                            videoDirectory.mkdirs();
                        }

                        String originFileName = classVideoList.get(0).getOriginalFilename();
                        String ext = originFileName.substring(originFileName.lastIndexOf("."));
                        String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                        System.out.println("originFileName = " + originFileName);
                        System.out.println("savedFileName = " + savedFileName);

                        classVideo.transferTo(new File(videoUploadDirectory + "/" + savedFileName));

                        AttachedFileDTO video = new AttachedFileDTO();
                        video.setOriginalAttachedFileName(originFileName);
                        video.setSaveAttachedFileName(savedFileName);
                        video.setStorageFile(fileUploadDirectory);
                        video.setDivision("강의영상");
                        video.setClasses(classDTO);
                        video.setFileDeletionYN("N");

                        AttachedFile videoEntity = modelMapper.map(video, AttachedFile.class);

                        videoList.add(videoEntity);

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
    public List<LectureDTO> findStatusOfLectureIsWaiting() {

        String status = "대기";
        List<Lecture> lectureList = requestLectureRepository.findByLectureApprovalStatus(status);

        return lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<LectureDTO> findStatusOfLectureIsNotWaiting() {

        String status1 = "승인";
        String status2 = "거절";
        List<Lecture> lectureList = requestLectureRepository.findByLectureApprovalStatusOrLectureApprovalStatus(status1, status2);

        return lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
    }

    @Override
    public LectureDTO findLectureByLectureNo(int lectureNo) {

        Lecture lectuer = requestLectureRepository.findByLectureNo(lectureNo);

        return modelMapper.map(lectuer, LectureDTO.class);
    }

    @Override
    @Transactional
    public void modifyLectureApprovalStatus(int lectureNo) {

        Lecture lecture = requestLectureRepository.findByLectureNo(lectureNo);
        lecture.setLectureApprovalStatus("승인");
    }

}
