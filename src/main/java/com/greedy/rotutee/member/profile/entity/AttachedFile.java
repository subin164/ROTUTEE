package com.greedy.rotutee.member.profile.entity;

import com.greedy.rotutee.member.member.entity.Member;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.member.entity
 * fileName : AttachedFile
 * author : 7sang
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 7sang 최초 생성
 */

@Entity(name = "AttachedFile")
@Table(name = "TBL_ATTACHED_FILE")
@SequenceGenerator(
        name = "ATTACHED_FILE_SEQ_GENERATOR",
        sequenceName = "ATTACHED_FILE_NO",
        initialValue = 1,
        allocationSize = 1
)
public class AttachedFile {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ATTACHED_FILE_SEQ_GENERATOR"
    )
    @Column(name = "ATTACHED_FILE_NO")
    private int attachedFileNo;

    @Column(name = "ORIGINAL_ATTACHED_FILE_NAME")
    private String originalAttachedFileName;

    @Column(name = "SAVE_ATTACHED_FILE_NAME")
    private String saveAttachedFileName;

    @Column(name = "THUMBNAIL_FILE_NAME")
    private String thumbnailAttachedFileName;

    @Column(name = "STORAGE_PATH")
    private String storagePath;

    @Column(name = "THUMBNAIL_FILE_PATH")
    private String thumbnailFilePath;

    @Column(name = "DIVISION")
    private String division;

    @Column(name = "FILE_DELETION_YN")
    private String fileDeletionYn;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne
    private Member member;

    public AttachedFile() {}

    public AttachedFile(int attachedFileNo, String originalAttachedFileName, String saveAttachedFileName, String thumbnailAttachedFileName, String storagePath, String thumbnailFilePath, String fileDeletionYn, Member member) {
        this.attachedFileNo = attachedFileNo;
        this.originalAttachedFileName = originalAttachedFileName;
        this.saveAttachedFileName = saveAttachedFileName;
        this.thumbnailAttachedFileName = thumbnailAttachedFileName;
        this.storagePath = storagePath;
        this.thumbnailFilePath = thumbnailFilePath;
        this.fileDeletionYn = fileDeletionYn;
        this.member = member;
    }

    public String getDivision() {
        return this.division;
    }

    public void setDivision(String division) {
        this.division = division;
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

    public String getThumbnailAttachedFileName() {
        return thumbnailAttachedFileName;
    }

    public void setThumbnailAttachedFileName(String thumbnailAttachedFileName) {
        this.thumbnailAttachedFileName = thumbnailAttachedFileName;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public String getThumbnailFilePath() {
        return thumbnailFilePath;
    }

    public void setThumbnailFilePath(String thumbnailFilePath) {
        this.thumbnailFilePath = thumbnailFilePath;
    }

    public String getFileDeletionYn() {
        return fileDeletionYn;
    }

    public void setFileDeletionYn(String fileDeletionYn) {
        this.fileDeletionYn = fileDeletionYn;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "AttachedFile{" +
                "attachedFileNo=" + attachedFileNo +
                ", originalAttachedFileName='" + originalAttachedFileName + '\'' +
                ", saveAttachedFileName='" + saveAttachedFileName + '\'' +
                ", thumbnailAttachedFileName='" + thumbnailAttachedFileName + '\'' +
                ", storagePath='" + storagePath + '\'' +
                ", thumbnailFilePath='" + thumbnailFilePath + '\'' +
                ", fileDeletionYn='" + fileDeletionYn + '\'' +
                ", member=" + member +
                '}';
    }
}
