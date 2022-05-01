package com.greedy.rotutee.lecture.request.entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name = "Request_Lecture")
@Table(name = "TBL_LECTURE")
@SequenceGenerator(
        name = "LECTURE_LECTURE_SEQ_GENERATOR",
        sequenceName = "LECTURE_NO",
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
    private String lectureDetails;

    @Column(name = "REVISION_HISTORY")
    private String revisionHistory;

    @Column(name = "LECTURE_APPROVAL_STATUS")
    private String lectureApprovalStatus;

    @Column(name = "LECTURE_OPENING_DATE")
    private Date lectureOpeningDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_NO")
    private Member tutor;

    @Column(name = "APPLICATION_DATE")
    private Date applicationDate;

    @Column(name = "APPLICATION_DIVISION")
    private String applicationDivision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LECTURE_CATEGORY_NO")
    private LectureCategory lectureCategory;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.PERSIST)
    private List<Chapter> chapterList;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.PERSIST)
    private List<AttachedFile> imageList;

    public Lecture() {
    }

    public Lecture(int lectureNo, String lectureName, int lecturePrice, String lectureLevel, String lectureSummary, String lectureDetails, String revisionHistory, String lectureApprovalStatus, Date lectureOpeningDate, Member tutor, Date applicationDate, String applicationDivision, LectureCategory lectureCategory, List<Chapter> chapterList, List<AttachedFile> imageList) {
        this.lectureNo = lectureNo;
        this.lectureName = lectureName;
        this.lecturePrice = lecturePrice;
        this.lectureLevel = lectureLevel;
        this.lectureSummary = lectureSummary;
        this.lectureDetails = lectureDetails;
        this.revisionHistory = revisionHistory;
        this.lectureApprovalStatus = lectureApprovalStatus;
        this.lectureOpeningDate = lectureOpeningDate;
        this.tutor = tutor;
        this.applicationDate = applicationDate;
        this.applicationDivision = applicationDivision;
        this.lectureCategory = lectureCategory;
        this.chapterList = chapterList;
        this.imageList = imageList;
    }

    public List<AttachedFile> getImageList() {
        return imageList;
    }

    public void setImageList(List<AttachedFile> imageList) {
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

    @Override
    public String toString() {
        return "Lecture{" +
                "lectureNo=" + lectureNo +
                ", lectureName='" + lectureName + '\'' +
                ", lecturePrice=" + lecturePrice +
                ", lectureLevel='" + lectureLevel + '\'' +
                ", lectureSummary='" + lectureSummary + '\'' +
                ", lectureDetails='" + lectureDetails + '\'' +
                ", revisionHistory='" + revisionHistory + '\'' +
                ", lectureApprovalStatus='" + lectureApprovalStatus + '\'' +
                ", lectureOpeningDate=" + lectureOpeningDate +
                ", tutor=" + tutor +
                ", applicationDate=" + applicationDate +
                ", applicationDivision='" + applicationDivision + '\'' +
                ", lectureCategory=" + lectureCategory +
//                ", chapterList=" + chapterList +
                '}';
    }
}
