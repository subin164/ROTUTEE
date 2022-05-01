package com.greedy.rotutee.lecture.request.dto;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

public class LectureDTO {

    private int lectureNo;
    private String lectureName;
    private int lecturePrice;
    private String lectureLevel;
    private String lectureSummary;
    private String lectureDetails;
    private String revisionHistory;
    private String lectureApprovalStatus;
    private Date lectureOpeningDate;
    private int memberNo;
    private MemberDTO tutor;
    private Date applicationDate;
    private String applicationDivision;
    private int lectureCategoryNo;
    private LectureCategoryDTO category;

    private List<MultipartFile> fileList;

    private List<AttachedFileDTO> imageList;
    private List<ChapterDTO> chapterList;

    public List<ChapterDTO> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<ChapterDTO> chapterList) {
        this.chapterList = chapterList;
    }

    public LectureDTO() {
    }

    public LectureDTO(int lectureNo, String lectureName, int lecturePrice, String lectureLevel, String lectureSummary, String lectureDetails, String revisionHistory, String lectureApprovalStatus, Date lectureOpeningDate, int memberNo, MemberDTO tutor, Date applicationDate, String applicationDivision, int lectureCategoryNo, LectureCategoryDTO category, String originalPath, String thumbnailPath, String bannerPath, List<MultipartFile> fileList, List<AttachedFileDTO> imageList) {
        this.lectureNo = lectureNo;
        this.lectureName = lectureName;
        this.lecturePrice = lecturePrice;
        this.lectureLevel = lectureLevel;
        this.lectureSummary = lectureSummary;
        this.lectureDetails = lectureDetails;
        this.revisionHistory = revisionHistory;
        this.lectureApprovalStatus = lectureApprovalStatus;
        this.lectureOpeningDate = lectureOpeningDate;
        this.memberNo = memberNo;
        this.tutor = tutor;
        this.applicationDate = applicationDate;
        this.applicationDivision = applicationDivision;
        this.lectureCategoryNo = lectureCategoryNo;
        this.category = category;
        this.fileList = fileList;
        this.imageList = imageList;
    }

    public int getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(int lectureNo) {
        this.lectureNo = lectureNo;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public int getLecturePrice() {
        return lecturePrice;
    }

    public void setLecturePrice(int lecturePrice) {
        this.lecturePrice = lecturePrice;
    }

    public String getLectureLevel() {
        return lectureLevel;
    }

    public void setLectureLevel(String lectureLevel) {
        this.lectureLevel = lectureLevel;
    }

    public String getLectureSummary() {
        return lectureSummary;
    }

    public void setLectureSummary(String lectureSummary) {
        this.lectureSummary = lectureSummary;
    }

    public String getLectureDetails() {
        return lectureDetails;
    }

    public void setLectureDetails(String lectureDetails) {
        this.lectureDetails = lectureDetails;
    }

    public String getRevisionHistory() {
        return revisionHistory;
    }

    public void setRevisionHistory(String revisionHistory) {
        this.revisionHistory = revisionHistory;
    }

    public String getLectureApprovalStatus() {
        return lectureApprovalStatus;
    }

    public void setLectureApprovalStatus(String lectureApprovalStatus) {
        this.lectureApprovalStatus = lectureApprovalStatus;
    }

    public Date getLectureOpeningDate() {
        return lectureOpeningDate;
    }

    public void setLectureOpeningDate(Date lectureOpeningDate) {
        this.lectureOpeningDate = lectureOpeningDate;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public MemberDTO getTutor() {
        return tutor;
    }

    public void setTutor(MemberDTO tutor) {
        this.tutor = tutor;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getApplicationDivision() {
        return applicationDivision;
    }

    public void setApplicationDivision(String applicationDivision) {
        this.applicationDivision = applicationDivision;
    }

    public int getLectureCategoryNo() {
        return lectureCategoryNo;
    }

    public void setLectureCategoryNo(int lectureCategoryNo) {
        this.lectureCategoryNo = lectureCategoryNo;
    }

    public LectureCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(LectureCategoryDTO category) {
        this.category = category;
    }

    public List<AttachedFileDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<AttachedFileDTO> imageList) {
        this.imageList = imageList;
    }

    public List<MultipartFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<MultipartFile> fileList) {
        this.fileList = fileList;
    }

    @Override
    public String toString() {
        return "LectureDTO{" +
                "lectureNo=" + lectureNo +
                ", lectureName='" + lectureName + '\'' +
                ", lecturePrice=" + lecturePrice +
                ", lectureLevel='" + lectureLevel + '\'' +
                ", lectureSummary='" + lectureSummary + '\'' +
                ", lectureDetails='" + lectureDetails + '\'' +
                ", revisionHistory='" + revisionHistory + '\'' +
                ", lectureApprovalStatus='" + lectureApprovalStatus + '\'' +
                ", lectureOpeningDate=" + lectureOpeningDate +
                ", memberNo=" + memberNo +
                ", tutor=" + tutor +
                ", applicationDate=" + applicationDate +
                ", applicationDivision='" + applicationDivision + '\'' +
                ", lectureCategoryNo=" + lectureCategoryNo +
                ", category=" + category +
//                ", chapterList='" + chapterList + '\'' +
                '}';
    }
}
