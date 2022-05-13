package com.greedy.rotutee.dashboard.lms.dto;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.dto
 * fileName : LMSAttachmentDTO
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
@ToString
public class LMSAttachmentDTO {

    private int attachedFileNo;
    private String originalAttachedFileName;
    private String saveAttachedFileName;
    private String thumbnailFileName;
    private String storageFile;
    private String thumbnailFilePath;
    private String division;
    private String fileDeletionYN;
    private int memberNo;
    private int lectureNo;
    private int classNo;
}
