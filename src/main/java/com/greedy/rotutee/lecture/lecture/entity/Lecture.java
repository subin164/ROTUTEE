package com.greedy.rotutee.lecture.lecture.entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name = "Lecture_Lecture")
@Table(name = "TBL_LECTURE")
@SequenceGenerator(
        name = "LECTURE_LECTURE_SEQ_GENERATOR",
        sequenceName = "BOARD_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Lecture {

    @Id
    @Column(name = "LECTURE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LECTURE_LECTURE_SEQ_GENERATOR"
    )
    private int lectureNo;

    @Column(name = "LECTURE_NAME")
    private String lectureName;

    @Column(name = "LECTURE_PRICE")
    private int lecturePrice;

    @Column(name = "LECTURE_LEVEL")
    private String lectureLevel;

    @Column(name = "LECTURE_SUMMARY")
    private String lectureSummary;

    @Column(name = "LECTURE_DETAILS")
    private String lecturedetails;

    @Column(name = "REVISION_HISTORY")
    private String revisionHistory;

    @Column(name = "LECTURE_APPROVAL_STATUS")
    private String lectureApprovalStatus;

    @Column(name = "LECTURE_OPENING_DATE")
    private Date lectureOpeningDate;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne
    private Member tutor;

    @Column(name = "APPLICATION_DATE")
    private Date applicationDate;

    @Column(name = "APPLICATION_DIVISION")
    private String applicationDivision;

    @JoinColumn(name = "LECTURE_CATEGORY_NO")
    @ManyToOne
    private LectureCategory lectureCategory;

    @OneToMany(mappedBy = "lecture")
    private List<Chapter> chapterList;

    @OneToMany(mappedBy = "lecture")
    private List<LectureReview> lectureReviewList;

    @OneToMany(mappedBy = "lecture")
    private List<AttachedFile> attachedFileList;


    public Lecture() {
    }

    public Lecture(int lectureNo, String lectureName, int lecturePrice, String lectureLevel, String lectureSummary, String lecturedetails, String revisionHistory, String lectureApprovalStatus, Date lectureOpeningDate, Member tutor, Date applicationDate, String applicationDivision, LectureCategory lectureCategory, List<Chapter> chapterList, List<LectureReview> lectureReviewList, List<AttachedFile> attachedFileList) {
        this.lectureNo = lectureNo;
        this.lectureName = lectureName;
        this.lecturePrice = lecturePrice;
        this.lectureLevel = lectureLevel;
        this.lectureSummary = lectureSummary;
        this.lecturedetails = lecturedetails;
        this.revisionHistory = revisionHistory;
        this.lectureApprovalStatus = lectureApprovalStatus;
        this.lectureOpeningDate = lectureOpeningDate;
        this.tutor = tutor;
        this.applicationDate = applicationDate;
        this.applicationDivision = applicationDivision;
        this.lectureCategory = lectureCategory;
        this.chapterList = chapterList;
        this.lectureReviewList = lectureReviewList;
        this.attachedFileList = attachedFileList;
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

    public Member getTutor() {
        return tutor;
    }

    public void setTutor(Member tutor) {
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

    public LectureCategory getLectureCategory() {
        return lectureCategory;
    }

    public void setLectureCategory(LectureCategory lectureCategory) {
        this.lectureCategory = lectureCategory;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    public List<LectureReview> getLectureReviewList() {
        return lectureReviewList;
    }

    public void setLectureReviewList(List<LectureReview> lectureReviewList) {
        this.lectureReviewList = lectureReviewList;
    }

    public List<AttachedFile> getAttachedFileList() {
        return attachedFileList;
    }

    public void setAttachedFileList(List<AttachedFile> attachedFileList) {
        this.attachedFileList = attachedFileList;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "lectureNo=" + lectureNo +
                ", lectureName='" + lectureName + '\'' +
                ", lecturePrice=" + lecturePrice +
                ", lectureLevel='" + lectureLevel + '\'' +
                ", lectureSummary='" + lectureSummary + '\'' +
                ", lecturedetails='" + lecturedetails + '\'' +
                ", revisionHistory='" + revisionHistory + '\'' +
                ", lectureApprovalStatus='" + lectureApprovalStatus + '\'' +
                ", lectureOpeningDate=" + lectureOpeningDate +
                ", tutor=" + tutor +
                ", applicationDate=" + applicationDate +
                ", applicationDivision='" + applicationDivision + '\'' +
                ", lectureCategory=" + lectureCategory +
//                ", chapterList=" + chapterList +
//                ", lectureReviewList=" + lectureReviewList +
//                ", attachedFileList=" + attachedFileList +
                '}';
    }
}
