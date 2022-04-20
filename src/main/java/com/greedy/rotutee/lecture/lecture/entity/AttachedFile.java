package com.greedy.rotutee.lecture.lecture.entity;

import javax.persistence.*;

@Entity(name = "Lecture_AttachedFile")
@Table(name = "TBL_ATTACHED_FILE")
public class AttachedFile {

    @Id
    @Column(name = "ATTACHED_FILE_NO")
    private int attachedFileNo;

    @Column(name = "ORIGINAL_ATTACHED_FILE_NAME")
    private String originalAttachedFileName;

    @Column(name = "SAVE_ATTACHED_FILE_NAME")
    private String saveAttachedFileName;

    @Column(name = "THUMBNAIL_FILE_NAME")
    private String thumbnailFileName;

    @Column(name = "STORAGE_PATH")
    private String storageFile;

    @Column(name = "THUMBNAIL_FILE_PATH")
    private String thumbnailFilePath;

    @Column(name = "DIVISION")
    private String division;

    @Column(name = "FILE_DELETION_YN")
    private String fileDeletionYN;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Column(name = "BOARD_NO")
    private int boardNo;

    @JoinColumn(name = "LECTURE_NO")
    @ManyToOne
    private Lecture lecture;

    @Column(name = "DOCUMENT_NO")
    private Integer documentNo;

    public AttachedFile() {
    }

    public AttachedFile(int attachedFileNo, String originalAttachedFileName, String saveAttachedFileName, String thumbnailFileName, String storageFile, String thumbnailFilePath, String division, String fileDeletionYN, int memberNo, int boardNo, Lecture lecture, Integer documentNo) {
        this.attachedFileNo = attachedFileNo;
        this.originalAttachedFileName = originalAttachedFileName;
        this.saveAttachedFileName = saveAttachedFileName;
        this.thumbnailFileName = thumbnailFileName;
        this.storageFile = storageFile;
        this.thumbnailFilePath = thumbnailFilePath;
        this.division = division;
        this.fileDeletionYN = fileDeletionYN;
        this.memberNo = memberNo;
        this.boardNo = boardNo;
        this.lecture = lecture;
        this.documentNo = documentNo;
    }

    public int getAttachedFileNo() {
        return attachedFileNo;
    }

    public void setAttachedFileNo(int attachedFileNo) {
        this.attachedFileNo = attachedFileNo;
    }

    public String getOriginalAttachedFileName() {
        return originalAttachedFileName;
    }

    public void setOriginalAttachedFileName(String originalAttachedFileName) {
        this.originalAttachedFileName = originalAttachedFileName;
    }

    public String getSaveAttachedFileName() {
        return saveAttachedFileName;
    }

    public void setSaveAttachedFileName(String saveAttachedFileName) {
        this.saveAttachedFileName = saveAttachedFileName;
    }

    public String getThumbnailFileName() {
        return thumbnailFileName;
    }

    public void setThumbnailFileName(String thumbnailFileName) {
        this.thumbnailFileName = thumbnailFileName;
    }

    public String getStorageFile() {
        return storageFile;
    }

    public void setStorageFile(String storageFile) {
        this.storageFile = storageFile;
    }

    public String getThumbnailFilePath() {
        return thumbnailFilePath;
    }

    public void setThumbnailFilePath(String thumbnailFilePath) {
        this.thumbnailFilePath = thumbnailFilePath;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getFileDeletionYN() {
        return fileDeletionYN;
    }

    public void setFileDeletionYN(String fileDeletionYN) {
        this.fileDeletionYN = fileDeletionYN;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Integer getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(Integer documentNo) {
        this.documentNo = documentNo;
    }

    @Override
    public String toString() {
        return "AttachedFile{" +
                "attachedFileNo=" + attachedFileNo +
                ", originalAttachedFileName='" + originalAttachedFileName + '\'' +
                ", saveAttachedFileName='" + saveAttachedFileName + '\'' +
                ", thumbnailFileName='" + thumbnailFileName + '\'' +
                ", storageFile='" + storageFile + '\'' +
                ", thumbnailFilePath='" + thumbnailFilePath + '\'' +
                ", division='" + division + '\'' +
                ", fileDeletionYN='" + fileDeletionYN + '\'' +
                ", memberNo=" + memberNo +
                ", boardNo=" + boardNo +
                ", lecture=" + lecture +
                ", documentNo=" + documentNo +
                '}';
    }
}
