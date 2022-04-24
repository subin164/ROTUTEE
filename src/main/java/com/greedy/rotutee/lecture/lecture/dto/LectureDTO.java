package com.greedy.rotutee.lecture.lecture.dto;

import com.greedy.rotutee.lecture.request.entity.AttachedFile;
import lombok.*;

import java.sql.Date;
import java.util.List;

public class LectureDTO {

    private int lectureNo;
    private String lectureName;
    private int lecturePrice;
    private String lectureLevel;
    private String lectureSummary;
    private String lecturedetails;
    private String revisionHistory;
    private String lectureApprovalStatus;
    private Date lectureOpeningDate;
    private int memberNo;
    private MemberDTO tutor;
    private Date applicationDate;
    private String applicationDivision;
    private int lectureCategoryNo;
    private LectureCategoryDTO category;
    private String originalPath;
    private String thumbnailPath;
    private String bannerPath;

    public LectureDTO() {
    }

    public LectureDTO(int lectureNo, String lectureName, int lecturePrice, String lectureLevel, String lectureSummary, String lecturedetails, String revisionHistory, String lectureApprovalStatus, Date lectureOpeningDate, int memberNo, MemberDTO tutor, Date applicationDate, String applicationDivision, int lectureCategoryNo, LectureCategoryDTO category, String originalPath, String thumbnailPath, String bannerPath) {
        this.lectureNo = lectureNo;
        this.lectureName = lectureName;
        this.lecturePrice = lecturePrice;
        this.lectureLevel = lectureLevel;
        this.lectureSummary = lectureSummary;
        this.lecturedetails = lecturedetails;
        this.revisionHistory = revisionHistory;
        this.lectureApprovalStatus = lectureApprovalStatus;
        this.lectureOpeningDate = lectureOpeningDate;
        this.memberNo = memberNo;
        this.tutor = tutor;
        this.applicationDate = applicationDate;
        this.applicationDivision = applicationDivision;
        this.lectureCategoryNo = lectureCategoryNo;
        this.category = category;
        this.originalPath = originalPath;
        this.thumbnailPath = thumbnailPath;
        this.bannerPath = bannerPath;
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

    public String getLecturedetails() {
        return lecturedetails;
    }

    public void setLecturedetails(String lecturedetails) {
        this.lecturedetails = lecturedetails;
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

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getBannerPath() {
        return bannerPath;
    }

    public void setBannerPath(String bannerPath) {
        this.bannerPath = bannerPath;
    }

    @Override
    public String toString() {
        return "LectureDTO{" +
                "lectureNo=" + lectureNo +
                ", lectureName='" + lectureName + '\'' +
                ", lecturePrice=" + lecturePrice +
                ", lectureLevel='" + lectureLevel + '\'' +
                ", lectureSummary='" + lectureSummary + '\'' +
                ", lecturedetails='" + lecturedetails + '\'' +
                ", revisionHistory='" + revisionHistory + '\'' +
                ", lectureApprovalStatus='" + lectureApprovalStatus + '\'' +
                ", lectureOpeningDate=" + lectureOpeningDate +
                ", memberNo=" + memberNo +
                ", tutor=" + tutor +
                ", applicationDate=" + applicationDate +
                ", applicationDivision='" + applicationDivision + '\'' +
                ", lectureCategoryNo=" + lectureCategoryNo +
                ", category=" + category +
                ", originalPath='" + originalPath + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", bannerPath='" + bannerPath + '\'' +
                '}';
    }
}
