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
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * packageName      : com.greedy.rotutee.lecture.request.service
 * fileName         : LectureRequestServiceImpl
 * author           : SEOK
 * date             : 2022-04-25
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-04-25      SEOK         최초 생성
 */
@Service
public class LectureRequestServiceImpl implements LectureRequestService{

    private final ModelMapper modelMapper;
    private final RequestLectureRepository requestLectureRepository;
    private final RequestMemberRepository requestMemberRepository;
    private final RequestAttachedFileRepository requestAttachedFileRepository;
    private final RequestLectureCategoryRepository requestLectureCategoryRepository;
    private final RequestLectureRejectionReasonRepository requestLectureRejectionReasonRepository;
    private final RequestLectureRequestProcessingHistoryRepository requestLectureRequestProcessingHistoryRepository;
    private final RequestNoticeRepository requestNoticeRepository;
    private final RequestNoticeCategoryRepository requestNoticeCategoryRepository;
    private final RequestMemberLectureRepository requestMemberLectureRepository;

    @Autowired
    public LectureRequestServiceImpl(ModelMapper modelMapper, RequestLectureRepository requestLectureRepository, RequestMemberRepository requestMemberRepository, RequestAttachedFileRepository requestAttachedFileRepository, RequestLectureCategoryRepository requestLectureCategoryRepository, RequestLectureRejectionReasonRepository requestLectureRejectionReasonRepository, RequestLectureRequestProcessingHistoryRepository requestLectureRequestProcessingHistoryRepository, RequestNoticeRepository requestNoticeRepository, RequestNoticeCategoryRepository requestNoticeCategoryRepository, RequestMemberLectureRepository requestMemberLectureRepository) {
        this.modelMapper = modelMapper;
        this.requestLectureRepository = requestLectureRepository;
        this.requestMemberRepository = requestMemberRepository;
        this.requestAttachedFileRepository = requestAttachedFileRepository;
        this.requestLectureCategoryRepository = requestLectureCategoryRepository;
        this.requestLectureRejectionReasonRepository = requestLectureRejectionReasonRepository;
        this.requestLectureRequestProcessingHistoryRepository = requestLectureRequestProcessingHistoryRepository;
        this.requestNoticeRepository = requestNoticeRepository;
        this.requestNoticeCategoryRepository = requestNoticeCategoryRepository;
        this.requestMemberLectureRepository = requestMemberLectureRepository;
    }

    /**
     * methodName : findLectureListBytutorNo
     * author : SEOK
     * description : 튜터의 강의 목록 조회 기능
     *
     * @Param int memberNo 회원 번호
     * @Return List<LectureDTO> lectureDTOList
     * */
    @Override
    public List<LectureDTO> findLectureListBytutorNo(int memberNo) {

        Member tutor = requestMemberRepository.findByNo(memberNo);

        List<Lecture> lectureList = requestLectureRepository.findByTutor(tutor);

        List<LectureDTO> lectureDTOList = lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());

        for(LectureDTO lectureDTO : lectureDTOList) {
            String division = "강의";
            String deletion = "N ";

            Lecture lecture = modelMapper.map(lectureDTO, Lecture.class);

            AttachedFile file = requestAttachedFileRepository.findByLectureAndDivisionAndFileDeletionYN(lecture, division, deletion);
            if(file != null) {
                lectureDTO.setSaveFileName(file.getSaveAttachedFileName());

            }
        }

