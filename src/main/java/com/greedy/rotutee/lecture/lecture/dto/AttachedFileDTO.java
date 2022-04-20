package com.greedy.rotutee.lecture.lecture.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AttachedFileDTO {

    private int attachedFileNo;
    private String originalAttachedFileName;
    private String saveAttachedFileName;
    private String thumbnailFileName;
    private String storageFile;
    private String thumbnailFilePath;
    private String division;
    private String fileDeletionYN;
    private Integer memberNo;
    private MemberDTO member;
    private Integer boardNo;
    private Integer lectureNo;
    private LectureDTO lecture;
    private Integer documentNo;
    
}
