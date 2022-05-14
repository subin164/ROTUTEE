package com.greedy.rotutee.dashboard.lms.entity;


import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.entity
 * fileName : LMSAttachment
 * author : SeoYoung
 * date : 2022-05-13
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-13 SeoYoung 최초 생성
 */
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "Dashboard_AttachedFile")
@Table(name = "TBL_ATTACHED_FILE")
@SequenceGenerator(
        name = "DASHBOARD_ATTACHED_FILE_SEQ_GENERATOR",
        sequenceName = "ATTACHED_FILE_NO",
        initialValue = 1,
        allocationSize = 1
)
public class LMSAttachment {

    @Id
    @Column(name = "ATTACHED_FILE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "DASHBOARD_ATTACHED_FILE_SEQ_GENERATOR"
    )
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
    private Integer memberNo;

    @Column(name = "LECTURE_NO")
    private Integer lectureNo;

    @Column(name = "CLASS_NO")
    private Integer classNo;

    @Override
    public String toString() {
        return "LMSAttachment{" +
                "attachedFileNo=" + attachedFileNo +
                ", originalAttachedFileName='" + originalAttachedFileName + '\'' +
                ", saveAttachedFileName='" + saveAttachedFileName + '\'' +
                ", thumbnailFileName='" + thumbnailFileName + '\'' +
                ", storageFile='" + storageFile + '\'' +
                ", thumbnailFilePath='" + thumbnailFilePath + '\'' +
                ", division='" + division + '\'' +
                ", fileDeletionYN='" + fileDeletionYN + '\'' +
                ", memberNo=" + memberNo +
                ", lectureNo=" + lectureNo +
                ", classNo=" + classNo +
                '}';
    }
}