        return lectureDTOList;
    }

    /**
     * methodName : registLectureOpeningApplication
     * author : SEOK
     * description : 강의 개설 요청 기능 및 동영상, 사진 파일 저장 기능
     *
     * @Param LectureDTO newLecture 강의 정보가 담긴 객체
     * @Param int categoryNo 강의 카테고리 번호
     * @Param int memberNo 회원 번호
     * */
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

    /**
     * methodName : findLectureByLectureNo
     * author : SEOK
     * description : 강의 번호로 강의 개설 요청 세부내용 조회 기능
     *
     * @Param int lectureNo 강의 번호
     * @Return LectureDTO
     * */
    @Override
    public LectureDTO findLectureByLectureNo(int lectureNo) {

        Lecture lecture = requestLectureRepository.findByLectureNo(lectureNo);

        return modelMapper.map(lecture, LectureDTO.class);
    }

    /**
     * methodName : modifyLectureApprovalStatus
     * author : SEOK
     * description : 강의 승인 상태 '승인' 변경 및 알림 추가 및 강의별 회원 정보 추가 기능
     *
     * @Param int lectureNo
     * */
    @Override
    @Transactional
    public void modifyLectureApprovalStatus(int lectureNo) {

        Lecture lecture = requestLectureRepository.findByLectureNo(lectureNo);
        lecture.setLectureApprovalStatus("승인");
        lecture.setLectureOpeningDate(new Date(System.currentTimeMillis()));

        LectureDTO lectureDTO = modelMapper.map(lecture, LectureDTO.class);

        MemberLectureDTO memberLectureDTO = new MemberLectureDTO();
        memberLectureDTO.setLectureNo(lectureNo);
        memberLectureDTO.setMemberNo(lecture.getTutor().getNo());

        requestMemberLectureRepository.save(modelMapper.map(memberLectureDTO, MemberLecture.class));

        int categoryNo = 7;

        NoticeCategory noticeCategoryEntity = requestNoticeCategoryRepository.findById(categoryNo).get();
        NoticeCategoryDTO noticeCategory = modelMapper.map(noticeCategoryEntity, NoticeCategoryDTO.class);

        String content = "[" + lecture.getLectureName() + "] 강의가 개설되었습니다.";

        MemberDTO member = modelMapper.map(lecture.getTutor(), MemberDTO.class);

        NoticeDTO notice = new NoticeDTO();
        notice.setNoticeCategory(noticeCategory);
        notice.setNoticeContent(content);
        notice.setMember(member);
        notice.setNoticeTime(new Date(System.currentTimeMillis()));

        requestNoticeRepository.save(modelMapper.map(notice, Notice.class));
    }

    /**
     * methodName : rejectLecture
     * author : SEOK
     * description : 강의 승인 상태 '거절' 변경 및 거절 이력 추가 및 알림 추가 기능
     *
     * @Param int lectureNo 강의 번호
     * @Param int rejectionCategoryNo 거절 카테고리 번호
     * */
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

        int categoryNo = 7;

        NoticeCategory noticeCategoryEntity = requestNoticeCategoryRepository.findById(categoryNo).get();
        NoticeCategoryDTO noticeCategory = modelMapper.map(noticeCategoryEntity, NoticeCategoryDTO.class);

        String content = "[" + lecture.getLectureName() + "] 강의 개설이 거절되었습니다. 이유 : [" + reason.getLectureRejectionContentReason() + "]";

        MemberDTO member = modelMapper.map(lecture.getTutor(), MemberDTO.class);

        NoticeDTO notice = new NoticeDTO();
        notice.setNoticeCategory(noticeCategory);
        notice.setNoticeContent(content);
        notice.setMember(member);
        notice.setNoticeTime(new Date(System.currentTimeMillis()));

        requestNoticeRepository.save(modelMapper.map(notice, Notice.class));
    }

    /**
     * methodName : findAllLecture
     * author : SEOK
     * description : 모든 강의 조회 기능
     *
     * @Param Pageable pageable 페이징 정보가 담긴 객체
     * @Param Map<String, String> searchMap 검색조건과 검색어가 담긴 객체
     * @Return Page<LectureDTO>
     * */
    @Override
    public Page<LectureDTO> findAllLecture(Pageable pageable, Map<String, String> searchMap) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() -1, pageable.getPageSize(),
                Sort.by("lectureNo").descending());

        String searchCondition = searchMap.get("searchCondition");
        String searchValue = searchMap.get("searchValue");

        Page<Lecture> lectureEntityList = null;

        if(searchCondition == null || searchCondition.equals("")) {
            lectureEntityList = requestLectureRepository.findAll(pageable);

        } else if(searchCondition.equals("title")){
            lectureEntityList = requestLectureRepository.findByLectureNameContaining(searchValue, pageable);

        } else if(searchCondition.equals("category")) {
            LectureCategory category = requestLectureCategoryRepository.findByLectureCategoryName(searchValue);

            lectureEntityList = requestLectureRepository.findByLectureCategory(category, pageable);

        } else if(searchCondition.equals("status")) {
            lectureEntityList = requestLectureRepository.findByLectureApprovalStatus(searchValue, pageable);

        }

        return lectureEntityList.map(lectureEntity -> modelMapper.map(lectureEntity, LectureDTO.class));
    }

}
